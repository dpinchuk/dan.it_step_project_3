package view;

import controllers.BookingController;
import controllers.FlightController;
import controllers.UserController;
import models.BookingModel;
import models.FlightModel;
import models.UserModel;
import utils.Exceptions;
import utils.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static utils.Constants.*;

/**
 * Console [UI] class
 *
 * @author Dmitry Pinchuk
 */
public class ViewConsole {

    private FlightController flightController = new FlightController();
    private BookingController bookingController = new BookingController();
    private UserController userController = new UserController();
    private Scanner scanner;
    private int sessionId = 0;
    private UserModel user = GUEST;
    private Logger logger = new Logger();

    /**
     * Main action list
     */
    public void run() {
        String selectConsole = "";
        while (true) {
            System.out.println("[Flight booking service]");
            System.out.println("Choose action: ");
            System.out.println("[0] - Exit");
            System.out.println("[1] - Online scoreboard");
            System.out.println("[2] - View flight information");
            System.out.println("[3] - Search flight by data and booking" + (this.sessionId != 0 ? " [Session id: " + this.sessionId + "]" : " [" + this.user.getUserName() + " " + this.user.getUserSurname() + "]"));
            System.out.println("[4] - Cancel booking" + (this.sessionId != 0 ? " [Session id: " + this.sessionId + "]" : " [" + this.user.getUserName() + " " + this.user.getUserSurname() + "]"));
            System.out.println("[5] - Show user flights " + (this.sessionId != 0 ? " [Session id: " + this.sessionId + "]" : " [" + this.user.getUserName() + " " + this.user.getUserSurname() + "]"));
            System.out.println("[6] - " + (this.sessionId == 0 ? "Log in" : "Log out [Session id: " + this.sessionId + "]"));
            System.out.println((this.sessionId == 0 ? "[7] - Registration" : ""));
            System.out.print("\nChoose your action: ");
            this.scanner = new Scanner(System.in);
            selectConsole = this.scanner.nextLine();
            if (selectConsole.equals(EXIT)) {
                this.logger.info("[" + this.user.getUserName() + " " + this.user.getUserSurname() + "] has left the system");
                System.out.println("Thank you for using our service!");
                this.userController.writeUserListToFile();
                this.flightController.writeFlightListToFile();
                this.bookingController.writeBookingListToFile();
                break;
            } else {
                select(selectConsole);
            }
        }
    }

    /**
     * Action selector
     *
     * @param select as String
     */
    private void select(String select) {
        switch (select) {
            case "1":
                actionOnlineScoreboard();
                break;
            case "2":
                actionViewFlightInfo();
                break;
            case "3":
                actionSearchFlightAndCreateBooking();
                break;
            case "4":
                actionDeleteFlightBooking();
                break;
            case "5":
                actionGetUserBookings();
                break;
            case "6":
                actionUserLogInOut();
                break;
            case "7":
                actionRegistration();
                break;
            default:
                try {
                    throw new Exceptions("Unknown action!");
                } catch (Exceptions exceptions) {
                    System.out.println(exceptions.getMessage());
                }
                this.logger.error("[" + this.user.getUserName() + " " + this.user.getUserSurname() + "] selected unknown action");
        }
        System.out.println("\n\n\n");
    }

    /**
     * Shows all flights next Time
     */
    private void actionOnlineScoreboard() {
        this.logger.info("[" + this.user.getUserName() + " " + this.user.getUserSurname() + "] opened the scoreboard");
        System.out.println("\n\n[Information on all flights from Kiev in the next {" + getHoursFromMilli(DIFFERENCE) + "} hours]");
        List<FlightModel> flightsListNextHours = this.flightController.getFlightsDuringTime(DIFFERENCE);
        printFlightList(flightsListNextHours);
    }

    /**
     * Shows flight info by [id]
     */
    private void actionViewFlightInfo() {
        System.out.println("\n\n[View flight information]");
        int id = inputIntData("Flight [id] must be between [1] and [" + this.flightController.getFlightListSize() + "]!", "Flight [id]");
        if (id > 0) {
            printObjectAsString(this.flightController.getFlightInfo(id));
            this.logger.info("[" + this.user.getUserName() + " " + this.user.getUserSurname() + "] has viewed information about exist flight [#" + id + "]");
        } else {
            try {
                throw new Exceptions(SEARCH_FALSE);
            } catch (Exceptions exceptions) {
                System.out.println(exceptions.getMessage());
            }
            this.logger.info("[" + this.user.getUserName() + " " + this.user.getUserSurname() + "] has viewed information about unknown flight [#" + id + "]");
        }
    }

