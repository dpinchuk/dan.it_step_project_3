package dao;

import models.BookingModel;
import models.FlightModel;

import java.util.List;

public interface BookingDAO {

    BookingModel createBooking(FlightModel flight, String name, String surname);
    boolean deleteBooking(int id);
    boolean deleteBooking(BookingModel booking);
    List<BookingModel> getAllBookings();
    List<BookingModel> getBookings(int bookingId);
    List<BookingModel> getUserBookings(String name, String surname);

}