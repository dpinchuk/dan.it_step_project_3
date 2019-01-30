package dao;

import models.UserModel;
import utils.Loader;

import java.util.List;

/**
 * DAO class implements interface UserDAO
 *
 * @author Pinchuk Dmitry
 */
public class UserDAOImpl implements UserDAO {

    private List<UserModel> userList;

    public UserDAOImpl() {
        try {
            this.userList = new Loader().getUserModelList();
        } catch (Exception e) {
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
     * Returns user list
     *
     * @return List<UserModel>
     */
    public List<UserModel> getUserList() {
        return this.userList;
    }

}