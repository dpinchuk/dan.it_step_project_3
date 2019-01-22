package services;

import dao.FlightDAO;
import dao.FlightDAOImpl;

import java.util.List;

public class FlightService extends MainService implements FlightDAO {

    private FlightDAOImpl flightListDAO = new FlightDAOImpl();

    @Override
    public String getFlightInfo(int id) {
        return null;
    }

    @Override
    public List<String> searchFlights(String direction, String date, int seatsNumber) {
        return null;
    }

    public void addFileDataToDAO(List<String> list) {
        this.flightListDAO.addFileDataToDAO(list);
    }

}