package models;

import java.io.Serializable;
import java.util.Objects;

public class BookingModel implements Serializable {

    private int id; //id бронирования рейса
    private int flightId; //id рейса
    private String name; //имя пассажира
    private String surname; //фамилия пассажира
    private int seatsRemaining; //количество оставшихся мест
    private int userHash; //принадлежность конкретному пользователю

    public BookingModel(int id, int flightId, String name, String surname, int seatsRemaining, int userHash) {
        this.id = id;
        this.flightId = flightId;
        this.name = name;
        this.surname = surname;
        this.seatsRemaining = seatsRemaining;
        this.userHash = userHash;
    }

    public int getId() {
        return id;
    }

    public int getFlightId() {
        return flightId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getSeatsRemaining() {
        return seatsRemaining;
    }

    public int getUserHash() {
        return userHash;
    }

    @Override
    public String toString() {
        return id +
                "\t\t\t\t" + flightId +
                "\t\t\t\t" + name +
                "\t\t\t\t" + surname +
                "\t\t\t\t" + seatsRemaining +
                "\t\t\t\t" + userHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingModel that = (BookingModel) o;
        return userHash == that.userHash &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, userHash);
    }

}