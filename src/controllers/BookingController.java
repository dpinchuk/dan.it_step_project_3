package controllers;

import services.BookingServiceImpl;

public class BookingController {

    private BookingServiceImpl bookingService = new BookingServiceImpl();

    public BookingServiceImpl getBookingService() {
        return bookingService;
    }
}