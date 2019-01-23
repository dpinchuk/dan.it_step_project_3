package dao;

import java.util.List;

public interface FlightDAO {

    String getFlightInfo(int id); //получаем информацию о рейсе по id

    List<String> searchFlights(String direction, String date, int seatsNumber); //находит рейсы по указанным данным

}