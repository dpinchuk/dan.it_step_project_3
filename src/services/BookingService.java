package services;

public interface BookingService {

    /**
     * @param id int
     * @return String
     */
    String getBookingInfo(int id); //получаем информацию о забронированном рейсе по [id]

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