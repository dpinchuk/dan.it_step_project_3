package models;

import java.io.Serializable;

public class BookingModel implements Serializable {

    // Нужно ли создавать этот объект, если можно просто обойтись коллекцией строк и с ней работать????
    // хз, как правильно?
    private int order; //id бронирования рейса
    private int flightId; //id рейса
    private String passengerName; //имя пассажира
    private String passengerSurname; //фамилия пассажира
    private int seatsNumber; //количество мест

}