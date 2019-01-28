package controllers;

import services.BookingServiceImpl;

/**
 * Class controller for managin BookingServiceImpl class
 * @author Pinchuk Dmitry
 */
public class BookingController {

    private BookingServiceImpl bookingService = new BookingServiceImpl();

    /**
     * Getter
     * @return this.bookingService
     */
    public BookingServiceImpl getBookingService() {
        return this.bookingService;
    }

}