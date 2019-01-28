package dao;

import models.BookingModel;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Class implements interface BookingDAO
 *
 * @author Pinchuk Dmitry
 */
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
        return this.bookingList.removeIf(b -> b.getId() == id);
    }

    @Override
    public BookingModel getBookingById(int id) {
        return this.bookingList
                .stream()
                .filter(e ->
                        e.getId() == id)
                .findFirst()
                .orElse(null);
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

    /**
     * Getter
     *
     * @return this.bookingList
     */
    public List<BookingModel> getBookingModelList() {
        return this.bookingList;
    }

}