package services;

import models.FlightModel;

import java.util.List;

/**
 * Interface for FlightServiceImpl
 *
 * @author Pinchuk Dmitry
 */
public interface FlightService {

    /**
     * @param milliLocalDateTime int
     * @return List<FlightModel>
     */
    List<FlightModel> getFlightsDuringTime(int milliLocalDateTime); //выводит все рейсы в часовом промежутке ms

    /**
     * @param id int
     * @return String
     */
    String getFlightInfo(int id); //получаем информацию о рейсе по id

    /**
     * @param destination String
     * @param date        String
     * @param seatsNumber int
     * @return List<FlightModel>
     */
    List<FlightModel> getFlightByData(String destination, String date, int seatsNumber); //возвращает список рейсов по данным

    /**
     * @param flight FlightModel
     * @param place  void
     */
    void updateFlight(FlightModel flight, int place); //обновляет количество оставшихся мест рейса

    /**
     * @param flight FlightModel
     * @return boolean
     */
    boolean isFlightExist(FlightModel flight);

    /**
     * void
     */
    void writeFlightListToFile();

}