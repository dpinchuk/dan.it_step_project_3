package view;

import controllers.BookingController;
import controllers.FlightController;
import controllers.UserController;
import models.FlightModel;
import models.UserModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static utils.Constants.EXIT;

public class ViewConsole {

    private FlightController flightController = new FlightController();
    private BookingController bookingController = new BookingController();
    private UserController userController = new UserController();

    public void run() {
        List<FlightModel> flightList = this.flightController.getFlightList();
        List<UserModel> userList = this.userController.getUserList();

        flightList.forEach(System.out::println);
        userList.forEach(System.out::println);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String select = "";
        while (true) {
            System.out.println("[Service of a flight booking]");
            System.out.print("Choose action: ");
            System.out.println("[0] - Exit");
            System.out.println("[1] - Online scoreboard");
            System.out.println("[2] - View flight information");
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
                // вызываем контроллер, в нем сервисЮ а в нем дао
                break;
            case "2":
                // вызываем контроллер, в нем сервисЮ а в нем дао
                break;
            case "3":
                // вызываем контроллер, в нем сервисЮ а в нем дао
                break;
            case "4":
                // вызываем контроллер, в нем сервисЮ а в нем дао
                break;
            case "5":
                // вызываем контроллер, в нем сервисЮ а в нем дао
                break;
            case "6":
                // вызываем контроллер, в нем сервисЮ а в нем дао
                break;
            default:
                break;
        }
    }

}