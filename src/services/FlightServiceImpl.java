package services;

import dao.FlightDAOImpl;
import models.FlightModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static utils.Constants.DATE_TIME_PATTERN;

public class FlightServiceImpl extends MainService implements FlightService {

    private FlightDAOImpl flightDAO = new FlightDAOImpl();


    @Override
    public List<FlightModel> getFlightsListNextHours(int milliFlight) {
        return this.flightDAO.getFlightsListNextHours(System.currentTimeMillis(), milliFlight);
    }

    @Override
    public String getFlightInfo(int id) {
        return this.flightDAO.getFlightById(id).toString();
    }

    @Override
    public boolean isFlightExist(int id) {
        return this.flightDAO.getFlightById(id) != null;
    }

    @Override
    public List<FlightModel> getFlightByData(String destination, String date, int seatsNumber) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_PATTERN);
        Date d = null;
        long flightTimeMS = 0;
        try {
            d = format.parse(date);
            flightTimeMS = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this.flightDAO.getFlightByData(destination, flightTimeMS, seatsNumber);
    }

    @Override
    public boolean updateFlightOccupiedPlaces(int[] flightIdAndNumberPlaces) {
        return this.flightDAO.updateFlightOccupiedPlaces(flightIdAndNumberPlaces);
    }

    public int getFlightListSize() {
        return this.flightDAO.getFlightListSize();
    }

}