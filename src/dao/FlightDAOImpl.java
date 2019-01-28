package dao;

import models.FlightModel;
import utils.Loader;

import java.time.ZoneOffset;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Class implements interface FlightDAO
 *
 * @author Pinchuk Dmitry
 */
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
        return this.flightList.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<FlightModel> getFlightByData(String destination, long dateMilli, int seatsNumber) {
        return this.flightList
                .stream()
                .filter(e ->
                        e.getDestination().toLowerCase().equals(destination.toLowerCase()) &&
                                e.getDateTime().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli() == dateMilli &&
                                (e.getSeatsLeft()) >= seatsNumber)
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateFlightOccupiedPlaces(int[] flightIdAndNumberPlaces) {
        OptionalInt indexOpt = IntStream.range(0, this.flightList.size())
                .filter(i -> flightIdAndNumberPlaces[0] == this.flightList.get(i).getId())
                .findFirst();
        int index = 0;
        if (indexOpt.isPresent()) {
            index = indexOpt.getAsInt();
        }
        FlightModel flightModel = new FlightModel(
                this.flightList.get(index).getId(),
                this.flightList.get(index).getDateTime(),
                this.flightList.get(index).getDispatchLocation(),
                this.flightList.get(index).getDestination(),
                this.flightList.get(index).getSeatsNumber(),
                this.flightList.get(index).getSeatsLeft() - flightIdAndNumberPlaces[1]);
        this.flightList.remove(index);
        this.flightList.add(index, flightModel);
        return this.flightList.contains(flightModel);
    }

    @Override
    public List<FlightModel> getFlightsListNextHours(long milliCurent, long milliFlight) {

        for (FlightModel flight : this.flightList) {
            boolean bool = flight.getDateTime().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli() - milliCurent <= milliFlight;
            System.out.println(bool);
        }

        return this.flightList
                .stream()
                .filter(e ->
                        (e.getDateTime().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli() - milliCurent <= milliFlight))
                .collect(Collectors.toList());
    }

    public int getFlightListSize() {
        return this.flightList.size();
    }
}