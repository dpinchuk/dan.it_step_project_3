package services;

import models.FlightModel;

import java.util.List;

/**
 * Interface for FlightServiceImpl
 *
 * @author Pinchuk Dmitry
 */
public interface FlightService {

    List<FlightModel> getFlightsDuringTime(int milliLocalDateTime); //выводит все рейсы в часовом промежутке ms

    String getFlightInfo(int id); //получаем информацию о рейсе по id

    List<FlightModel> getFlightByData(String destination, String date, int seatsNumber); //возвращает список рейсов по данным

    void updateFlight(FlightModel flight, int place); //обновляет количество оставшихся мест рейса

    public boolean isFlightExist(FlightModel flight);

    void writeFlightListToFile();

}