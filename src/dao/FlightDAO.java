package dao;

import models.FlightModel;

import java.util.List;

public interface FlightDAO {

    FlightModel getFlightInfo(int id); //получаем информацию о рейсе по id

    List<FlightModel> searchFlights(String destination, String date, int seatsNumber); //находит рейсы по указанным данным

}