package dao;

import models.BookingModel;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BookingDAOImpl implements BookingDAO {

    private List<BookingModel> bookingList = new ArrayList<>();
    private BookingModel bookingModel;
    private int bookingId = 0;

    @Override
    public int createBooking(int flightId, String name, String surname, int seatsNumber) {
        this.bookingModel = new BookingModel(++bookingId, flightId, name, surname, seatsNumber);
        bookingList.add(this.bookingModel);
        return this.bookingModel.getOrder();
    }

    @Override
    public boolean deleteBooking(int id) {
        BookingModel bookingModel;
        try {
            bookingModel = this.bookingList
                    .stream()
                    .filter(e ->
                            e.getOrder() == id)
                    .findFirst()
                    .get();
            this.bookingList.remove(bookingModel);
            return true;
        } catch (NoSuchElementException e) {
        }
        return false;
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
        this.bookingList = new ArrayList<>(list);
    }

    public List<BookingModel> getBookingModelList() {
        return bookingList;
    }

    public void setBookingModelList(List<BookingModel> bookingModelList) {
        this.bookingList = bookingModelList;
    }

}