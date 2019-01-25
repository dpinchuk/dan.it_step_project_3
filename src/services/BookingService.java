package services;

import models.BookingModel;

import java.util.List;

public interface BookingService {

    int createBooking(int flightId, String name, String surname, int seatsNumber); //создает бронированный рейс

    boolean deleteBooking(int id); //удаляет бронированный рейс по id

    List<BookingModel> getBookings(int bookingId); //получаем забронированный рейс по id

    List<BookingModel> getUserBookings(String name, String surname); //получаем список забронированных рейсов юзера

    void addFileDataToDAO(List<BookingModel> list); //добавляет список, полученный из файла, в DAO list

}