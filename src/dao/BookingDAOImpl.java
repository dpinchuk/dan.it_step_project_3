package dao;

import models.BookingModel;
import models.FlightModel;
import models.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class implements interface BookingDAO
 *
 * @author Pinchuk Dmitry
 */
public class BookingDAOImpl implements BookingDAO {

    private List<BookingModel> bookingList = new ArrayList<>();
    private int bookingId = 0;

    /**
     * Implements int createBooking(FlightModel flight, UserModel user) returns new booking [id]
     *
     * @param flight FlightModel
     * @param user   UserModel
     * @return int
     */
    @Override
    public int createBooking(FlightModel flight, UserModel user) {
        BookingModel bookingModel = new BookingModel(++bookingId, flight, user);
        this.bookingList.add(bookingModel);
        return bookingModel.getId();
    }

    /**
     * Implements boolean deleteBookingById(int id) returns result as true/false
     *
     * @param id int
     * @return boolean
     */
    @Override
    public boolean deleteBookingById(int id) {
        return this.bookingList.removeIf(b -> b.getId() == id);
    }

    /**
     * Implements BookingModel getBookingById(int id) returns booking by [id]
     *
     * @param id int
     * @return BookingModel
     */
    @Override
    public BookingModel getBookingById(int id) {
        return this.bookingList
                .stream()
                .filter(e ->
                        e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Implements List<BookingModel> getUserBookings(UserModel user) returns user's bookings by [user]
     *
     * @param user UserModel
     * @return List<BookingModel>
     */
    @Override
    public List<BookingModel> getUserBookings(UserModel user) {
        return this.getBookingModelList()
                .stream()
                .filter(e -> e.getUser().equals(user))
                .collect(Collectors.toList());
    }

    /**
     * Implements List<BookingModel> getUserBookings(UserModel user) and returns user's bookings by [sessionId]
     *
     * @param sessionId int
     * @return List<BookingModel>
     */
    @Override
    public List<BookingModel> getUserBookings(int sessionId) {
        return this.getBookingModelList()
                .stream()
                .filter(e -> e.hashCode() == sessionId)
                .collect(Collectors.toList());
    }

    /**
     * Getter
     *
     * @return bookingList
     */
    public List<BookingModel> getBookingModelList() {
        return this.bookingList;
    }

}