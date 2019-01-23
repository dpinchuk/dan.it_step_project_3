package dao;

import models.FlightModel;
import utils.Loader;

import java.util.List;

import static utils.Constants.DATA_FILE_FLIGHTS_TO;
import static utils.Constants.DATA_FILE_FLIGHTS_FROM;

public class FlightDAOImpl implements FlightDAO {

    private List<FlightModel> flightList;
    private Loader loader;

    {
        try {
            this.loader = new Loader(DATA_FILE_FLIGHTS_FROM, DATA_FILE_FLIGHTS_TO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getFlightInfo(int id) {
        return null;
    }

    @Override
    public List<String> searchFlights(String direction, String date, int seatsNumber) {
        return null;
    }

    public List<FlightModel> getFlightList() {
        return flightList;
    }

    public void setFlightList(List<FlightModel> flightList) {
        this.flightList = flightList;
    }

}