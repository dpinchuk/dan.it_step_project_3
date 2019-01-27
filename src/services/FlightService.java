package services;

import models.FlightModel;

import java.util.List;

public interface FlightService {

    List<FlightModel> getFlightsListNextHours(int ms); //������� ��� ����� � ������� ���������� ms

    String getFlightInfo(int id); //�������� ���������� � ����� �� id

    boolean isFlightExist(int id); //��������� ������������� ����� �� id

}