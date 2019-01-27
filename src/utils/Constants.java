package utils;

public interface Constants {

    String EXIT = "0";

    String DATA_FILE_FLIGHTS_FROM = "src/dataFiles/flightsFrom.txt";
    String DATA_FILE_FLIGHTS_TO = "src/dataFiles/flightsTo.txt";

    String DATA_FILE_BOOKINGS = "src/dataFiles/bookingsTo.txt";

    String DATA_FILE_USERS_FROM = "src/dataFiles/usersFrom.txt";
    String DATA_FILE_USERS_TO = "src/dataFiles/usersTo.txt";

    int DIFFERENCE = 24 * 60 * 60 * 1000;

    String DATE_PATTERN = "yyyy-MM-dd";

    String DATE_FORMAT_REGEX = "^((?:(?:1[6-9]|2[0-9])\\d{2})(-)(?:(?:(?:0[13578]|1[02])(-)31)|((0[1,3-9]|1[0-2])(-)(29|30))))$|^(?:(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00)))(-)02(-)29)$|^(?:(?:1[6-9]|2[0-9])\\d{2})(-)(?:(?:0[1-9])|(?:1[0-2]))(-)(?:0[1-9]|1\\d|2[0-8])$";

    String TIME_PATTERN = "hh:mm";

    String SEARCH_NOTHING = "Search did not return results!";

    String INVALID_DATA = "Invalid data!";

    String OPERATION_SUCCESS = "Operation success!";

    String OPERATION_ERROR = "Operation error!";

    String BREAK_ACTION = "Break action!";

    String ERROR_AUTHORIZATION_USER_IS_NOT_FOUND = "Error authorization: [User is not found!]";

    String ERROR_AUTHORIZATION_YOU_ARE_NOT_AUTHORIZED = "Error authorization: [You are not authorized!]";

    String ERROR_AUTHORIZATION_USER_IS_ALREADY_AUTHORIZED = "Error authorization: [User is already authorized!]";

}