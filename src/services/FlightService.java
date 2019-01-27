package services;

import models.FlightModel;

import java.util.List;

public interface FlightService {

    List<FlightModel> getFlightsListNextHours(int ms); //выводит все рейсы в часовом промежутке ms

    String getFlightInfo(int id); //получаем информацию о рейсе по id

    boolean isFlightExist(int id); //проверяет существование рейса по id

}