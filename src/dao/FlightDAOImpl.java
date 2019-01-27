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

    @Override
    public FlightModel getFlightById(int id) {
        try {
            return this.flightList
                    .stream()
                    .filter(e ->
                            e.getId() == id)
                    .findFirst()
                    .get();
        } catch (NoSuchElementException e) {
        }
        return null;
    }

    @Override
    public List<FlightModel> getFlightByData(String destination, String date, int seatsNumber) {
        return this.flightList
                .stream()
                .filter(e ->
                        e.getDestination().toLowerCase().equals(destination.toLowerCase()) &&
                                e.getDate().equals(date) &&
                                (e.getSeatsLeft()) >= seatsNumber)
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateFlightOccupiedPlaces(int[] flightIdAndNumberPlaces) {
        OptionalInt indexOpt = IntStream.range(0, this.getFlightList().size())
                .filter(i -> flightIdAndNumberPlaces[0] == this.getFlightList().get(i).getId())
                .findFirst();
        int index = 0;
        if (indexOpt.isPresent()) {
            index = indexOpt.getAsInt();
        }
        FlightModel flightModel = new FlightModel(
                this.flightList.get(index).getId(),
                this.flightList.get(index).getDate(),
                this.flightList.get(index).getTime(),
                this.flightList.get(index).getDispatchLocation(),
                this.flightList.get(index).getDestination(),
                this.flightList.get(index).getSeatsNumber(),
                this.flightList.get(index).getSeatsLeft() - flightIdAndNumberPlaces[1]);
        this.getFlightList().remove(index);
        this.getFlightList().add(index, flightModel);
        return this.getFlightList().contains(flightModel);
    }

    public List<FlightModel> getFlightList() {
        return flightList;
    }

}