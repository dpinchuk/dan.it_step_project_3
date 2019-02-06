package dao;

import models.BookingModel;
import models.FlightModel;
import models.UserModel;

import java.util.List;

/**
 * Interface for BookingDAOImpl
 *
 * @author Pinchuk Dmitry
 */
public interface BookingDAO {

    /**
     * @param flight FlightModel
     * @param user   UserModel
     * @return BookingModel
     */
    BookingModel createBooking(FlightModel flight, UserModel user); //создает бронированныйие рейса

    /**
     * @param id int
     * @return is deleting true/false
     */
    boolean deleteBookingById(int id); //удаляет бронированный рейс по id

    /**
     * @param booking BookingModel
     * @return int
     */
    int deleteBooking(BookingModel booking); //удаляет бронированный рейс

    /**
     * @param id int
     * @param user UserModel
     * @return BookingModel
     */
    BookingModel getUserBookingById(int id, UserModel user); //получаем забронированный рейс по id

    /**
     * @param user UserModel
     * @return List<BookingModel>
     */
    List<BookingModel> getUserBookings(UserModel user); //получаем список забронированных рейсов юзера

    /**
     * void
     */
    void writeBookingListToFile();

}