    private void actionSearchFlightAndCreateBooking() {
        if (this.sessionId != 0) {
            System.out.println("\n\n[Flight search and booking]");
            String destination = inputStringData("[1. Destination must be consist of letters and case does not matter [Kiev, berlin, MADRID]!]", "destination");
            if (!destination.equals("")) {
                String date = inputStringData("[2. Date must be in a format [YYYY-MM-DD]!]", "date");
                if (!date.equals("") && isDateValid(date)) {
                    int ticketsNumber = inputIntData("[3. Number of tickets must be an integer!]", "number of tickets");
                    if (ticketsNumber > 0) {
                        List<FlightModel> listFlights = this.flightController.getFlightByData(destination, date, ticketsNumber);
                        if (listFlights.size() > 0) {
                            printFlightList(listFlights);
                            FlightModel flight = selectFlight(listFlights, ticketsNumber);
                            if (flight != null) {
                                for (int i = 0; i < ticketsNumber; i++) {
                                    BookingModel booking = this.bookingController.createBooking(flight, this.userController.getUserBySessionId(this.sessionId));
                                    this.flightController.updateFlight(flight, -1);
                                    System.out.println("Created booking:\n" + booking);
                                    this.logger.info("User [" + this.user.getUserName() + " " + this.user.getUserSurname() + "] created booking [id=" + booking.getId() + "]");
                                }
                            }
                        } else {
                            try {
                                throw new Exceptions(SEARCH_FALSE);
                            } catch (Exceptions exceptions) {
                                System.out.println(exceptions.getMessage());
                            }
                        }
                    } else {
                        try {
                            throw new Exceptions(INVALID_DATA);
                        } catch (Exceptions exceptions) {
                            System.out.println(exceptions.getMessage());
                        }
                    }
                } else {
                    try {
                        throw new Exceptions(INVALID_DATA);
                    } catch (Exceptions exceptions) {
                        System.out.println(exceptions.getMessage());
                    }
                }
            } else {
                try {
                    throw new Exceptions(INVALID_DATA);
                } catch (Exceptions exceptions) {
                    System.out.println(exceptions.getMessage());
                }
            }
        } else {
            try {
                throw new Exceptions(ERROR_AUTHORIZATION_YOU_ARE_NOT_AUTHORIZED);
            } catch (Exceptions exceptions) {
                System.out.println(exceptions.getMessage());
            }
        }
    }


    /**
     * Cancel booking by [id]
     */
    private void actionDeleteFlightBooking() {
        if (this.sessionId != 0) {
            System.out.println("[0] - EXIT");
            List<BookingModel> listBookings = this.bookingController.getUserBookings(this.user);
            if (listBookings.size() > 0) {
                int[] idArr = this.bookingController.getUserBookings(this.user).stream().mapToInt(BookingModel::getId).toArray();
                int deleteBookingId = inputIntData("Booking [id] must be in " + Arrays.toString(idArr) + "!", "[id] your flight booking");
                if (deleteBookingId > 0) {
                    if (this.bookingController.isBookingExist(deleteBookingId)) {
                        FlightModel updateFlight = this.bookingController.getBookingById(deleteBookingId).getFlight();
                        boolean isBookingDelete = this.bookingController.deleteBookingById(deleteBookingId);
                        if (isBookingDelete) {
                            this.flightController.updateFlight(updateFlight, 1);
                            System.out.println(OPERATION_SUCCESS);
                            this.logger.info("User [" + this.user.getUserName() + " " + this.user.getUserSurname() + "] deleted booking [id=" + deleteBookingId + "]");
                        } else {
                            try {
                                throw new Exceptions(OPERATION_ERROR);
                            } catch (Exceptions exceptions) {
                                System.out.println(exceptions.getMessage());
                            }
                        }
                    } else {
                        try {
                            throw new Exceptions(INVALID_DATA);
                        } catch (Exceptions exceptions) {
                            System.out.println(exceptions.getMessage());
                        }
                    }
                } else {
                    try {
                        throw new Exceptions(BREAK_ACTION);
                    } catch (Exceptions exceptions) {
                        System.out.println(exceptions.getMessage());
                    }
                }
            } else {
                try {
                    throw new Exceptions(SEARCH_FALSE);
                } catch (Exceptions exceptions) {
                    System.out.println(exceptions.getMessage());
                }
            }
        } else {
            try {
                throw new Exceptions(ERROR_AUTHORIZATION_YOU_ARE_NOT_AUTHORIZED);
            } catch (Exceptions exceptions) {
                System.out.println(exceptions.getMessage());
            }
        }
    }

