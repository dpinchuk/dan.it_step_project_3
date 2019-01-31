package dao;

import models.UserModel;
import utils.Loader;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * DAO class implements interface UserDAO
 *
 * @author Pinchuk Dmitry
 */
public class UserDAOImpl implements UserDAO {

    private List<UserModel> userList;
    private Loader loader = new Loader();

    public UserDAOImpl() {
        try {
            this.userList = this.loader.readUserListFromFile();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Implements UserModel getUserById(int id)
     *
     * @param id int
     * @return UserModel
     */
    @Override
    public UserModel getUserById(int id) {
        return this.userList
                .stream()
                .filter(e ->
                        e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Implements
     *
     * @param login    String
     * @param password String
     * @return UserModel
     */
    @Override
    public UserModel getUserByLoginAndPassword(String login, String password) {
        return this.userList
                .stream()
                .filter(e ->
                        (e.getLogin().equals(login) &&
                                e.getPassword().equals(password)))
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns user by [sessionId]
     *
     * @param sessionId int
     * @return UserModel
     */
    @Override
    public UserModel getUserBySessionId(int sessionId) {
        return this.userList
                .stream()
                .filter(e -> e.hashCode() == sessionId)
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns user by login [Only for check during registration!]
     *
     * @param login String
     * @return UserModel
     */
    @Override
    public UserModel getUserByLogin(String login) {
        return this.userList
                .stream()
                .filter(e -> e.getLogin().equals(login))
                .findFirst()
                .orElse(null);
    }

    @Override
    public UserModel createUser(String login, String password, String userName, String userSurname) {
        UserModel userModel = this.userList
                .stream()
                .max(Comparator.comparing(UserModel::getId))
                .get();
        UserModel newUser = new UserModel(userModel.getId() + 1, login, password, userName, userSurname);
        this.userList.add(newUser);
        return newUser;
    }

    @Override
    public List<UserModel> getUserList() {
        return this.userList;
    }

    @Override
    public void writeUserListToFile() {
        this.loader.writeUserListToFile(this.userList);
    }

}