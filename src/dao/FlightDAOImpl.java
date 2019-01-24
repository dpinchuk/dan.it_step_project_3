package dao;

import models.FlightModel;
import utils.Loader;

import java.util.List;
import java.util.stream.Collectors;

public class FlightDAOImpl implements FlightDAO {

    private List<FlightModel> flightList;

    public FlightDAOImpl() {
        try {
            this.flightList = new Loader().getFlightModelList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getFlightInfo(int id) {
        return null;
    }

    @Override
    public List<FlightModel> searchFlights(String destination, String date, int seatsNumber) {
        return this.flightList
                .stream()
                .filter(e ->
                        e.getDestination().equals(destination) &&
                        e.getDate().equals(date) &&
                        e.getSeatsNumber() == seatsNumber)
        .collect(Collectors.toList());
    }

    public List<FlightModel> getFlightList() {
        return flightList;
    }

}