    /**
     * Returns users's bookings (only for authorized users!)
     */
    private void actionGetUserBookings() {
        if (this.sessionId != 0) {
            List<BookingModel> userBookings = this.bookingController.getUserBookings(this.user);
            if (userBookings.size() > 0) {
                printBookingList(userBookings);
                this.logger.info("User [" + this.user.getUserName() + " " + this.user.getUserSurname() + "] got bookings");
            } else {
                try {
                    throw new Exceptions(SEARCH_FALSE);
                } catch (Exceptions exceptions) {
                    System.out.println(exceptions.getMessage());
                }
            }
        } else {
            try {
                throw new Exceptions(ERROR_AUTHORIZATION_YOU_ARE_NOT_AUTHORIZED);
            } catch (Exceptions exceptions) {
                System.out.println(exceptions.getMessage());
            }
        }
    }

    /**
     * Makes Log In/Out for users
     */
    private void actionUserLogInOut() {
        if (this.sessionId == 0) {
            System.out.println("\n[Log in user]");
            String login = inputStringData("", "login");
            if (!login.equals("")) {
                String password = inputStringData("", "password");
                if (!password.equals("")) {
                    UserModel user = this.userController.getUserByLoginAndPassword(login, password);
                    if (user != null) {
                        this.sessionId = user.hashCode();
                        this.user = user;
                        System.out.println(SUCCESSFUL_AUTHORIZATION);
                        System.out.println("Welcone, [" + user.getUserName() + " " + user.getUserSurname() + "] !");
                        this.logger.info("User [" + this.user.getUserName() + " " + this.user.getUserSurname() + " has logged in");
                    } else {
                        try {
                            throw new Exceptions(ERROR_AUTHORIZATION_USER_IS_NOT_FOUND);
                        } catch (Exceptions exceptions) {
                            System.out.println(exceptions.getMessage());
                        }
                        this.logger.info("Unknown user [" + this.user.getUserName() + " " + this.user.getUserSurname() + " tried to log in with fake login data");
                    }
                } else {
                    try {
                        throw new Exceptions(INVALID_DATA);
                    } catch (Exceptions exceptions) {
                        System.out.println(exceptions.getMessage());
                    }
                }
            } else {
                try {
                    throw new Exceptions(INVALID_DATA);
                } catch (Exceptions exceptions) {
                    System.out.println(exceptions.getMessage());
                }
            }
        } else {
            System.out.println("[Log out user]");
            this.sessionId = 0;
            this.user = GUEST;
            System.out.println(ERROR_AUTHORIZATION_YOU_ARE_NOT_AUTHORIZED);
        }
    }

