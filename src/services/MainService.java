package services;

import java.util.regex.Pattern;

import static utils.Constants.DATE_FORMAT_REGEX;

/**
 * Main class for services
 *
 * @author Pinchuk Dmitry
 */
public class MainService {

    /**
     * Checks for number > 0
     *
     * @param number int
     * @return boolean
     */
    public boolean isValidNumber(int number) {
        return number > 0;
    }

    /**
     * Checks for valid string
     *
     * @param str String
     * @return boolean
     */
    public boolean isValidString(String str) {
        //TODO XSS-inj, SQL-inj, CSRF-inj
        return str != null && !str.equals("") && str.length() < 32;
    }

    /**
     * Checks for valid date
     *
     * @param date String
     * @return boolean
     */
    private boolean isDateValid(String date) {
        return Pattern.compile(DATE_FORMAT_REGEX).matcher(date).matches();
    }

}