package utils;

import models.UserModel;

public interface Constants {

    String EXIT = "0";

    String DATA_FILE_FLIGHTS = "src/dataFiles/flights.txt";

    String DATA_FILE_BOOKINGS = "src/dataFiles/bookings.txt";

    String DATA_FILE_USERS = "src/dataFiles/users.txt";

    int DIFFERENCE = 24 * 60 * 60 * 1000;

    String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";

    String TIME_ZONE = "Europe/Kiev";

    String DATE_FORMAT_REGEX = "^((?:(?:1[6-9]|2[0-9])\\d{2})(-)(?:(?:(?:0[13578]|1[02])(-)31)|((0[1,3-9]|1[0-2])(-)(29|30))))$|^(?:(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00)))(-)02(-)29)$|^(?:(?:1[6-9]|2[0-9])\\d{2})(-)(?:(?:0[1-9])|(?:1[0-2]))(-)(?:0[1-9]|1\\d|2[0-8])$";

    String ERROR_FILE_IO = "Error file io: [File not exist!]";

    String FILES_CREATED_SUCCESSFULLY = "Files created successfully!";

    String SEARCH_FALSE = "Search did not return results!";

    String SEARCH_TRUE = "Ssearch returned the following results:";

    String INVALID_DATA = "Invalid data!";

    String DATA_WRITEN_SUCCESSFULLY = "Data written successfully!";

    String OPERATION_SUCCESS = "Operation success!";

    String OPERATION_ERROR = "Operation error!";

    String BREAK_ACTION = "Break action!";

    String SUCCESSFUL_AUTHORIZATION = "Successful authorization!";

    String ERROR_AUTHORIZATION_USER_IS_NOT_FOUND = "Error authorization: [User is not found!]";

    String ERROR_AUTHORIZATION_YOU_ARE_NOT_AUTHORIZED = "Error authorization: [You are not authorized!]";

    String ERROR_AUTHORIZATION_USER_IS_ALREADY_AUTHORIZED = "Error authorization: [User is already authorized!]";

    String ERROR_REGISTRATION_INVALID_INPUT_DATA = "Error registration: [Invalid input data!]";

    String ERROR_REGISTRATION_USER_IS_ALREADY_REGISTERED = "Error registration: [User is already registered!]";

    int FLIGHTS_LIMIT = 50;

    int BOOKINGS_LIMIT = 25;

    String KIEV = "Kiev";

    //String[] DIRECTIONS = {"Rome", "Gent"};
    String[] DIRECTIONS = {
            "Minsk",
            "Grodno",
            "Moscow",
            "Bratislava",
            "Budapest",
            "Rome",
            "Gent",
            "Brussels",
            "London",
            "Warsaw",
            "Prague",
            "Kosice",
            "Paris",
            "Berlin",
            "Barcelona",
            "Danzig",
            "Gdansk",
            "Naples",
            "Cairo",
            "Vladivostok",
            "Tokyo","" +
            "Beijing",
            "Sydney",
            "Dubai",
            "New-York",
            "Hong-Kong",
            "Washington"
    };

    String[] USER_LIST = {
            "sukin.mm     555555  Miron  Sukin",
            "pinchuk.da   111111  Dmitry Pinchuk",
            "dmss111278   111278  Dmitry Pinchuk"
    };

    UserModel GUEST = new UserModel(0, "guest", "guest", "Guest", "Guest");

}