    /**
     * Makes registration of new users
     */
    private void actionRegistration() {
        if (this.sessionId == 0) {
            System.out.println("\n[User registration]");
            String login = inputStringData("", "login");
            UserModel user = this.userController.getUserByLogin(login);
            if (!login.equals("") && user == null) {
                String password = inputStringData("", "password");
                if (!password.equals("")) {
                    String confirmPassword = inputStringData("", "confirm password");
                    if (password.equals(confirmPassword)) {
                        String userName = inputStringData("", "user name");
                        String userSurname = inputStringData("", "user surname");
                        if (!userName.equals("") && !userSurname.equals("")) {
                            this.userController.createUser(login, password, userName, userSurname);
                            System.out.println(SUCCESSFUL_REGISTRATION + " - [" + userName + " " + userSurname + "]");
                            this.logger.info("User [" + userName + " " + userSurname+ " creates account [" + login + ", " + password + "]");
                        }
                    } else {
                        try {
                            throw new Exceptions(ERROR_REGISTRATION_INVALID_INPUT_DATA);
                        } catch (Exceptions exceptions) {
                            System.out.println(exceptions.getMessage());
                        }
                    }
                }
            } else {
                try {
                    throw new Exceptions(ERROR_REGISTRATION_USER_IS_ALREADY_REGISTERED);
                } catch (Exceptions exceptions) {
                    System.out.println(exceptions.getMessage());
                }
            }
        } else {
            try {
                throw new Exceptions(ERROR_AUTHORIZATION_USER_IS_ALREADY_AUTHORIZED);
            } catch (Exceptions exceptions) {
                System.out.println(exceptions.getMessage());
            }
        }
    }

    /**
     * Converts ms to hours
     *
     * @param ms as long
     * @return hour
     */
    private int getHoursFromMilli(long ms) {
        return (int) (ms / 1000 / 60 / 60);
    }

    /**
     * Prints flight collection
     *
     * @param list as ArrayList<>(FlightModel)
     */
    private void printFlightList(List<FlightModel> list) {
        System.out.println("\n\n" + SEARCH_TRUE);
        if (list.size() != 0) {
            list.forEach(System.out::println);
        } else {
            System.out.println("\n\n" + SEARCH_FALSE);
        }
    }

    /**
     * Prints booking collection
     *
     * @param list as ArrayList<>(BookingModel)
     */
    private void printBookingList(List<BookingModel> list) {
        if (list.size() == 0) {
            System.out.println("\n\n" + SEARCH_FALSE);
        } else {
            list.forEach(System.out::println);
        }
    }

    /**
     * Prints specific flight
     *
     * @param object String
     */
    private void printObjectAsString(String object) {
        if (object != null) {
            System.out.println(object);
        } else {
            System.out.println("\n\n" + SEARCH_FALSE);
        }
    }

    /**
     * Checks if the entered [id] is in the array of allowable values [id]
     *
     * @param id    as int
     * @param idArr as int[]
     * @return boolean
     */
    private boolean isInputNumberIsInArrayOfFlightId(int id, int[] idArr) {
        for (int i : idArr) {
            if (i == id) {
                return true;
            }
        }
        return false;
    }

    private boolean isDateValid(String date) {
        return Pattern.compile(DATE_FORMAT_REGEX).matcher(date).matches();
    }

    /**
     * Returns input string
     *
     * @param actionName String
     * @param text       String
     * @return String
     */
    private String inputStringData(String actionName, String text) {
        if (!actionName.equals("")) {
            System.out.println("\n" + actionName);
        }
        System.out.print("Input " + text + ": ");
        String inputData = this.scanner.nextLine();
        if (!inputData.equals("")) {
            if (!inputData.replaceAll("\\s", "").equals("")) {
                return inputData.replaceAll("\\s", "");
            }
        }
        return "";
    }

    /**
     * Returns input number
     *
     * @param actionName String
     * @param text       String
     * @return int
     */
    private int inputIntData(String actionName, String text) {
        if (!actionName.equals("")) {
            System.out.println("\n" + actionName);
        }
        System.out.print("Input " + text + ": ");
        int num = this.scanner.nextInt();
        if (num > 0 && num <= 100) {
            return num;
        }
        return 0;
    }

    /**
     * Returns flight by [id]
     *
     * @param listFlights   List<FlightModel>
     * @param ticketsNumber int
     * @return FlightModel
     */
    private FlightModel selectFlight(List<FlightModel> listFlights, int ticketsNumber) {
        int[] idArr = listFlights.stream().mapToInt(FlightModel::getId).toArray();
        int flightId = inputIntData("\n[4. Flight [id] must be in " + Arrays.toString(idArr) + "!]", "a flight and input [id]");
        if (isInputNumberIsInArrayOfFlightId(flightId, idArr) && this.flightController.isFlightExist(this.flightController.getFlightById(flightId))) {
            return this.flightController.getFlightById(flightId);
        }
        return null;
    }

}