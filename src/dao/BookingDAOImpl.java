package dao;

import models.BookingModel;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class BookingDAOImpl implements BookingDAO {

    private List<BookingModel> bookingList = new ArrayList<>();
    private int bookingId = 0;

    @Override
    public int createBooking(int flightId, String name, String surname, int seatsNumber, int userHash) {
        BookingModel bookingModel = new BookingModel(++bookingId, flightId, name, surname, seatsNumber, userHash);
        this.bookingList.add(bookingModel);
        return bookingModel.getId();
    }

    @Override
    public boolean deleteBookingById(int id) {
        try {
            return this.bookingList.remove(this.bookingList
                    .stream()
                    .filter(e ->
                            e.getId() == id)
                    .findFirst()
                    .get());
        } catch (NoSuchElementException e) {
        }
        return false;
    }

    @Override
    public BookingModel getBookingById(int id) {
        try {
            return this.bookingList
                    .stream()
                    .filter(e ->
                            e.getId() == id)
                    .findFirst()
                    .get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public List<BookingModel> getUserBookings(String name, String surname) {
        return this.getBookingModelList()
                .stream()
                .filter(e -> (e.getName().equals(name) && e.getSurname().equals(surname)))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingModel> getUserBookings(int sessionId) {
        return this.getBookingModelList()
                .stream()
                .filter(e -> e.getUserHash() == sessionId)
                .collect(Collectors.toList());
    }

    public List<BookingModel> getBookingModelList() {
        return bookingList;
    }

}