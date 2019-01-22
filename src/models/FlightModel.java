package models;

public class FlightModel extends MainModel {

    private int flightId; //id рейса
    private String date; //дата отправки
    private String time; //время отправки
    private String dispatchLocation; //место отправки
    private String destination; //место назначения
    private int seatsNumber; //общее кол-во мест рейса
    private int occupiedPlaces; //кол-во занятых мест

}