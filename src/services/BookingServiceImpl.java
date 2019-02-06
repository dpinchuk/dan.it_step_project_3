package services;

import dao.BookingDAOImpl;
import models.BookingModel;
import models.FlightModel;
import models.UserModel;

import java.util.ArrayList;
import java.util.List;

import static utils.Constants.*;

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
     * Returns all user bookings [user must be authorized]
     *
     * @param user      UserModel
     * @param sessionId int
     * @return List<BookingModel>
     */
    @Override
    public List<BookingModel> getUserBookings(UserModel user, int sessionId) {
        if (sessionId == 0) {
            getException(ERROR_AUTHORIZATION_YOU_ARE_NOT_AUTHORIZED, "User [" + user.getUserName() + " " + user.getUserSurname() + "] selected [actionDeleteFlightBooking]");
            return new ArrayList<>();
        }
        List<BookingModel> bookingList = this.bookingDAO.getUserBookings(user);
        if (bookingList.size() != 0) {
            return this.bookingDAO.getUserBookings(user);
        } else {
            getException(SEARCH_FALSE, "User [" + user.getUserName() + " " + user.getUserSurname() + "] could not view bookings");
            return new ArrayList<>();
        }
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
     * @param id   int
     * @param user UserModel
     * @return BookingModel
     */
    @Override
    public BookingModel getBookingById(int id, UserModel user) {
        BookingModel booking;
        if (id > 0) {
            booking = this.bookingDAO.getBookingById(id);
            if (booking != null) {
                return booking;
            } else {
                getException(SEARCH_FALSE, "User [" + user.getUserName() + " " + user.getUserSurname() + "] did not find booking by [id=" + id + "]");
                return null;
            }
        }
        getException(INVALID_DATA, "User [" + user.getUserName() + " " + user.getUserSurname() + "] entered incorrect [id=" + id + "]");
        return null;
    }

    /**
     * Delete booking by [id]
     * Returns flight [id]
     *
     * @param id int
     * @return int
     */
    @Override
    public int deleteBookingById(String id, UserModel user) {
        BookingModel booking;
        if (!id.equals("")) {
            int idNum = -1;
            try {
                idNum = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                getException(INVALID_DATA, "User [" + user.getUserName() + " " + user.getUserSurname() + "] entered incorrect [id=" + id + "]");
                return 0;
            }
            if (idNum > 0) {
                booking = this.bookingDAO.getBookingById(idNum);
                if (booking == null) {
                    getException(INVALID_DATA, "User [" + user.getUserName() + " " + user.getUserSurname() + "] did not delete booking by [id=" + id + "]");
                    return 0;
                } else {
                    return this.bookingDAO.deleteBooking(booking);
                }
            } else if (idNum == 0) {
                getException(BREAK_ACTION, "User [" + user.getUserName() + " " + user.getUserSurname() + "] canceled the action [DELETE Booking]");
                return 0;
            } else {
                getException(INVALID_DATA, "User [" + user.getUserName() + " " + user.getUserSurname() + "] did not delete booking by [id=" + id + "]");
                return 0;
            }
        } else {
            getException(INVALID_DATA, "User [" + user.getUserName() + " " + user.getUserSurname() + "] did not delete booking by [id=" + id + "]");
            return 0;
        }
    }

    /**
     * Writes booking List to file
     * void
     */
    @Override
    public void writeBookingListToFile() {
        this.bookingDAO.writeBookingListToFile();
    }

}