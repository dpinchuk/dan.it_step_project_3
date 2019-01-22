package dao;

import models.BookingModel;
import models.FlightModel;

import java.util.List;

public class BookingDAOImpl implements BookingDAO {
    @Override
    public BookingModel createBooking(FlightModel flight, String name, String surname) {
        return null;
    }

    @Override
    public boolean deleteBooking(int id) {
        return false;
    }

    @Override
    public boolean deleteBooking(BookingModel booking) {
        return false;
    }

    @Override
    public List<BookingModel> getAllBookings() {
        return null;
    }

    @Override
    public List<BookingModel> getBookings(int bookingId) {
        return null;
    }

    @Override
    public List<BookingModel> getUserBookings(String name, String surname) {
        return null;
    }
}