package services;

import models.FlightModel;

import java.util.List;

public interface FlightService {

    List<FlightModel> getFlightsListNextHours(int ms); //������� ��� ����� � ������� ����������

    FlightModel getFlightInfo(int id); //�������� ���������� � ����� �� id

    List<FlightModel> searchFlights(String destination, String date, int seatsNumber); //������� ����� �� ��������� ������

}