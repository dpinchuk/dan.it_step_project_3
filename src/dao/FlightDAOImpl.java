package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightDAOImpl implements FlightDAO {

    private List<String> flightList = new ArrayList<>();

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
        this.flightList = list.stream().collect(Collectors.toList());
    }

    public List<String> getFlightList() {
        return flightList;
    }

    public void setFlightList(List<String> flightList) {
        this.flightList = flightList;
    }

}