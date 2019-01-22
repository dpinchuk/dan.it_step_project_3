package dao;

import java.util.List;

public interface FlightDAO {

    String getFlightInfo(int id);

    List<String> searchFlights(String direction, String date, int seatsNumber);

}