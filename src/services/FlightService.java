package services;

import dao.FlightDAO;
import dao.FlightDAOImpl;

import java.util.List;

public class FlightService implements FlightDAO {

    private FlightDAOImpl flightListDAO = new FlightDAOImpl();

    @Override
    public String getFlightInfo(int id) {
        return null;
    }

    @Override
    public List<String> searchFlights(String direction, String date, int seatsNumber) {
        return null;
    }

    public FlightDAOImpl getFlightListDAO() {
        return flightListDAO;
    }
}