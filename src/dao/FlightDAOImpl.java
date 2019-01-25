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

    public List<FlightModel> getFlightList() {
        return flightList;
    }

    @Override
    public FlightModel getFlightInfo(int id) {
        return this.flightList
                .stream()
                .filter(e ->
                            e.getFlightId() == id)
                .findFirst()
                .get();
    }

    @Override
    public List<FlightModel> searchFlights(String destination, String date, int seatsNumber) {
        return this.flightList
                .stream()
                .filter(e ->
                            e.getDestination().toLowerCase().equals(destination.toLowerCase()) &&
                            e.getDate().equals(date) &&
                            (e.getSeatsNumber() - e.getOccupiedPlaces()) >= seatsNumber)
                .collect(Collectors.toList());
    }
}