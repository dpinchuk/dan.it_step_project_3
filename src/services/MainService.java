package services;

import utils.Exceptions;
import utils.Logger;

import java.util.regex.Pattern;

import static utils.Constants.DATE_FORMAT_REGEX;

/**
 * Main class for services
 *
 * @author Pinchuk Dmitry
 */
public class MainService {

    private Logger logger = new Logger();

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
     * Generate new exceptions
     *
     * @param message String
     * @param logText logText
     */
    public void getException(String message, String logText) {
        try {
            throw new Exceptions(message);
        } catch (Exceptions e) {
            System.out.println(e.getMessage());
            this.logger.error(logText);
        }
    }

    /**
     * Checks for valid date
     *
     * @param date String
     * @return boolean
     */
    public boolean isDateValid(String date) {
        return Pattern.compile(DATE_FORMAT_REGEX).matcher(date).matches();
    }

}