package services;

import dao.UserDAOImpl;
import models.UserModel;

/**
 * Service class extends MainService implements UserService
 *
 * @author Pinchuk Dmitry
 */
public class UserServiceImpl implements UserService {

    private UserDAOImpl userDAO = new UserDAOImpl();

    /**
     * Returns true/false if user exist
     *
     * @param id int
     * @return boolean
     */
    @Override
    public boolean isUserExist(int id) {
        return this.userDAO.getUserById(id) != null;
    }

    /**
     * Creates new user by data
     *
     * @param login       String
     * @param password    String
     * @param userName    String
     * @param userSurname String
     * @return UserModel
     */
    @Override
    public UserModel createUser(String login, String password, String userName, String userSurname) {
        return this.userDAO.createUser(login, password, userName, userSurname);
    }

    /**
     * Returns user by [login, password]
     *
     * @param login    String
     * @param password String
     * @return UserModel
     */
    @Override
    public UserModel getUserByLoginAndPassword(String login, String password) {
        return this.userDAO.getUserByLoginAndPassword(login, password);
    }

    /**
     * Returns user by [sessionId]
     *
     * @param sessionId int
     * @return UserModel
     */
    @Override
    public UserModel getUserBySessionId(int sessionId) {
        return this.userDAO.getUserBySessionId(sessionId);
    }

    /**
     * Returns user by [login]
     *
     * @param login String
     * @return UserModel
     */
    @Override
    public UserModel getUserByLogin(String login) {
        return this.userDAO.getUserByLogin(login);
    }

    /**
     * Writes user List to file
     * void
     */
    @Override
    public void writeUserListToFile() {
        this.userDAO.writeUserListToFile();
    }

}