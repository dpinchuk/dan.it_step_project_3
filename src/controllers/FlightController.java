package controllers;

import models.FlightModel;
import services.FlightServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static utils.Constants.DATE_FORMAT_REGEX;

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
     * @param id int
     * @return String
     */
    public String getFlightInfo(int id) {
        return this.flightService.getFlightInfo(id);
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
    public List<FlightModel> getFlightByData(String destination, String date, int seatsNumber) {
        return this.flightService.getFlightByData(destination, date, seatsNumber);
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

    public FlightModel getFlightById(int flightId) {
        return this.flightService.getFlightById(flightId);
    }

    public void writeFlightListToFile() {
        this.flightService.writeFlightListToFile();
    }

}