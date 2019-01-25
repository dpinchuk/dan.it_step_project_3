package services;

import dao.FlightDAOImpl;
import models.FlightModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FlightServiceImpl implements FlightService {

    private FlightDAOImpl flightListDAO = new FlightDAOImpl();

    @Override
    public List<FlightModel> getFlightsListNextHours(int ms) {
        return this.flightListDAO.getFlightList()
                .stream()
                .filter(e -> {
                    try {
                        return compareDateFlightWithDateNow(e.getDate(), e.getTime(), ms);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public FlightModel getFlightInfo(int id) {
        return this.flightListDAO.getFlightInfo(id);
    }

    @Override
    public List<FlightModel> searchFlights(String destination, String date, int seatsNumber) {
        return this.flightListDAO.searchFlights(destination, date, seatsNumber);
    }

    public List<FlightModel> getFlightList() {
        return this.flightListDAO.getFlightList();
    }

    private boolean compareDateFlightWithDateNow(String date, String time, int difference) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date d = sdf.parse(date + " " + time);
        long flightTimeMS = d.getTime();
        return (flightTimeMS - Instant.now().toEpochMilli()) >= 0 &&
                (flightTimeMS - Instant.now().toEpochMilli()) <= difference;
    }

}