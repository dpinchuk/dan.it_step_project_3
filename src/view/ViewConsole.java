package view;

import controllers.BookingController;
import controllers.FlightController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ViewConsole {

    private BookingController bookingController;
    private FlightController flightController;
    private final String EXIT = "0";

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String select = "";
        while (true) {
            System.out.println("[Service of a flight booking]");
            System.out.print("Choose action: ");
            System.out.println("[0] - Exit");
            System.out.println("[1] - Online scoreboard");
            System.out.println("[2] - 12View flight information");
            System.out.println("[3] - Flight search and booking");
            System.out.println("[4] - Cancel booking");
            System.out.println("[5] - My flights");
            System.out.println("[6] - Exit");
            try {
                select = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (select.equals(EXIT)) {
                break;
            } else {
                select(select);
            }
        }
    }

    private void select(String select) {
        switch (select) {
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                break;
            case "6":
                break;
        }
    }

}