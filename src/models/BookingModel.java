package models;

import java.io.Serializable;
import java.util.Objects;

public class BookingModel implements Serializable {

    private int order; //id бронирования рейса
    private int flightId; //id рейса
    private String passengerName; //имя пассажира
    private String passengerSurname; //фамилия пассажира
    private int seatsNumber; //количество мест

    public BookingModel(int order, int flightId, String passengerName, String passengerSurname, int seatsNumber) {
        this.order = order;
        this.flightId = flightId;
        this.passengerName = passengerName;
        this.passengerSurname = passengerSurname;
        this.seatsNumber = seatsNumber;
    }

    public int getOrder() {
        return order;
    }

    public int getFlightId() {
        return flightId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getPassengerSurname() {
        return passengerSurname;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    @Override
    public String toString() {
        return "BookingModel{" +
                "order=" + order +
                ", flightId=" + flightId +
                ", passengerName='" + passengerName + '\'' +
                ", passengerSurname='" + passengerSurname + '\'' +
                ", seatsNumber=" + seatsNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingModel that = (BookingModel) o;
        return order == that.order &&
                Objects.equals(passengerName, that.passengerName) &&
                Objects.equals(passengerSurname, that.passengerSurname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, passengerName, passengerSurname);
    }
}