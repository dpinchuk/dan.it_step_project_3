package utils;

import models.BookingModel;
import models.FlightModel;
import models.UserModel;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static utils.Constants.*;

/**
 * Loader class for io working
 *
 * @author Pinchuk Dmitry
 */
public class Loader {

    /**
     * If dataBase not exist - creates DB
     */
    public void generateDB() {
        if (Files.exists(Paths.get(DATA_FILE_USERS)) ||
                Files.exists(Paths.get(DATA_FILE_FLIGHTS)) ||
                Files.exists(Paths.get(DATA_FILE_BOOKINGS))) {
            try {
                if (FileChannel.open(Paths.get(DATA_FILE_USERS)).size() == 0 &&
                        FileChannel.open(Paths.get(DATA_FILE_FLIGHTS)).size() == 0 &&
                        FileChannel.open(Paths.get(DATA_FILE_BOOKINGS)).size() == 0) {
                    writeObjectListToFile();
                }
            } catch (IOException e) {
                System.out.println(ERROR_FILE_IO);
            }
        } else {
            try {
                Files.createFile(Paths.get(DATA_FILE_USERS));
                Files.createFile(Paths.get(DATA_FILE_FLIGHTS));
                Files.createFile(Paths.get(DATA_FILE_BOOKINGS));
                System.out.println(FILES_CREATED_SUCCESSFULLY);
                writeObjectListToFile();
                System.out.println(DATA_WRITEN_SUCCESSFULLY);
            } catch (IOException e) {
                System.out.println(ERROR_FILE_IO);
            }
        }
    }

