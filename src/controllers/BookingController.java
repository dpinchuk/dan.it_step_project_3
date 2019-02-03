package controllers;

import models.BookingModel;
import models.FlightModel;
import models.UserModel;
import services.BookingServiceImpl;

import java.util.List;

/**
 * Class controller for managin BookingServiceImpl class
 *
 * @author Pinchuk Dmitry
 */
public class BookingController {

    private BookingServiceImpl bookingService = new BookingServiceImpl();

    /**
     * Returns users's bookings
     *
     * @param user UserModel
     * @return List<BookingModel>
     */
    public List<BookingModel> getUserBookings(UserModel user) {
        return this.bookingService.getUserBookings(user);
    }

    /**
     * Checks for booking existing
     *
     * @param bookingId int
     * @return boolean
     */
    public boolean isBookingExist(int bookingId) {
        return this.bookingService.isBookingExist(bookingId);
    }

    /**
     * Returns booking by [flight] and [user]
     *
     * @param flight FlightModel
     * @param user   UserModel
     * @return BookingModel
     */
    public BookingModel createBooking(FlightModel flight, UserModel user) {
        return this.bookingService.createBooking(flight, user);
    }

    /**
     * Returns booking by [id]
     *
     * @param id int
     * @return BookingModel
     */
    public BookingModel getBookingById(int id) {
        return this.bookingService.getBookingById(id);
    }

    /**
     * Deleting booking by [id]
     *
     * @param id int
     * @return boolean
     */
    public boolean deleteBookingById(int id) {
        return this.bookingService.deleteBookingById(id);
    }

    /**
     * Writes booking List to file
     */
    public void writeBookingListToFile() {
        this.bookingService.writeBookingListToFile();
    }

}