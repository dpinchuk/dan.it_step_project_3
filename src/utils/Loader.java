package utils;

import models.FlightModel;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Loader {

    private String fileFrom;
    private String fileTo;
    private int count = 0;
    private List<FlightModel> flightModelList = new ArrayList<>();

    public Loader(String fileFrom, String fileTo) {
        this.fileFrom = fileFrom;
        this.fileTo = fileTo;
        try {
            setObjectsToFile();
            getObjectsFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setObjectsToFile() throws Exception {
        Files.deleteIfExists(Paths.get(this.fileTo));
        Files.createFile(Paths.get(this.fileTo));
        ArrayList<String> list = (ArrayList<String>) Files.lines(Paths.get(this.fileFrom))
                .skip(1)
                .collect(Collectors.toList());
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.fileTo));
        for (String str : list) {
            String[] s = str.split("\\s+");
            oos.writeObject(new FlightModel(this.count++, s[0], s[1], s[2], s[3], s[4], s[5]));
        }
        oos.close();
    }

    private void getObjectsFromFile() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.fileTo));
        for (int i = 0; i < this.count; i++) {
            this.flightModelList.add((FlightModel) ois.readObject());
        }
        ois.close();
    }

    public List<FlightModel> getFlightModelList() {
        return flightModelList;
    }
}