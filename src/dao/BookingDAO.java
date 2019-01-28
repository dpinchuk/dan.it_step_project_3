package dao;

import models.BookingModel;

import java.util.List;

/**
 * Interface for BookingDAOImpl
 * @author Pinchuk Dmitry
 */
public interface BookingDAO {

    /**
     * Creates new booking
     * @param flightId int
     * @param name String
     * @param surname String
     * @param seatsNumber int
     * @param userHash int
     * @return id booking (int)
     */
    int createBooking(int flightId, String name, String surname, int seatsNumber, int userHash); //создает бронированный рейс

    /**
     * Delete existing booking
     * @param id int
     * @return is deleting true/false
     */
    boolean deleteBookingById(int id); //удаляет бронированный рейс по id

    /**
     * Return booking by [id]
     * @param id int
     * @return object BookingModel
     */
    BookingModel getBookingById(int id); //получаем забронированный рейс по id

    /**
     * Return List<BookingModel> by user's name and surname
     * @param name String
     * @param surname String
     * @return List<BookingModel>
     */
    List<BookingModel> getUserBookings(String name, String surname); //получаем список забронированных рейсов юзера //TODO

    /**
     * Return List<BookingModel> by sessionId (for authorized users only)
     * @param sessionId
     * @return
     */
    List<BookingModel> getUserBookings(int sessionId); //получаем список забронированных рейсов авторизованного юзера

}