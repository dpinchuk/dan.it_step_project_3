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
     * Creates new booking
     *
     * @param flight FlightModel
     * @param user   UserModel
     * @return BookingModel
     */
    BookingModel createBooking(FlightModel flight, UserModel user); //создает бронированныйие рейса

    /**
     * Delete existing booking
     *
     * @param id int
     * @return is deleting true/false
     */
    boolean deleteBookingById(int id); //удаляет бронированный рейс по id

    /**
     * Return booking by [id]
     *
     * @param id int
     * @return object BookingModel
     */
    BookingModel getBookingById(int id); //получаем забронированный рейс по id

    /**
     * Return List<BookingModel> by user's name and surname
     *
     * @param user UserModel
     * @return List<BookingModel>
     */
    List<BookingModel> getUserBookings(UserModel user); //получаем список забронированных рейсов юзера

    /**
     * Return List<BookingModel> by sessionId (for authorized users only)
     *
     * @param sessionId int
     * @return List<BookingModel>
     */
    List<BookingModel> getUserBookings(int sessionId); //получаем список забронированных рейсов авторизованного юзера

    void writeBookingListToFile();

}