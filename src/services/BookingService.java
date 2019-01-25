package services;

import models.BookingModel;

import java.util.List;

public interface BookingService {

    int createBooking(int flightId, String name, String surname, int seatsNumber); //������� ������������� ����

    boolean deleteBooking(int id); //������� ������������� ���� �� id

    List<BookingModel> getBookings(int bookingId); //�������� ��������������� ���� �� id

    List<BookingModel> getUserBookings(String name, String surname); //�������� ������ ��������������� ������ �����

    void addFileDataToDAO(List<BookingModel> list); //��������� ������, ���������� �� �����, � DAO list

}