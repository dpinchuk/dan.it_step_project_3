package services;

import models.BookingModel;

import java.util.List;

public interface BookingService {

    String getBookingInfo(int id); //получаем информацию о забронированно рейсе по id

    boolean isBookingExist(int id); //проверяет существование бронированного рейса по id

}