package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

import static utils.Constants.DATA_FILES_LOG;

/**
 * Logger class
 *
 * @author Pinchuk Dmitry
 */
public class Logger {

    public Logger() {
        if (!Files.exists(Paths.get(DATA_FILES_LOG))) {
            try {
                throw new Exceptions("Log file not found!");
            } catch (Exceptions exceptions) {
                System.out.println(exceptions.getMessage());
                try {
                    Files.createFile(Paths.get(DATA_FILES_LOG));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Saves info about user's actions
     *
     * @param message String
     */
    public void info(String message) {
        saveDataToFile(LocalDateTime.now() + " - " + message + "\n");
    }

    /**
     * Saves messages error about user's actions
     *
     * @param message String
     */
    public void error(String message) {
        saveDataToFile("[DEBUG]/[ERROR] - " + message + "\n");
    }

    /**
     * Save data into file
     *
     * @param message String
     */
    private void saveDataToFile(String message) {
        try {
            Files.write(Paths.get(DATA_FILES_LOG), message.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}