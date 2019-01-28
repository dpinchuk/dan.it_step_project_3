package controllers;

import models.FlightModel;
import services.FlightServiceImpl;

import java.util.List;

/**
 * Class controller for managin FlightServiceImpl class
 * @author Pinchuk Dmitry
 */
public class FlightController {

    private FlightServiceImpl flightService = new FlightServiceImpl();

    public List<FlightModel> getFlightsListNextHours(int ms) {
        return this.flightService.getFlightsListNextHours(ms);
    }

    public String getFlightInfo(int id) {
        return this.flightService.getFlightInfo(id);
    }

    public boolean isFlightExist(int id) {
        return this.flightService.isFlightExist(id);
    }

    public List<FlightModel> getFlightByData(String destination, String date, int seatsNumber) {
        //TODO check for date!!!
        return this.flightService.getFlightByData(destination, date, seatsNumber);
    }

    public boolean updateFlightOccupiedPlaces(int[] flightIdAndNumberPlaces) {
        return this.flightService.updateFlightOccupiedPlaces(flightIdAndNumberPlaces);
    }

    public int getFlightListSize() {
        return this.flightService.getFlightListSize();
    }
}