package dao;

import models.BookingModel;
import models.FlightModel;

import java.util.List;
import java.util.stream.Collectors;

public class BookingDAOImpl implements BookingDAO {

    private List<String> bookingList;

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
    public List<String> getAllBookings() {
        return null;
    }

    @Override
    public List<String> getBookings(int bookingId) {
        return null;
    }

    @Override
    public List<String> getUserBookings(String name, String surname) {
        return null;
    }

    @Override
    public void addFileDataToDAO(List<String> list) {
        this.bookingList = list.stream().collect(Collectors.toList());
    }

    public List<String> getBookingModelList() {
        return bookingList;
    }

    public void setBookingModelList(List<String> bookingModelList) {
        this.bookingList = bookingModelList;
    }

}