package utils;

import models.FlightModel;
import models.UserModel;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static utils.Constants.*;

public class Loader {

    private int countFlights = 0;
    private int countUsers = 0;

    private void setFlightsFromListToFile() throws Exception {
        Files.deleteIfExists(Paths.get(DATA_FILE_FLIGHTS_TO));
        Files.createFile(Paths.get(DATA_FILE_FLIGHTS_TO));
        List<String> flightList = Files.lines(Paths.get(DATA_FILE_FLIGHTS_FROM))
                .skip(1)
                .collect(Collectors.toList());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(DATA_FILE_FLIGHTS_TO));
        for (String str : flightList) {
            String[] s = str.split("\\s+");
            objectOutputStream.writeObject(new FlightModel(this.countFlights++, s[0], s[1], s[2], s[3], Integer.parseInt(s[4]), s[5]));
        }
        objectOutputStream.close();
    }

    private void setUsersFromListToFile() throws Exception {
        Files.deleteIfExists(Paths.get(DATA_FILE_USERS_TO));
        Files.createFile(Paths.get(DATA_FILE_USERS_TO));
        List<String> userList = Files.lines(Paths.get(DATA_FILE_USERS_FROM))
                .skip(1)
                .collect(Collectors.toList());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(DATA_FILE_USERS_TO));
        for (String str : userList) {
            String[] s = str.split("\\s+");
            objectOutputStream.writeObject(new UserModel(this.countUsers++, s[0], s[1]));
        }
        objectOutputStream.close();
    }

    private List<FlightModel> getFlightsFromFileToList() throws Exception {
        setFlightsFromListToFile();
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(DATA_FILE_FLIGHTS_TO));
        List<FlightModel> flightModelList = new ArrayList<>();
        for (int i = 0; i < this.countFlights; i++) {
            flightModelList.add((FlightModel) objectInputStream.readObject());
        }
        objectInputStream.close();
        return flightModelList;
    }

    private List<UserModel> getUsersFromFileToList() throws Exception {
        setUsersFromListToFile();
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(DATA_FILE_USERS_TO));
        List<UserModel> userModelList = new ArrayList<>();
        for (int i = 0; i < this.countUsers; i++) {
            userModelList.add((UserModel) objectInputStream.readObject());
        }
        objectInputStream.close();
        return userModelList;
    }

    public List<FlightModel> getFlightModelList() throws Exception {
        return getFlightsFromFileToList();
    }

    public List<UserModel> getUserModelList() throws Exception {
        return getUsersFromFileToList();
    }

}