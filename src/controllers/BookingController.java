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
    public boolean isBookingExist(int bookingId, UserModel user) {
        return this.bookingService.isBookingExist(bookingId, user);
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
    public BookingModel getBookingById(int id, UserModel user) {
        return this.bookingService.getBookingById(id, user);
    }

    /**
     * Deleting booking by [id]
     * Returns flight [id]
     *
     * @param id   int
     * @param user UserModel
     * @return int
     */
    public int deleteBookingById(String id, UserModel user) {
        return this.bookingService.deleteBookingById(id, user);
    }

    /**
     * Writes booking List to file
     */
    public void writeBookingListToFile() {
        this.bookingService.writeBookingListToFile();
    }

}