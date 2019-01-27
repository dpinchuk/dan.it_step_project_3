package dao;

import models.BookingModel;

import java.util.List;

public interface BookingDAO {

    int createBooking(int flightId, String name, String surname, int seatsNumber, int userHash); //создает бронированный рейс

    boolean deleteBookingById(int id); //удаляет бронированный рейс по id

    BookingModel getBookingById(int id); //получаем забронированный рейс по id

    List<BookingModel> getUserBookings(String name, String surname); //получаем список забронированных рейсов юзера //TODO

    List<BookingModel> getUserBookings(int sessionId); //получаем список забронированных рейсов авторизованного юзера

}