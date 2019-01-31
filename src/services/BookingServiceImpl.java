package services;

import dao.BookingDAOImpl;
import models.BookingModel;
import models.FlightModel;
import models.UserModel;

import java.util.List;

/**
 * Service class extends MainService implements BookingService
 *
 * @author Pinchuk Dmitry
 */
public class BookingServiceImpl extends MainService implements BookingService {

    private BookingDAOImpl bookingDAO = new BookingDAOImpl();

    /**
     * Returns info about booking by [id]
     *
     * @param id int
     * @return String
     */
    @Override
    public String getBookingInfo(int id) {
        if (isValidNumber(id)) {
            return this.bookingDAO.getBookingById(id).toString();
        }
        return "";
    }

    /**
     * Returns true/false if booking exist
     *
     * @param id int
     * @return boolean
     */
    @Override
    public boolean isBookingExist(int id) {
        if (isValidNumber(id)) {
            return this.bookingDAO.getBookingById(id) != null;
        }
        return false;
    }

    /**
     * Returns all bookings [Special method!!!]
     *
     * @return List<BookingModel>
     */
    public List<BookingModel> getBookingModelList() {
        return this.bookingDAO.getBookingModelList();
    }

    /**
     * Returns all user bookings [user must be authorized]
     *
     * @param user UserModel
     * @return List<BookingModel>
     */
    public List<BookingModel> getUserBookings(UserModel user) {
        return this.bookingDAO.getUserBookings(user);
    }

    /**
     * Creates new booking
     *
     * @param flight FlightModel
     * @param user   UserModel
     * @return int
     */
    public BookingModel createBooking(FlightModel flight, UserModel user) {
        if (flight != null && user != null) {
            return this.bookingDAO.createBooking(flight, user);
        }
        return null;
    }

    /**
     * Returns booking by [id]
     *
     * @param id int
     * @return BookingModel
     */
    public BookingModel getBookingById(int id) {
        return this.bookingDAO.getBookingById(id);
    }

    /**
     * Delete booking by [id]
     *
     * @param id int
     * @return boolean
     */
    public boolean deleteBookingById(int id) {
        return this.bookingDAO.deleteBookingById(id);
    }

    @Override
    public void writeBookingListToFile() {
        this.bookingDAO.writeBookingListToFile();
    }

}