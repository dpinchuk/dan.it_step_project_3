package services;

import dao.BookingDAOImpl;
import models.BookingModel;

import java.util.List;

public class BookingServiceImpl implements BookingService {

    private BookingDAOImpl bookingDAO = new BookingDAOImpl();

    public BookingDAOImpl getBookingDAO() {
        return bookingDAO;
    }

    @Override
    public int createBooking(int flightId, String passengerName, String passengerSurname, int seatsNumber) {
        if (isValidNumber(flightId) && isValidString(passengerName) && isValidString(passengerSurname) && isValidNumber(seatsNumber)) {
            return this.getBookingDAO().createBooking(flightId, passengerName, passengerSurname, seatsNumber);
        }
        return 0;
    }

    @Override
    public boolean deleteBooking(int id) {
        return this.getBookingDAO().deleteBooking(id);
    }

    @Override
    public List<BookingModel> getBookings(int bookingId) {
        return null;
    }

    @Override
    public List<BookingModel> getUserBookings(String name, String surname) {
        return null;
    }

    @Override
    public void addFileDataToDAO(List<BookingModel> list) {

    }

    private boolean isValidNumber(int number) {
        if(number > 0) {
            return true;
        }
        return false;
    }

    private boolean isValidString(String str) {
        if(str != null && !str.equals("") && str.length() < 32) {
            //TODO XSS-inj, SQL-inj, CSRF-inj
            return true;
        }
        return false;
    }

}