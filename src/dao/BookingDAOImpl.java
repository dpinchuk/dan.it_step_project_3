package dao;

import models.BookingModel;
import models.FlightModel;

import java.util.ArrayList;
import java.util.List;

public class BookingDAOImpl implements BookingDAO {

    private List<String> bookingModelList;

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
    }

    public List<String> getBookingModelList() {
        return bookingModelList;
    }

    public void setBookingModelList(List<String> bookingModelList) {
        this.bookingModelList = bookingModelList;
    }

}