package services;

import java.util.regex.Pattern;

import static utils.Constants.DATE_FORMAT_REGEX;

/**
 * Main class for services
 *
 * @author Pinchuk Dmitry
 */
public class MainService {

    public boolean isValidNumber(int number) {
        if (number > 0) {
            return true;
        }
        return false;
    }

    public boolean isValidString(String str) {
        if (str != null && !str.equals("") && str.length() < 32) {
            //TODO XSS-inj, SQL-inj, CSRF-inj
            return true;
        }
        return false;
    }

    private boolean isDateValid(String date) {
        return Pattern.compile(DATE_FORMAT_REGEX).matcher(date).matches();
    }

}