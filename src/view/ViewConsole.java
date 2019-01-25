package view;

import controllers.BookingController;
import controllers.FlightController;
import controllers.UserController;
import models.FlightModel;
import models.UserModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static utils.Constants.*;

public class ViewConsole {

    private FlightController flightController = new FlightController();
    private BookingController bookingController = new BookingController();
    private UserController userController = new UserController();
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void run() {
        List<FlightModel> flightList = this.flightController.getFlightServiceImpl().getFlightList();
        List<UserModel> userList = this.userController.getUserList();
        String selectConsole = "";
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("[Service of a flight booking]");
            System.out.println("Choose action: ");
            System.out.println("[0] - Exit");
            System.out.println("[1] - Online scoreboard");
            System.out.println("[2] - View flight information");
            System.out.println("[3] - Flight search and booking");
            System.out.println("[4] - Cancel booking");
            System.out.println("[5] - My flights");
            try {
                System.out.print("\nChoose your action: ");
                selectConsole = this.reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (selectConsole.equals(EXIT)) {
                break;
            } else {
                select(selectConsole);
            }
        }
    }

    private void select(String select) {
        switch (select) {
            case "1":
                actionFlightsInfoNextOurs();
                break;
            case "2":
                actionFlightViewById();
                break;
            case "3":
                actionSearchFlightByDataAndBooking();
                break;
            case "4":
                actionCancelFlightBooking();
                break;
            case "5":
                //TODO
                break;
            case "6":
                //TODO
                break;
            default:
                break;
        }
        System.out.println("\n\n\n\n");
    }

    private String getDateInFormat(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    private String getTimeInFormat(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern(TIME_PATTERN));
    }

    private int getHoursFromMilli(long ms) {
        return (int) (ms / 1000 / 60 / 60);
    }

    private void printFlightList(List<FlightModel> list) {
        if (list.size() == 0) {
            System.out.println("\n\n" + SEARCH_NOTHING);
        } else {
            list.forEach(e -> System.out.println("\n\n" +
                    e.getFlightId() + "\t\t" +
                    e.getDate() + "\t\t" +
                    e.getTime() + "\t\t" +
                    e.getDispatchLocation() + "\t\t" +
                    e.getDestination() + "\t\t" +
                    e.getSeatsNumber() + "\t\t" +
                    e.getOccupiedPlaces()
            ));
        }
    }

    private void printFlight(FlightModel flight) {
        if (flight == null) {
            System.out.println("\n\n" + SEARCH_NOTHING);
        } else {
            System.out.println("\n\n" +
                    flight.getFlightId() + "\t\t" +
                    flight.getDate() + "\t\t" +
                    flight.getTime() + "\t\t" +
                    flight.getDispatchLocation() + "\t\t" +
                    flight.getDestination() + "\t\t" +
                    flight.getSeatsNumber() + "\t\t" +
                    flight.getOccupiedPlaces()
            );
        }
    }

    private int getId(String str) {
        int id = 0;
        try {
            id = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println("\n\n" + SEARCH_NOTHING);
        }
        if (id >= 0 && id < this.flightController.getFlightServiceImpl().getFlightList().size()) {
            return id;
        }
        System.out.println(INVALID_DATA);
        System.out.println("id must be between [1] and [" + this.flightController.getFlightServiceImpl().getFlightList().size() + "]!");
        return 0;
    }

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

    private boolean isInputNumberIsInArrayOfFlightId(int id, int[] idArr) {
        for (int i : idArr) {
            if (i == id) {
                return true;
            }
        }
        return false;
    }

    private void actionFlightsInfoNextOurs() {
        System.out.println("\n\n[Information on all flights from Kiev in the next {" + getHoursFromMilli(DIFFERENCE) + "} hours]");
        printFlightList(this.flightController.getFlightServiceImpl().getFlightsListNextHours(DIFFERENCE));
    }

    private void actionFlightViewById() {
        System.out.println("\n\n[View flight information]");
        System.out.println("Flight [id] must be between [1] and [" + this.flightController.getFlightServiceImpl().getFlightList().size() + "]!");
        String id = "";
        System.out.print("Input Flight [id]: ");
        try {
            id = this.reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (getId(id) > 0) {
            printFlight(this.flightController.getFlightServiceImpl().getFlightInfo(getId(id)));
        }
    }

    private void actionSearchFlightByDataAndBooking() {
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

                    List<FlightModel> listFlights = this.flightController.getFlightServiceImpl().searchFlights(destination, date, getNumberFromString(ticketsNumber));
                    printFlightList(listFlights);

                    if (listFlights.size() > 0) {
                        int flightIdSelect = 0;
                        int[] idArr = listFlights.stream().mapToInt(FlightModel::getFlightId).toArray();
                        System.out.println("\n\n4. Flight [id] must be in " + Arrays.toString(idArr) + "!");
                        System.out.print("Choose a flight and input [id]: ");
                        try {
                            String str = this.reader.readLine();
                            if (str.equals(EXIT)) {
                                //TODO Exception!
                                System.out.println(BREAK_ACTION);
                            } else {
                                flightIdSelect = getId(str);
                                if (isInputNumberIsInArrayOfFlightId(flightIdSelect, idArr)) {
                                    int booking = bookingFlight(new int[]{flightIdSelect, Integer.parseInt(ticketsNumber)});
                                    if (booking != 0) {
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
    }

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
        int booking = this.bookingController.getBookingService().createBooking(flightId, passengerName, passengerSurname, seatsNumber);
        if (booking != 0) {
            return booking;
        }
        return 0;
    }

    private void actionCancelFlightBooking() {
        int bookingId = 0;
        System.out.print("\nInput [id] your flight booking: ");
        try {
            String data = this.reader.readLine();
            bookingId = getNumberFromString(data);
        } catch (IOException e) {
        }
        if(this.bookingController.getBookingService().deleteBooking(bookingId)) {
            System.out.println(OPERATION_SUCCESS + " [" + bookingId + "]");
        } else {
            System.out.println(OPERATION_ERROR);
        }
    }

}