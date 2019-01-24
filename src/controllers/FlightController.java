package controllers;

import models.FlightModel;
import services.FlightService;

import java.util.List;

public class FlightController {

    private FlightService flightService = new FlightService();

    public List<FlightModel> getFlightList() {
        return this.flightService.getFlightList();
    }

}