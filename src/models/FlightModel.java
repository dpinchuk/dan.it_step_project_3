package models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class FlightModel implements Serializable {

    private int id; //id рейса
    private LocalDateTime dateTime; //дата и время отправки
    private String dispatchLocation; //место отправки
    private String destination; //место назначения
    private int seatsNumber; //общее кол-во мест рейса
    private int seatsLeft; //кол-во занятых мест

    public FlightModel(int id, LocalDateTime dateTime, String dispatchLocation, String destination, int seatsNumber, int seatsLeft) {
        this.id = id;
        this.dateTime = dateTime;
        this.dispatchLocation = dispatchLocation;
        this.destination = destination;
        this.seatsNumber = seatsNumber;
        this.seatsLeft = seatsLeft;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDispatchLocation() {
        return dispatchLocation;
    }

    public String getDestination() {
        return destination;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public int getSeatsLeft() {
        return seatsLeft;
    }

    @Override
    public String toString() {
        return id +
                "\t\t\t\t" + dateTime +
                "\t\t\t\t" + dispatchLocation +
                "\t\t\t\t" + destination +
                "\t\t\t\t" + seatsNumber +
                "\t\t\t\t" + seatsLeft;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightModel that = (FlightModel) o;
        return id == that.id &&
                Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(destination, that.destination) &&
                Objects.equals(seatsNumber, that.seatsNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, destination, seatsNumber);
    }
}