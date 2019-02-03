package dao;

import models.FlightModel;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface for FlightDAOImpl
 *
 * @author Pinchuk Dmitry
 */
public interface FlightDAO {

    /**
     * Return flight by [id]
     *
     * @param id int
     * @return FlightModel
     */
    FlightModel getFlightById(int id); //возвращает рейс по id

    /**
     * @param destination String
     * @param date        LocalDate
     * @param seatsNumber int
     * @return List<FlightModel>
     */
    List<FlightModel> getFlightByData(String destination, LocalDate date, int seatsNumber); //находит рейсы по указанным данным (направление, дата, количество мест)

    /**
     * @param flight FlightModel
     * @param place  int
     */
    void updateFlight(FlightModel flight, int place); //изменяет количество занятых мест рейса на +-1

    /**
     * @param milliCurrent long
     * @param milliFlight  long
     * @return List<FlightModel>
     */
    List<FlightModel> getFlightsDuringTime(long milliCurrent, long milliFlight); //возвращает список рейсов в ближайшее время {24 часа по дефолту}

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