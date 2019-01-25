package dao;

import models.FlightModel;
import utils.Loader;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        FlightModel flightModel;
        try {
            flightModel = this.flightList
                    .stream()
                    .filter(e ->
                            e.getFlightId() == id)
                    .findFirst()
                    .get();
        } catch (NoSuchElementException e) {
            flightModel = null;
        }
        return flightModel;
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

    @Override
    public boolean updateOccupiedPlaces(int flight, int places) {
        OptionalInt indexOpt = IntStream.range(0, this.getFlightList().size())
                .filter(i -> flight == this.getFlightList().get(i).getFlightId())
                .findFirst();
        int index = indexOpt.getAsInt();
        FlightModel flightModel = new FlightModel(
                this.flightList.get(index).getFlightId(),
                this.flightList.get(index).getDate(),
                this.flightList.get(index).getTime(),
                this.flightList.get(index).getDispatchLocation(),
                this.flightList.get(index).getDestination(),
                this.flightList.get(index).getSeatsNumber(),
                places);
        this.getFlightList().remove(index);
        this.getFlightList().add(index, flightModel);
        return false;
    }

}