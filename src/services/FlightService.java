package services;

import dao.FlightDAO;
import dao.FlightDAOImpl;
import models.FlightModel;

import java.util.List;

public class FlightService implements FlightDAO {

    private FlightDAOImpl flightListDAO = new FlightDAOImpl();

    @Override
    public String getFlightInfo(int id) {
        return this.flightListDAO.getFlightInfo(id);
    }

    @Override
    public List<FlightModel> searchFlights(String destination, String date, int seatsNumber) {
        return this.flightListDAO.searchFlights(destination, date, seatsNumber);
    }

    public List<FlightModel> getFlightList() {
        return this.flightListDAO.getFlightList();
    }

}