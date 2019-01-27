package dao;

import models.FlightModel;

import java.util.List;

public interface FlightDAO {

    FlightModel getFlightById(int id); //возвращает рейс по id

    List<FlightModel> getFlightByData(String destination, String date, int seatsNumber); //находит рейсы по указанным данным

    boolean updateFlightOccupiedPlaces(int[] flightIdAndNumberPlaces); //изменяет количество занятых мест рейса

}