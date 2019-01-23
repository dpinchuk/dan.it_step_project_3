package controllers;

import services.BookingService;
import services.FlightService;
import utils.Loader;

import static utils.Constants.*;

public class InitDataController {

    private FlightService flightService = new FlightService();
    private BookingService bookingService = new BookingService();

    {
        Loader loader = new Loader(DATA_FILE_FLIGHTS_FROM, DATA_FILE_FLIGHTS_TO);
    }

    public FlightService getFlightService() {
        return flightService;
    }

    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }

    public BookingService getBookingService() {
        return bookingService;
    }

    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }
}