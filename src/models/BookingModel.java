package models;

import java.io.Serializable;
import java.util.Objects;

/**
 * Model class of bookings
 *
 * @author Pinchuk Dmitry
 */
public class BookingModel implements Serializable {

    private int id; //id бронирования рейса
    private FlightModel flight; //рейс
    private UserModel user; //пользователь

    public BookingModel(int id, FlightModel flight, UserModel user) {
        this.id = id;
        this.flight = flight;
        this.user = user;
    }

    public BookingModel() {};

    public int getId() {
        return id;
    }

    public FlightModel getFlight() {
        return flight;
    }

    public UserModel getUser() {
        return user;
    }

    @Override
    public String toString() {
        return id +
                "\t\t" + flight.getId() +
                "\t\t" + flight.getDateTime() +
                "\t\t" + flight.getDispatchLocation() +
                "\t\t" + flight.getDestination() +
                "\t\t" + user.getUserName() +
                "\t\t" + user.getUserSurname();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingModel that = (BookingModel) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}