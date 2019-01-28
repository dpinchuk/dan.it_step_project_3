package dao;

import models.FlightModel;

import java.util.List;

/**
 * Interface for FlightDAOImpl
 * @author Pinchuk Dmitry
 */
public interface FlightDAO {

    /**
     * Return flight by [id]
     * @param id int
     * @return FlightModel
     */
    FlightModel getFlightById(int id); //возвращает рейс по id

    /**
     * Return List<FlightModel> by destination, date, seatsNumber
     * @param destination String
     * @param dateMilli long
     * @param seatsNumber int
     * @return List<FlightModel>
     */
    List<FlightModel> getFlightByData(String destination, long dateMilli, int seatsNumber); //находит рейсы по указанным данным

    /**
     * Updates flight if booking was made
     * @param flightIdAndNumberPlaces boolean
     * @return boolean true/false
     */
    boolean updateFlightOccupiedPlaces(int[] flightIdAndNumberPlaces); //изменяет количество занятых мест рейса

    List<FlightModel> getFlightsListNextHours(long milliCurent, long milliFlight);

}