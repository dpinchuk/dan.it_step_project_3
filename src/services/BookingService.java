package services;

import models.BookingModel;
import models.UserModel;

import java.util.List;

public interface BookingService {

    /**
     * @param id int
     * @return String
     */
    String getBookingInfo(int id); //получаем информацию о забронированном рейсе по [id]

    /**
     * @param user      UserModel
     * @param sessionId int
     * @return List<BookingModel>
     */
    List<BookingModel> getUserBookings(UserModel user, int sessionId);

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
     * @param id int
     * @return boolean
     */
    boolean isBookingExist(int id); //проверяет существование бронированного рейса по [id]

    /**
     * void
     */
    void writeBookingListToFile();

}