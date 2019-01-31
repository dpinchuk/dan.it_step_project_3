package models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Model class of flights
 *
 * @author Pinchuk Dmitry
 */
public class FlightModel implements Serializable {

    private int id; //id рейса
    private LocalDateTime dateTime; //дата и время отправки
    private String dispatchLocation; //место отправки
    private String destination; //место назначения
    private int seatsRemaining; //количество оставшихся мест

    public FlightModel(int id, LocalDateTime dateTime, String dispatchLocation, String destination, int seatsRemaining) {
        this.id = id;
        this.dateTime = dateTime;
        this.dispatchLocation = dispatchLocation;
        this.destination = destination;
        this.seatsRemaining = seatsRemaining;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDestination() {
        return destination;
    }

    public String getDispatchLocation() {
        return dispatchLocation;
    }

    public int getSeatsRemaining() {
        return seatsRemaining;
    }

    public void setSeatsRemaining(int seatsRemaining) {
        this.seatsRemaining = seatsRemaining;
    }

    @Override
    public String toString() {
        return "\t\t" + id +
                "\t\t" + dateTime +
                "\t\t" + dispatchLocation +
                "\t\t" + destination +
                "\t\t" + seatsRemaining;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightModel that = (FlightModel) o;
        return id == that.id &&
                Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(dispatchLocation, that.dispatchLocation) &&
                Objects.equals(destination, that.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime, dispatchLocation, destination);
    }

}