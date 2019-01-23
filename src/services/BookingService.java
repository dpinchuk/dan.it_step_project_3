package services;

import dao.BookingDAO;
import dao.BookingDAOImpl;
import models.BookingModel;
import models.FlightModel;

import java.util.List;

public class BookingService implements BookingDAO {

    private BookingDAOImpl bookingDAO = new BookingDAOImpl();

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

    public void addFileDataToDAO(List<String> list) {
        this.bookingDAO.setBookingModelList(list);
    }

}