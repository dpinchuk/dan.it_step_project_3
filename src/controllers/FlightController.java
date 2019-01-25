package controllers;

import services.FlightServiceImpl;

public class FlightController {

    private FlightServiceImpl flightServiceImpl = new FlightServiceImpl();

    public FlightServiceImpl getFlightServiceImpl() {
        return this.flightServiceImpl;
    }

}