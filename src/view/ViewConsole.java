package view;

import controllers.BookingController;
import controllers.FlightController;
import controllers.UserController;
import models.BookingModel;
import models.FlightModel;
import models.UserModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

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
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private int sessionId = 0;

    /**
     * Main action list
     */
    public void run() {
        String selectConsole = "";
        while (true) {
            System.out.println("[Service of a flight booking]");
            System.out.println("Choose action: ");
            System.out.println("[0] - Exit");
            System.out.println("[1] - Online scoreboard");
            System.out.println("[2] - View flight information");
            System.out.println("[3] - Search flight by data and booking" + (this.sessionId != 0 ? " [Session id: " + this.sessionId + "]" : " [Not authorized]"));
            System.out.println("[4] - Cancel booking" + (this.sessionId != 0 ? " [Session id: " + this.sessionId + "]" : " [Not authorized]"));
            System.out.println("[5] - Show user flights " + (this.sessionId != 0 ? " [Session id: " + this.sessionId + "]" : " [Not authorized]"));
            System.out.println("[6] - " + (this.sessionId == 0 ? "Log in [Not authorized]" : "Log out [Session id: " + this.sessionId + "]"));
            try {
                System.out.print("\nChoose your action: ");
                selectConsole = this.reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (selectConsole.equals(EXIT)) {
                System.out.println("Thank you for using our service!");
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
                actionSearchFlightByDataAndBooking();
                break;
            case "4":
                actionDeleteFlightBooking();
                break;
            case "5":
                actionGetUserFlights();
                break;
            case "6":
                actionUserLogInOut();
                break;
            default:
                System.out.println("\nUnknown action!");
        }
        System.out.println("\n\n\n\n");
    }

    /**
     * Shows all flights next Time
     */
    private void actionOnlineScoreboard() {
        System.out.println("\n\n[Information on all flights from Kiev in the next {" + getHoursFromMilli(DIFFERENCE) + "} hours]");
        List<FlightModel> flightsListNextHours = this.flightController.getFlightsListNextHours(DIFFERENCE);
        printFlightList(flightsListNextHours);
    }

    /**
     * Shows flight info by [id]
     */
    private void actionViewFlightInfo() {
        System.out.println("\n\n[View flight information]");
        System.out.println("Flight [id] must be between [1] and [" + this.flightController.getFlightListSize() + "]!");
        String id = "";
        System.out.print("Input Flight [id]: ");
        try {
            id = this.reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (convertId(id) > 0) {
            printObject(this.flightController.getFlightInfo(convertId(id)));
        }
    }

    /**
     * Shows flight by [id] and makes booking
     */
    private void actionSearchFlightByDataAndBooking() {
        if (this.sessionId != 0) {
            System.out.println("\n\n[Flight search and booking]");
            String destination = "";
            String date = "";
            String ticketsNumber = "";
            System.out.println("1. Destination must be consist of letters and case does not matter [Kiev, berlin, MADRID]!");
            System.out.print("Input destination: ");
            try {
                destination = this.reader.readLine();
                System.out.println("\n2. Date must be in a format [YYYY-MM-DD]!");
                System.out.print("Input date: ");
                try {
                    date = this.reader.readLine();
                    System.out.println("\n3. Number of tickets must be less than 100!");
                    System.out.print("Input number of tickets: ");
                    try {
                        ticketsNumber = this.reader.readLine();
                        List<FlightModel> listFlights = this.flightController.getFlightByData(destination, date, getNumberFromString(ticketsNumber));
                        printFlightList(listFlights);
                        if (listFlights.size() > 0) {
                            int flightIdSelect = 0;
                            int[] idArr = listFlights.stream().mapToInt(FlightModel::getId).toArray();
                            System.out.println("\n\n4. Flight [id] must be in " + Arrays.toString(idArr) + "!");
                            System.out.print("Choose a flight and input [id]: ");
                            try {
                                String str = this.reader.readLine();
                                if (str.equals(EXIT)) {
                                    //TODO Exception!
                                    System.out.println(BREAK_ACTION);
                                } else {
                                    flightIdSelect = convertId(str);
                                    if (isInputNumberIsInArrayOfFlightId(flightIdSelect, idArr)) {
                                        int[] updateFlightData = {flightIdSelect, Integer.parseInt(ticketsNumber)};
                                        int booking = bookingFlight(updateFlightData);
                                        if (booking > 0) {
                                            this.flightController.updateFlightOccupiedPlaces(updateFlightData);
                                            System.out.println(OPERATION_SUCCESS + " [" + booking + "]");
                                        } else {
                                            System.out.println(OPERATION_ERROR);
                                        }
                                    } else {
                                        //TODO Exception!
                                        System.out.println(INVALID_DATA);
                                    }
                                }
                            } catch (IOException e) {
                                //TODO Exception!
                                System.out.println(INVALID_DATA);
                            }
                        }
                    } catch (IOException e) {
                        //TODO Exception!
                        System.out.println(INVALID_DATA);
                    }
                } catch (IOException | IllegalArgumentException e) {
                    //TODO Exception!
                    System.out.println(INVALID_DATA);
                }
            } catch (IOException e) {
                //TODO Exception!
                System.out.println(INVALID_DATA);
            }
        } else {
            System.out.println(ERROR_AUTHORIZATION_YOU_ARE_NOT_AUTHORIZED);
        }
    }

    /**
     * Cancel booking by [id]
     */
    private void actionDeleteFlightBooking() {
        if (this.sessionId != 0) {
            int bookingId = 0;
            System.out.println("[0] - EXIT");
            List<BookingModel> listBookings = this.bookingController.getBookingService().getBookingDAO().getBookingModelList();
            if (listBookings.size() > 0) {
                int[] idArr = listBookings.stream().mapToInt(BookingModel::getId).toArray();
                System.out.println("\n\n4. Booking [id] must be in " + Arrays.toString(idArr) + "!");
                System.out.print("\nInput [id] your flight booking: ");
                try {
                    String data = this.reader.readLine();
                    bookingId = getNumberFromString(data);
                    if (this.bookingController.getBookingService().isBookingExist(bookingId)) {
                        int flightId = this.bookingController.getBookingService().getBookingDAO().getBookingById(bookingId).getFlightId();
                        int flightPlaces = -this.bookingController.getBookingService().getBookingDAO().getBookingById(bookingId).getSeatsRemaining();
                        if (this.bookingController.getBookingService().getBookingDAO().deleteBookingById(bookingId)) {
                            int[] updateFlightData = {flightId, flightPlaces};
                            this.flightController.updateFlightOccupiedPlaces(updateFlightData);
                            System.out.println(OPERATION_SUCCESS + " [" + bookingId + "]");
                        } else {
                            System.out.println(OPERATION_ERROR);
                        }
                    }
                } catch (IOException e) {
                    System.out.println(INVALID_DATA);
                }
            } else {
                System.out.println(INVALID_DATA);
            }
        } else {
            System.out.println(ERROR_AUTHORIZATION_YOU_ARE_NOT_AUTHORIZED);
        }
    }

    private void actionGetUserFlights() {
        if (this.sessionId != 0) {
            List<BookingModel> userBookings = this.bookingController.getBookingService().getBookingDAO().getUserBookings(this.sessionId);
            if (userBookings.size() > 0) {
                printBookingList(userBookings);
            } else {
                System.out.println(SEARCH_NOTHING);
            }
        } else {
            System.out.println(ERROR_AUTHORIZATION_YOU_ARE_NOT_AUTHORIZED);
        }

    }

    /**
     * Makes Log In/Out for users
     */
    private void actionUserLogInOut() {
        if (this.sessionId == 0) {
            List<UserModel> listUsers = this.userController.getUserService().getUserDAO().getUserList();
            if (listUsers.size() > 0) {
                String[] userLoginAndPassword = {"", ""};
                try {
                    System.out.println("\n\n[Log in user]");
                    System.out.print("Input login: ");
                    userLoginAndPassword[0] = this.reader.readLine();
                    userLoginAndPassword[0] = checkUserData(userLoginAndPassword[0]);
                    System.out.print("Input password: ");
                    userLoginAndPassword[1] = this.reader.readLine();
                    userLoginAndPassword[1] = checkUserData(userLoginAndPassword[1]);
                    UserModel user = this.userController.getUserService().getUserDAO().getUserByLoginAndPassword(userLoginAndPassword);
                    if (user != null) {
                        this.sessionId = user.hashCode();
                    } else {
                        System.out.println(ERROR_AUTHORIZATION_USER_IS_NOT_FOUND);
                    }
                } catch (IOException e) {
                    System.out.println(INVALID_DATA);
                }
            } else {
                System.out.println(INVALID_DATA);
            }
        } else {
            System.out.println("[Log out user]");
            this.sessionId = 0;
            System.out.println(ERROR_AUTHORIZATION_YOU_ARE_NOT_AUTHORIZED);
        }
    }

    /**
     * Makes booking
     *
     * @param dataForBooking as int[]
     * @return booking [id]
     */
    private int bookingFlight(int[] dataForBooking) {
        int flightId = dataForBooking[0];
        String passengerName = "";
        String passengerSurname = "";
        int seatsNumber = dataForBooking[1];
        System.out.print("\nInput your name: ");
        try {
            passengerName = this.reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("Input your surname: ");
        try {
            passengerSurname = this.reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int booking = this.bookingController.getBookingService().getBookingDAO().createBooking(flightId, passengerName, passengerSurname, seatsNumber, this.sessionId);
        if (booking > 0) {
            return booking;
        }
        return 0;
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
        if (list.size() != 0) {
            list.forEach(System.out::println);
        } else {
            System.out.println("\n\n" + SEARCH_NOTHING);
        }
    }

    /**
     * Prints booking collection
     *
     * @param list as ArrayList<>(BookingModel)
     */
    private void printBookingList(List<BookingModel> list) {
        if (list.size() == 0) {
            System.out.println("\n\n" + SEARCH_NOTHING);
        } else {
            list.forEach(System.out::println);
        }
    }

    /**
     * Prints specific flight
     *
     * @param object as String
     */
    private void printObject(String object) {
        if (object != null) {
            System.out.println(object);
        } else {
            System.out.println("\n\n" + SEARCH_NOTHING);
        }
    }

    /**
     * Checks [id] boundary and converts it from string to a number
     *
     * @param str as String
     * @return number
     */
    private int convertId(String str) {
        int id = 0;
        try {
            id = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println("\n\n" + SEARCH_NOTHING);
        }
        int boundary = this.flightController.getFlightListSize();
        if (id >= 0 && id < boundary) {
            return id;
        }
        System.out.println(INVALID_DATA);
        System.out.println("id must be between [1] and [" + boundary + "]!");
        return 0;
    }

    /**
     * Converts a string to a number
     *
     * @param str as String
     * @return number
     */
    private int getNumberFromString(String str) {
        int num = 0;
        try {
            num = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println(INVALID_DATA);
        }
        if (num > 0 && num <= 100) {
            return num;
        }
        System.out.println(INVALID_DATA);
        return 0;
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

    private String checkUserData(String data) {
        //TODO
        return data.replaceAll("\\s+", "");
    }

}


///**
// DateTimeFormatter dfs = new DateTimeFormatterBuilder()
// .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
// .appendOptional(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
// .toFormatter();
// LocalDate d = LocalDate.parse("2014-05-14", dfs); //2014-05-14
// LocalDate d2 = LocalDate.parse("14.05.2014", dfs); //2014-05-14