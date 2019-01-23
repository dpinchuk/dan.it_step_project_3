package controllers;

import dao.FlightDAOImpl;
import services.BookingService;
import services.FlightService;
import utils.Loader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static utils.Constants.*;

public class InitDataController {

    private FlightService flightService = new FlightService();
    private BookingService bookingService = new BookingService();

    {
        Loader loader = new Loader(DATA_FILE_FLIGHTS_FROM, DATA_FILE_FLIGHTS);
//        try {
//            loader.getFileData();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

//    {
//        this.flightService.addFileDataToDAO(getFileData(DATA_FILE_FLIGHTS));
//        this.flightService.getFlightListDAO().getFlightList().forEach(System.out::println); //test!!!
//        this.bookingService.addFileDataToDAO(getFileData(DATA_FILE_BOOKINGS));
//    }

//    public List<String> getFileData(String file) {
//        try {
//            return Files.lines(Paths.get(file)).collect(Collectors.toList());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return new ArrayList<>();
//    }

    public FlightService getFlightService() {
        return flightService;
    }

    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }

    public BookingService getBookingService() {
        return bookingService;
    }

    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }
}