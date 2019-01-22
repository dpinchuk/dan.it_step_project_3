package dao;

import models.BookingModel;
import models.FlightModel;

import java.util.List;

public interface BookingDAO {

    BookingModel createBooking(FlightModel flight, String name, String surname); //создает бронированный рейс

    boolean deleteBooking(int id); //удаляет бронированный рейс по id

    boolean deleteBooking(BookingModel booking); //удаляет бронированный рейс??? Еще подумаю как реализовать!!!

    List<String> getAllBookings(); //***получаем список всех существующих забронированных рейсов ***

    List<String> getBookings(int bookingId); //получаем забронированный рейс по id

    List<String> getUserBookings(String name, String surname); //получаем список забронированных рейсов юзера

    void addFileDataToDAO(List<String> list); //добавляет список, полученный из файла, в DAO list

}