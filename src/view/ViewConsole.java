package view;

import controllers.BookingController;
import controllers.FlightController;
import controllers.InitDataController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static utils.Constants.EXIT;

public class ViewConsole {

    private BookingController bookingController;
    private FlightController flightController;

    public void run() {
        initData();
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

    private void initData() {
        InitDataController initDataController = new InitDataController();
    }

    private void select(String select) {
        switch (select) {
            case "1":
                // вызываем контроллер, а в нем дао
                break;
            case "2":
                // вызываем контроллер, а в нем дао
                break;
            case "3":
                // вызываем контроллер, а в нем дао
                break;
            case "4":
                // вызываем контроллер, а в нем дао
                break;
            case "5":
                // вызываем контроллер, а в нем дао
                break;
            case "6":
                // вызываем контроллер, а в нем дао
                break;
            default:
                break;
        }
    }

}