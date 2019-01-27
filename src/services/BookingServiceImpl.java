package services;

import dao.BookingDAOImpl;
import models.BookingModel;

public class BookingServiceImpl extends MainService implements BookingService {

    private BookingDAOImpl bookingDAO = new BookingDAOImpl();

    @Override
    public String getBookingInfo(int id) {
        if (isValidNumber(id)) {
            return this.bookingDAO.getBookingById(id).toString();
        }
        return "";
    }

    @Override
    public boolean isBookingExist(int id) {
        if (isValidNumber(id)) {
            return this.bookingDAO.getBookingById(id) != null;
        }
        return false;
    }

    public BookingDAOImpl getBookingDAO() {
        return bookingDAO;
    }

}