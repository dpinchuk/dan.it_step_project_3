package dao;

import models.BookingModel;
import models.FlightModel;
import models.UserModel;
import utils.Loader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class implements interface BookingDAO
 *
 * @author Pinchuk Dmitry
 */
public class BookingDAOImpl implements BookingDAO {

    private List<BookingModel> bookingList = new ArrayList<>();
    private Loader loader = new Loader();

    public BookingDAOImpl() {
        try {
            this.bookingList = loader.readBookingListFromFile();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Implements int createBooking(FlightModel flight, UserModel user) returns new booking [id]
     *
     * @param flight FlightModel
     * @param user   UserModel
     * @return int
     */
    @Override
    public BookingModel createBooking(FlightModel flight, UserModel user) {
        int bookingId = bookingList.get(bookingList.size() - 1).getId() + 1;
        BookingModel bookingModel = new BookingModel(bookingId, flight, user);
        this.bookingList.add(bookingModel);
        return bookingModel;
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
     * Deletes booking
     * Returns flight [id]
     *
     * @param booking BookingModel
     * @return boolean
     */
    @Override
    public int deleteBooking(BookingModel booking) {
        int id = 0;
        BookingModel boooking = this.bookingList
                .stream()
                .filter(e -> e.equals(booking))
                .findFirst()
                .orElse(null);
        if (boooking != null) {
            id = boooking.getFlight().getId();
            this.bookingList.remove(boooking);
        }
        //.removeIf(b -> b.equals(booking));
        return id;
    }

    /**
     * Implements BookingModel getBookingById(int id) returns booking by [id]
     *
     * @param id int
     * @return BookingModel
     */
    @Override
    public BookingModel getUserBookingById(int id, UserModel user) {
        return this.bookingList
                .stream()
                .filter(e -> e.getUser().equals(user) && e.getId() == id)
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
        return this.bookingList
                .stream()
                .filter(e -> e.getUser().equals(user))
                .collect(Collectors.toList());
    }

    /**
     * Writes Booking List To File
     */
    @Override
    public void writeBookingListToFile() {
        this.loader.writeBookingListToFile(this.bookingList);
    }

}