package dao;

import models.FlightModel;

import java.util.ArrayList;
import java.util.List;

public class FlightDAOImpl implements FlightDAO {

    private List<String> flightModelList = new ArrayList<>();



    public List<String> getFlightModelList() {
        return flightModelList;
    }

    public void setFlightModelList(List<String> flightModelList) {
        this.flightModelList = flightModelList;
    }

    @Override
    public String getFlightInfo(int id) {
        return null;
    }

    @Override
    public List<String> searchFlights(String direction, String date, int seatsNumber) {
        return null;
    }

    @Override
    public void addFileDataToDAO(List<String> list) {
    }

}