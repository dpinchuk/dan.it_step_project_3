package view;

import controllers.BookingController;
import controllers.FlightController;
import controllers.UserController;
import models.BookingModel;
import models.FlightModel;
import models.UserModel;
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
                actionCancelBooking();
                break;
            case "5":
                actionGetUserBookings();
                break;
            case "6":
                actionUserLogInOut();
                break;
            case "7":
                if (this.sessionId == 0) {
                    actionRegistration();
                } else {
                    System.out.println("Unknown action!");
                }
                break;
            default:
                System.out.println("Unknown action!");
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
        String id = inputStringData("", "");
        String flightInfo = this.flightController.getFlightInfo(id, this.user);
        printString(flightInfo);
    }

    /**
     * Search flight and create booking
     */
    private void actionSearchFlightAndCreateBooking() {
        if (this.sessionId != 0) {
            System.out.println("\n\n[Flight search and booking]");
            String destination = inputStringData("[1. Destination must be consist of letters and case does not matter [Kiev, berlin, MADRID]!]", "destination");
            String date = inputStringData("[2. Date must be in a format [YYYY-MM-DD]!]", "date");
            while (!isDateValid(date)) {
                if (date.equals(EXIT)) {
                    System.out.println(BREAK_ACTION);
                    break;
                }
                System.out.println(INVALID_DATA + " Repeat to enter date:");
                date = inputStringData("[2. Date must be in a format [YYYY-MM-DD]!]", "date");
            }
            if (isDateValid(date)) {
                String ticketsNumber = inputStringData("[3. Number of tickets must be an integer!]", "number of tickets");
                try {
                    int tickets = Integer.parseInt(ticketsNumber);
                    List<FlightModel> flightList = this.flightController.getFlightByData(destination, date, tickets, this.user);
                    if (flightList.size() > 0) {
                        printFlightList(flightList);
                        createBooking(flightList, tickets);
                    } else {
                        System.out.println(SEARCH_FALSE + " Please, change search parameters [destination, date, number of tickets]");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(INVALID_DATA);
                    this.logger.info("User [" + this.user.getUserName() + " " + this.user.getUserSurname() + "] entered invalid number of tickets");
                }
            }
        } else {
            System.out.println(ERROR_AUTHORIZATION_YOU_ARE_NOT_AUTHORIZED);
        }
    }

    /**
     * Create new booking
     *
     * @param flightList List<FlightModel>
     * @param tickets    int
     */
    private void createBooking(List<FlightModel> flightList, int tickets) {
        int[] idArr = flightList.stream().mapToInt(FlightModel::getId).toArray();
        String flightId = inputStringData("\n[4. Flight [id] must be in " + Arrays.toString(idArr) + "!]", "a flight and input [id]");
        int id = 0;
        try {
            id = Integer.parseInt(flightId);
            if (isInputNumberIsInArrayOfFlightId(id, idArr) && this.flightController.isFlightExist(this.flightController.getFlightById(id))) {
                FlightModel flight = this.flightController.getFlightById(id);
                for (int i = 0; i < tickets; i++) {
                    BookingModel booking = this.bookingController.createBooking(flight, this.user);
                    if (booking != null) {
                        this.flightController.updateFlight(flight, -1);
                        System.out.println("Created booking:\n" + booking);
                        this.logger.info("User [" + this.user.getUserName() + " " + this.user.getUserSurname() + "] created booking [id=" + booking.getId() + "]");
                    } else {
                        System.out.println(OPERATION_ERROR);
                        this.logger.error("User [" + this.user.getUserName() + " " + this.user.getUserSurname() + "] didn't create booking");
                    }
                }
            } else {
                System.out.println(OPERATION_ERROR);
                this.logger.error("User [" + this.user.getUserName() + " " + this.user.getUserSurname() + "] didn't create booking");
            }
        } catch (NumberFormatException e) {
            System.out.println(INVALID_DATA);
        }
    }

    /**
     * Cancel booking by [id]
     */
    private void actionCancelBooking() {
        System.out.println("[0] - EXIT");
        List<BookingModel> bookingList = this.bookingController.getUserBookings(this.user, this.sessionId);
        if (bookingList.size() > 0) {
            int[] idArr = bookingList.stream().mapToInt(BookingModel::getId).toArray();
            String bookingDel = inputStringData("Booking [id] must be in " + Arrays.toString(idArr) + "!", "[id] your flight booking");
            int flightId = this.bookingController.deleteBookingById(bookingDel, this.user);
            if (flightId != 0) {
                FlightModel updateFlight = this.flightController.getFlightById(flightId);
                this.flightController.updateFlight(updateFlight, 1);
                System.out.println(OPERATION_SUCCESS);
                this.logger.info("User [" + this.user.getUserName() + " " + this.user.getUserSurname() + "] deleted booking [id=" + bookingDel + "]");
            }
        } else {
            System.out.println(ERROR_AUTHORIZATION_YOU_ARE_NOT_AUTHORIZED);
        }
    }

    /**
     * Returns users's bookings (only for authorized users!)
     */
    private void actionGetUserBookings() {
        List<BookingModel> userBookings = this.bookingController.getUserBookings(this.user, this.sessionId);
        if (userBookings.size() > 0) {
            printBookingList(userBookings);
            this.logger.info("User [" + this.user.getUserName() + " " + this.user.getUserSurname() + "] viewed bookings");
        }
    }

    /**
     * Makes Log In/Out for users
     */
    private void actionUserLogInOut() {
        if (this.sessionId == 0) {
            System.out.println("\n[Log in user]");
            String login = inputStringData("", "login");
            String password = inputStringData("", "password");
            UserModel user = this.userController.getUserByLoginAndPassword(login, password);
            if (user != null) {
                this.sessionId = user.hashCode();
                this.user = user;
                System.out.println(SUCCESSFUL_AUTHORIZATION);
                System.out.println("Welcone, [" + user.getUserName() + " " + user.getUserSurname() + "] !");
                this.logger.info("User [" + this.user.getUserName() + " " + this.user.getUserSurname() + " has logged in");
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
            String password = inputStringData("", "password");
            String confirmPassword = inputStringData("", "confirm password");
            if (!login.equals("") && !password.equals("") && password.equals(confirmPassword)) {
                String userName = inputStringData("", "user name");
                String userSurname = inputStringData("", "user surname");
                boolean isUserCreated = this.userController.createUser(login, password, userName, userSurname);
                if (isUserCreated) {
                    System.out.println(OPERATION_SUCCESS);
                    this.logger.info("Created new user [login: " + login + ", password: " + password + ", username: " + userName + ", userSurname: " + userSurname + "]");
                } else {
                    System.out.println(OPERATION_ERROR);
                }
            } else {
                System.out.println(ERROR_REGISTRATION_INVALID_INPUT_DATA);
            }
        } else {
            System.out.println(ERROR_AUTHORIZATION_USER_IS_ALREADY_AUTHORIZED);
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
     * @param string String
     */
    private void printString(String string) {
        if (!string.equals("")) {
            System.out.println(string);
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
     * Checks for valid date
     *
     * @param date String
     * @return boolean
     */
    private boolean isDateValid(String date) {
        return Pattern.compile(DATE_FORMAT_REGEX).matcher(date).matches();
    }

}