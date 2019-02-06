package services;

import models.BookingModel;
import models.UserModel;

import java.util.List;

public interface BookingService {

    /**
     * @param id   int
     * @param user UserModel
     * @return String
     */
    String getBookingInfo(int id, UserModel user); //получаем информацию о забронированном рейсе по [id]

    /**
     * @param user      UserModel
     * @return List<BookingModel>
     */
    List<BookingModel> getUserBookings(UserModel user);

    /**
     * @param id   int
     * @param user UserModel
     * @return BookingModel
     */
    BookingModel getBookingById(int id, UserModel user);

    /**
     * @param id   String
     * @param user UserModel
     * @return int
     */
    int deleteBookingById(String id, UserModel user);

    /**
     * @param id   int
     * @param user UserModel
     * @return boolean
     */
    boolean isBookingExist(int id, UserModel user); //проверяет существование бронированного рейса по [id]

    /**
     * void
     */
    void writeBookingListToFile();

}