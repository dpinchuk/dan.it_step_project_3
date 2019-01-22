package controllers;

import dao.FlightDAOImpl;
import services.BookingService;
import services.FlightService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static utils.Constants.DATA_FILE_BOOKINGS;
import static utils.Constants.DATA_FILE_FLIGHTS;

public class InitDataController {

    private FlightService flightService = new FlightService();
    private BookingService bookingService = new BookingService();

    {
        this.flightService.addFileDataToDAO(getFileData(DATA_FILE_FLIGHTS));
        this.bookingService.addFileDataToDAO(getFileData(DATA_FILE_BOOKINGS));
    }

    public List<String> getFileData(String file) {
        try {
            return Files.lines(Paths.get(file)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}