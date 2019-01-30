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

    public List<BookingModel> getUserBookings(UserModel user) {
        return this.bookingService.getUserBookings(user);
    }

    public boolean isBookingExist(int bookingId) {
        return this.bookingService.isBookingExist(bookingId);
    }

    public BookingModel createBooking(FlightModel flight, UserModel user) {
        return this.bookingService.createBooking(flight, user);
    }

    public BookingModel getBookingById(int id) {
        return this.bookingService.getBookingById(id);
    }

    public boolean deleteBookingById(int id) {
        return this.bookingService.deleteBookingById(id);
    }
}