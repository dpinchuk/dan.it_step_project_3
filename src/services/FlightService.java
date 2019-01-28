package services;

import models.FlightModel;

import java.util.List;

public interface FlightService {

    List<FlightModel> getFlightsListNextHours(int milliFlight); //выводит все рейсы в часовом промежутке ms

    String getFlightInfo(int id); //получаем информацию о рейсе по id

    boolean isFlightExist(int id); //проверяет существование рейса по id

    List<FlightModel> getFlightByData(String destination, String date, int seatsNumber); //возвращает список рейсов по данным

    boolean updateFlightOccupiedPlaces(int[] flightIdAndNumberPlaces);

}