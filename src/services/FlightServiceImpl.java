package services;

import dao.FlightDAOImpl;
import models.FlightModel;
import models.UserModel;
import utils.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static utils.Constants.*;

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
     * @param id   String
     * @param user UserModel
     * @return FlightModel
     */
    @Override
    public String getFlightInfo(String id, UserModel user) {
        FlightModel flight;
        if (!id.equals("")) {
            int idNum = -1;
            try {
                idNum = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                getException(INVALID_DATA, "User [" + user.getUserName() + " " + user.getUserSurname() + "] entered incorrect flight [id=" + id + "]");
                return "";
            }
            if (idNum > 0) {
                flight = this.flightDAO.getFlightById(idNum);
                if (flight == null) {
                    getException(SEARCH_FALSE, "User [" + user.getUserName() + " " + user.getUserSurname() + "] did not find flight by [id=" + id + "]");
                    return "";
                } else {
                    return flight.toString();
                }
            } else {
                getException(INVALID_DATA, "User [" + user.getUserName() + " " + user.getUserSurname() + "] entered incorrect [id=" + id + "]");
                return "";
            }
        } else {
            getException(INVALID_DATA, "User [" + user.getUserName() + " " + user.getUserSurname() + "] entered incorrect [id=" + id + "]");
            return "";
        }
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
     * Returns flights list by data
     *
     * @param destination String
     * @param date        String
     * @param seatsNumber int
     * @param user        UserModel
     * @return List<FlightModel>
     */
    @Override
    public List<FlightModel> getFlightByData(String destination, String date, int seatsNumber, UserModel user) {
        if (destination.equals("") || seatsNumber <= 0 || user == null) {
            getException(INVALID_DATA, INVALID_DATA + " " + "User entered invalid data during searching flights");
            return new ArrayList<>();
        }
        if (Pattern.compile(DATE_FORMAT_REGEX).matcher(date).matches()) {
            LocalDate localDate = LocalDate.parse(date);
            return this.flightDAO.getFlightByData(destination, localDate, seatsNumber);
        }
        getException(INVALID_DATA, INVALID_DATA + " " + "User entered invalid data during searching flights");
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

    /**
     * Returns flight by [id]
     *
     * @param flightId int
     * @return FlightModel
     */
    public FlightModel getFlightById(int flightId) {
        return this.flightDAO.getFlightById(flightId);
    }

    /**
     * Writes flight List to file
     */
    @Override
    public void writeFlightListToFile() {
        this.flightDAO.writeFlightListToFile();
    }

}