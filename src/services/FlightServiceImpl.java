package services;

import dao.FlightDAOImpl;
import models.FlightModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FlightServiceImpl extends MainService implements FlightService {

    private FlightDAOImpl flightDAO = new FlightDAOImpl();

    @Override
    public List<FlightModel> getFlightsListNextHours(int ms) {
        return this.flightDAO.getFlightList()
                .stream()
                .filter(e -> {
                    try {
                        return compareDateFlightWithDateNow(e.getDate(), e.getTime(), ms);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public String getFlightInfo(int id) {
        return this.flightDAO.getFlightById(id).toString();
    }

    @Override
    public boolean isFlightExist(int id) {
        return this.flightDAO.getFlightById(id) != null;
    }

    public FlightDAOImpl getFlightDAO() {
        return flightDAO;
    }

    private boolean compareDateFlightWithDateNow(String date, String time, int difference) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date d = sdf.parse(date + " " + time);
        long flightTimeMS = d.getTime();
        return (flightTimeMS - Instant.now().toEpochMilli()) >= 0 &&
                (flightTimeMS - Instant.now().toEpochMilli()) <= difference;
    }

}