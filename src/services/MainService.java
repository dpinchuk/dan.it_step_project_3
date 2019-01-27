package services;

import static utils.Constants.DATE_FORMAT_REGEX;

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

    public boolean isValidDate(String date) {
        return date.matches(DATE_FORMAT_REGEX);
    }

}