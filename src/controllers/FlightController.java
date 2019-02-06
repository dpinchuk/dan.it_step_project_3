package controllers;

import models.FlightModel;
import models.UserModel;
import services.FlightServiceImpl;

import java.util.List;

/**
 * Class controller for management FlightServiceImpl.class
 *
 * @author Pinchuk Dmitry
 */
public class FlightController {

    private FlightServiceImpl flightService = new FlightServiceImpl();

    /**
     * Returns List<FlightModel> during the specified time
     *
     * @param ms int
     * @return List<FlightModel>
     */
    public List<FlightModel> getFlightsDuringTime(int ms) {
        return this.flightService.getFlightsDuringTime(ms);
    }

    /**
     * Returns info about flight
     *
     * @param id   String
     * @param user UserModel
     * @return UserModel
     */
    public String getFlightInfo(String id, UserModel user) {
        return this.flightService.getFlightInfo(id, user);
    }

    /**
     * Returns true/false for flight existing
     *
     * @param flight FlightModel
     * @return boolean
     */
    public boolean isFlightExist(FlightModel flight) {
        return this.flightService.isFlightExist(flight);
    }

    /**
     * Returns fkights list by data
     *
     * @param destination String
     * @param date        String
     * @param seatsNumber int
     * @return List<FlightModel>
     */
    public List<FlightModel> getFlightByData(String destination, String date, int seatsNumber, UserModel user) {
        return this.flightService.getFlightByData(destination, date, seatsNumber, user);
    }

    /**
     * Returns true/false after update of flight
     *
     * @param flightModel FlightModel
     * @param place       int
     */
    public void updateFlight(FlightModel flightModel, int place) {
        this.flightService.updateFlight(flightModel, place);
    }

    /**
     * Returns a size of flights collection
     *
     * @return int
     */
    public int getFlightListSize() {
        return this.flightService.getFlightListSize();
    }

    /**
     * Return flight List by [id]
     *
     * @param flightId int
     * @return FlightModel
     */
    public FlightModel getFlightById(int flightId) {
        return this.flightService.getFlightById(flightId);
    }

    /**
     * Writes flight List to file
     */
    public void writeFlightListToFile() {
        this.flightService.writeFlightListToFile();
    }

}