package controllers;

import models.BookingModel;
import models.FlightModel;
import models.UserModel;
import services.BookingServiceImpl;

import java.util.List;

/**
 * Class controller for managin BookingServiceImpl class
 * @author Pinchuk Dmitry
 */
public class BookingController {

    private BookingServiceImpl bookingService = new BookingServiceImpl();

    public List<BookingModel> getBookingModelList() {
        return this.bookingService.getBookingModelList();
    }

    public List<BookingModel> getUserBookings(int sessionId) {
        return this.bookingService.getUserBookings(sessionId);
    }

    public boolean isBookingExist(int bookingId) {
        return this.bookingService.isBookingExist(bookingId);
    }

    public int createBooking(FlightModel flight, UserModel user) {
        return this.bookingService.createBooking(flight, user);
    }

    public BookingModel getBookingById(int id) {
        return this.bookingService.getBookingById(id);
    }

    public boolean deleteBookingById(int id) {
        return this.bookingService.deleteBookingById(id);
    }
}