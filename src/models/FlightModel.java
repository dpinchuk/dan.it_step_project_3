package models;

import java.io.Serializable;

public class FlightModel implements Serializable {

    private int flightId; //id рейса
    private String date; //дата отправки
    private String time; //время отправки
    private String dispatchLocation; //место отправки
    private String destination; //место назначения
    private String seatsNumber; //общее кол-во мест рейса
    private String occupiedPlaces; //кол-во занятых мест

    public FlightModel(int flightId, String date, String time, String dispatchLocation, String destination, String seatsNumber, String occupiedPlaces) {
        this.flightId = flightId;
        this.date = date;
        this.time = time;
        this.dispatchLocation = dispatchLocation;
        this.destination = destination;
        this.seatsNumber = seatsNumber;
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
}