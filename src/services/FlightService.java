package services;

import models.FlightModel;

import java.util.List;

public interface FlightService {

    List<FlightModel> getFlightsListNextHours(int ms); //выводит все рейсы в часовом промежутке

    FlightModel getFlightInfo(int id); //получаем информацию о рейсе по id

    List<FlightModel> searchFlights(String destination, String date, int seatsNumber); //находит рейсы по указанным данным

}