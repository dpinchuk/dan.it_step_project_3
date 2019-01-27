package controllers;

import services.FlightServiceImpl;

public class FlightController {

    private FlightServiceImpl flightService = new FlightServiceImpl();

    public FlightServiceImpl getFlightService() {
        return this.flightService;
    }

}