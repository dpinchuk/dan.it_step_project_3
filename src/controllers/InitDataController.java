package controllers;

import services.BookingService;
import services.FlightService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InitDataController {

    private FlightService flightService = new FlightService();
    private BookingService bookingService = new BookingService();

    public List<String> getFileData(String file) {
        try {
            return Files.lines(Paths.get(file)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public boolean addDataToFlightListDAO(List<String> list) {
        if (list.size() != 0) {
            this.flightService.addFileDataToDAO(list);
            return true;
        }
        return false;
    }

    public boolean addDataToBookingListDAO(List<String> list) {
        if (list.size() != 0) {
            this.bookingService.addFileDataToDAO(list);
            return true;
        }
        return false;
    }

}