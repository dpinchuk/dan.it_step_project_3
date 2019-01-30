package services;

import dao.FlightDAOImpl;
import models.FlightModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static utils.Constants.DATE_FORMAT_REGEX;
import static utils.Constants.DATE_TIME_PATTERN;

/**
 * Service class extends MainService implements FlightService
 *
 * @author Pinchuk Dmitry
 */
public class FlightServiceImpl extends MainService implements FlightService {

    private FlightDAOImpl flightDAO = new FlightDAOImpl();

    /**
     * Returns the list of flights for the specified time
     *
     * @param milliLocalDateTime int
     * @return List<FlightModel>
     */
    @Override
    public List<FlightModel> getFlightsDuringTime(int milliLocalDateTime) {
        return this.flightDAO.getFlightsDuringTime(System.currentTimeMillis(), milliLocalDateTime);
    }

    /**
     * Returns info about flight
     *
     * @param id int
     * @return String
     */
    @Override
    public String getFlightInfo(int id) {
        return this.flightDAO.getFlightById(id).toString();
    }

    /**
     * Returns true/false for flight existing
     *
     * @param flight FlightModel
     * @return boolean
     */
    @Override
    public boolean isFlightExist(FlightModel flight) {
        return this.flightDAO.isFlightExist(flight);
    }

    /**
     * Returns fkights list by data
     *
     * @param destination String
     * @param date        String
     * @param seatsNumber int
     * @return List<FlightModel>
     */
    @Override
    public List<FlightModel> getFlightByData(String destination, String date, int seatsNumber) {
        if (Pattern.compile(DATE_FORMAT_REGEX).matcher(date).matches()) {
            LocalDate localDate = LocalDate.parse(date);
            return this.flightDAO.getFlightByData(destination, localDate, seatsNumber);
        }
        return new ArrayList<>();
    }

    /**
     * Updates flight after creating/deleting (+-place) and returns true/false as result
     *
     * @param flight FlightModel
     * @param place  int
     */
    @Override
    public void updateFlight(FlightModel flight, int place) {
        this.flightDAO.updateFlight(flight, place);
    }

    /**
     * Returns a size of flights collection
     *
     * @return int
     */
    public int getFlightListSize() {
        return this.flightDAO.getFlightListSize();
    }

    public FlightModel getFlightById(int flightId) {
        return this.flightDAO.getFlightById(flightId);
    }
}