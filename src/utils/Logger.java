package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

import static utils.Constants.DATA_FILES_LOG;

public class Logger {

    public void info(String message) {
        saveDataToFile(LocalDateTime.now() + " - " + message + "\n");
    }

    public void error(String message) {
        saveDataToFile("[DEBUG]/[ERROR] - " + message + "\n");
    }

    private void saveDataToFile(String message) {
        try {
            Files.write(Paths.get(DATA_FILES_LOG), message.getBytes(), StandardOpenOption.APPEND);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}