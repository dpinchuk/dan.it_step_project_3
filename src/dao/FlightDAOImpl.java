package dao;

import models.FlightModel;
import utils.Loader;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import static utils.Constants.*;

/**
 * DAO class implements interface FlightDAO
 *
 * @author Pinchuk Dmitry
 */
public class FlightDAOImpl implements FlightDAO {

    private List<FlightModel> flightList;
    private Loader loader = new Loader();

    public FlightDAOImpl() {
        try {
            this.flightList = loader.readFlightListFromFile();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Implements FlightModel getFlightById(int id)
     *
     * @param id int
     * @return FlightModel
     */
    @Override
    public FlightModel getFlightById(int id) {
        return this.flightList.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Implements List<FlightModel> getFlightByData(String destination, LocalDate date, int seatsNumber)
     *
     * @param destination String
     * @param date        LocalDate
     * @param seatsNumber int
     * @return List<FlightModel>
     */
    @Override
    public List<FlightModel> getFlightByData(String destination, LocalDate date, int seatsNumber) {
        return this.flightList
                .stream()
                .filter(e -> e.getDestination().toLowerCase().equals(destination.toLowerCase()) &&
                        e.getDateTime().toLocalDate().equals(date) &&
                        e.getSeatsRemaining() >= seatsNumber)
                .collect(Collectors.toList());
    }

    /**
     * Implements boolean updateFlight(FlightModel flight, int place)
     *
     * @param flight FlightModel
     * @param place  int
     */
    @Override
    public void updateFlight(FlightModel flight, int place) {
        FlightModel flightModel = this.flightList
                .stream()
                .filter(e -> e.getId() == flight.getId())
                .findFirst()
                .orElse(null);
        if (flightModel != null) {
            this.flightList.remove(flightModel);
            int seatsRemaining = flightModel.getSeatsRemaining() + place;
            flightModel.setSeatsRemaining(seatsRemaining);
            this.flightList.add(flightModel);
        } else {
            System.out.println(OPERATION_ERROR);
        }
    }

    /**
     * Implements List<FlightModel> getFlightsDuringTime(long milliCurrent, long milliFlight)
     *
     * @param milliCurrent long
     * @param milliFlight  long
     * @return List<FlightModel>
     */
    @Override
    public List<FlightModel> getFlightsDuringTime(long milliCurrent, long milliFlight) {
        return this.flightList
                .stream()
                .filter(e ->
                        (e.getDateTime().atZone(ZoneId.of(TIME_ZONE)).toInstant().toEpochMilli() - milliCurrent <= milliFlight) &&
                                e.getDateTime().atZone(ZoneId.of(TIME_ZONE)).toInstant().toEpochMilli() - milliCurrent >= 0)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isFlightExist(FlightModel flight) {
        return this.flightList
                .stream()
                .anyMatch(e -> e.equals(flight));
    }

    /**
     * Returns a size of flights collection
     *
     * @return int
     */
    public int getFlightListSize() {
        return this.flightList.size();
    }

    @Override
    public void writeFlightListToFile() {
        this.loader.writeFlightListToFile(this.flightList);
    }

}