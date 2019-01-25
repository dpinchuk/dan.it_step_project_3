package models;

import java.io.Serializable;
import java.util.Objects;

public class FlightModel implements Serializable {

    private int flightId; //id рейса
    private String date; //дата отправки
    private String time; //время отправки
    private String dispatchLocation; //место отправки
    private String destination; //место назначения
    private int seatsNumber; //общее кол-во мест рейса
    private int occupiedPlaces; //кол-во занятых мест

    public FlightModel(int flightId, String date, String time, String dispatchLocation, String destination, int seatsNumber, int occupiedPlaces) {
        this.flightId = flightId;
        this.date = date;
        this.time = time;
        this.dispatchLocation = dispatchLocation;
        this.destination = destination;
        this.seatsNumber = seatsNumber;
        this.occupiedPlaces = occupiedPlaces;
    }

    public int getFlightId() {
        return flightId;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
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

    public int getOccupiedPlaces() {
        return occupiedPlaces;
    }

    public void setOccupiedPlaces(int occupiedPlaces) {
        this.occupiedPlaces = occupiedPlaces;
    }

    @Override
    public String toString() {
        return "FlightModel{" +
                "flightId=" + flightId +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", dispatchLocation='" + dispatchLocation + '\'' +
                ", destination='" + destination + '\'' +
                ", seatsNumber=" + seatsNumber +
                ", occupiedPlaces=" + occupiedPlaces +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightModel that = (FlightModel) o;
        return flightId == that.flightId &&
                Objects.equals(date, that.date) &&
                Objects.equals(destination, that.destination) &&
                Objects.equals(seatsNumber, that.seatsNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, destination, seatsNumber);
    }
}