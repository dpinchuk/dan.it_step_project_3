package services;

import models.BookingModel;

import java.util.List;

public interface BookingService {

    String getBookingInfo(int id); //�������� ���������� � �������������� ����� �� id

    boolean isBookingExist(int id); //��������� ������������� �������������� ����� �� id

}