    /**
     * Reads UserList from file
     *
     * @return List<UserModel>
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<UserModel> readUserListFromFile() throws IOException, ClassNotFoundException {
        List<UserModel> userList = new ArrayList<>();
        ObjectInputStream userInputStream = new ObjectInputStream(new FileInputStream(DATA_FILE_USERS));
        int userCount = (int) userInputStream.readObject();
        for (int i = 0; i < userCount; i++) {
            userList.add((UserModel) userInputStream.readObject());
        }
        return userList;
    }

    /**
     * Writes UserList to file
     *
     * @param userList List<UserModel>
     */
    public void writeUserListToFile(List<UserModel> userList) {
        ObjectOutputStream userOutputStream = null;
        try {
            userOutputStream = new ObjectOutputStream(new FileOutputStream(DATA_FILE_USERS, false));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (userOutputStream != null) {
                userOutputStream.writeObject(userList.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (UserModel user : userList) {
            try {
                if (userOutputStream != null) {
                    userOutputStream.writeObject(user);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Reads FlightList from file
     *
     * @return List<FlightModel>
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<FlightModel> readFlightListFromFile() throws IOException, ClassNotFoundException {
        List<FlightModel> flightList = new ArrayList<>();
        ObjectInputStream flightInputStream = new ObjectInputStream(new FileInputStream(DATA_FILE_FLIGHTS));
        int flightCount = (int) flightInputStream.readObject();
        for (int i = 0; i < flightCount; i++) {
            flightList.add((FlightModel) flightInputStream.readObject());
        }
        return flightList;
    }

    /**
     * Writes FlightList to file
     *
     * @param flightList List<FlightModel>
     */
    public void writeFlightListToFile(List<FlightModel> flightList) {
        ObjectOutputStream flightOutputStream = null;
        try {
            flightOutputStream = new ObjectOutputStream(new FileOutputStream(DATA_FILE_FLIGHTS, false));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (flightOutputStream != null) {
                flightOutputStream.writeObject(flightList.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (FlightModel flight : flightList) {
            try {
                if (flightOutputStream != null) {
                    flightOutputStream.writeObject(flight);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Reads BookingList from file
     *
     * @return List<BookingModel>
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<BookingModel> readBookingListFromFile() throws IOException, ClassNotFoundException {
        List<BookingModel> bookingList = new ArrayList<>();
        ObjectInputStream bookingInputStream = new ObjectInputStream(new FileInputStream(DATA_FILE_BOOKINGS));
        int bookingCount = (int) bookingInputStream.readObject();
        for (int i = 0; i < bookingCount; i++) {
            BookingModel booking = (BookingModel) bookingInputStream.readObject();
            bookingList.add(booking);
        }
        return bookingList;
    }

    /**
     * Writes BookingList to file
     *
     * @param bookingList List<BookingModel>
     */
    public void writeBookingListToFile(List<BookingModel> bookingList) {
        ObjectOutputStream bookingOutputStream = null;
        try {
            bookingOutputStream = new ObjectOutputStream(new FileOutputStream(DATA_FILE_BOOKINGS, false));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (bookingOutputStream != null) {
                bookingOutputStream.writeObject(bookingList.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (BookingModel booking : bookingList) {
            try {
                if (bookingOutputStream != null) {
                    bookingOutputStream.writeObject(booking);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Generates UserList
     *
     * @return List<UserModel>
     */
    private List<UserModel> generateUserList() {
        List<UserModel> userList = new ArrayList<>();
        int countUsers = 0;
        UserModel userModel;
        for (String user : USER_LIST) {
            String[] s = user.split("\\s+");
            userModel = new UserModel(++countUsers, s[0], s[1], s[2], s[3]);
            userList.add(userModel);
        }
        return userList;
    }

    /**
     * Generates FlightList
     *
     * @return List<FlightModel>
     */
    private List<FlightModel> generateFlightList() {
        List<FlightModel> flightList = new ArrayList<>();
        int countFlights = 0;
        int hours = 0;
        int minutes = 0;
        FlightModel flight;
        LocalDateTime dateTime;
        int seats = 0;
        int index = 0;
        Random random = new Random();
        for (int i = 0; i < FLIGHTS_LIMIT; i++) {
            dateTime = LocalDateTime.parse(LocalDateTime.now().plusHours(hours).plusMinutes(minutes).format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)), DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
            seats = random.ints(75, 100).limit(1).findFirst().getAsInt();
            index = random.ints(0, DIRECTIONS.length).limit(1).findFirst().getAsInt();
            flight = new FlightModel(
                    ++countFlights,
                    dateTime,
                    KIEV,
                    DIRECTIONS[index],
                    seats
            );
            flightList.add(flight);
            hours += 2;
            minutes += 30;
        }
        return flightList;
    }

    /**
     * Generates BookingList
     *
     * @return List<BookingModel>
     */
    private List<BookingModel> generateBookingList(List<FlightModel> flightList) {
        List<BookingModel> bookingList = new ArrayList<>();
        List<UserModel> userList = generateUserList();
        int countBookings = 0;
        BookingModel booking;
        Random random = new Random();
        for (int i = 0; i < BOOKINGS_LIMIT; i++) {
            booking = new BookingModel(++countBookings, flightList.get(random.nextInt(BOOKINGS_LIMIT)), userList.get(random.nextInt(USER_LIST.length)));
            bookingList.add(booking);
        }
        return bookingList;
    }

    /**
     * Writes userList, flightList, bookingList to file
     * void
     */
    private void writeObjectListToFile() {
        List<UserModel> userList = generateUserList();
        List<FlightModel> flightList = generateFlightList();
        List<BookingModel> bookingList = generateBookingList(flightList);
        ObjectOutputStream userOutputStream = null;
        ObjectOutputStream flightOutputStream = null;
        ObjectOutputStream bookingOutputStream = null;
        try {
            userOutputStream = new ObjectOutputStream(new FileOutputStream(DATA_FILE_USERS));
            flightOutputStream = new ObjectOutputStream(new FileOutputStream(DATA_FILE_FLIGHTS));
            bookingOutputStream = new ObjectOutputStream(new FileOutputStream(DATA_FILE_BOOKINGS));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (userOutputStream != null) {
                userOutputStream.writeObject(userList.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (UserModel user : userList) {
            try {
                Objects.requireNonNull(userOutputStream).writeObject(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            if (flightOutputStream != null) {
                flightOutputStream.writeObject(flightList.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (FlightModel fligh : flightList) {
            try {
                Objects.requireNonNull(flightOutputStream).writeObject(fligh);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            if (bookingOutputStream != null) {
                bookingOutputStream.writeObject(bookingList.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (BookingModel booking : bookingList) {
            try {
                Objects.requireNonNull(bookingOutputStream).writeObject(booking);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Objects.requireNonNull(userOutputStream).close();
            Objects.requireNonNull(flightOutputStream).close();
            Objects.requireNonNull(bookingOutputStream).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("New database generated successfully!");
    }

}