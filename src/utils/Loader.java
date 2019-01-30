package utils;

import models.FlightModel;
import models.UserModel;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static utils.Constants.*;

/**
 * Loader class for io working
 * @author Pinchuk Dmitry
 */
public class Loader {

    private int countFlights = 0;
    private int countUsers = 0;

    public List<FlightModel> getFlightModelList() throws Exception {
        return getFlightsToList();
    }

    public List<UserModel> getUserModelList() throws Exception {
        return getUsersToList();
    }

    /**
     * Generates flight objects and writes into file
     * @throws Exception IOException
     */
    private void setFlightsToList() {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(DATA_FILE_FLIGHTS_TO));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int hours = 0;
        int minutes = 0;
        FlightModel flight;
        LocalDateTime dateTime;
        int seats = 0;
        int index = 0;
        for (int i = 0; i < FLIGHTS_LIMIT; i++) {
            dateTime = LocalDateTime.parse(LocalDateTime.now().plusHours(0).plusMinutes(minutes).format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)), DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
            seats = new Random().ints(50, (100)).limit(1).findFirst().getAsInt();
            index = new Random().ints(0, DIRECTIONS.length).limit(1).findFirst().getAsInt();
            flight = new FlightModel(
                    ++this.countFlights,
                    dateTime,
                    KIEV,
                    DIRECTIONS[index],
                    seats
            );
            try {
                objectOutputStream.writeObject(flight);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //hours++;
            minutes += 30;
        }
        try {
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates user objects and writes into file
     * @throws Exception
     */
    private void setUsersFromListToFile() throws Exception {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(DATA_FILE_USERS_TO));
        UserModel userModel;
        for (String user : USER_LIST) {
            String[] s = user.split("\\s+");
            userModel = new UserModel(++this.countUsers, s[0], s[1], s[2], s[3]);
            objectOutputStream.writeObject(userModel);
        }
        objectOutputStream.close();
    }

    /**
     *
     * @return
     * @throws Exception
     */
    private List<FlightModel> getFlightsToList() throws Exception {
        setFlightsToList();
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(DATA_FILE_FLIGHTS_TO));
        List<FlightModel> flightModelList = new ArrayList<>();
        for (int i = 0; i < this.countFlights; i++) {
            flightModelList.add((FlightModel) objectInputStream.readObject());
        }
        objectInputStream.close();
        return flightModelList;
    }

    private List<UserModel> getUsersToList() throws Exception {
        setUsersFromListToFile();
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(DATA_FILE_USERS_TO));
        List<UserModel> userModelList = new ArrayList<>();
        for (int i = 0; i < this.countUsers; i++) {
            userModelList.add((UserModel) objectInputStream.readObject());
        }
        objectInputStream.close();
        return userModelList;
    }

}