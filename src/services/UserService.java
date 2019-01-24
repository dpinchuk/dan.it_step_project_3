package services;

import dao.UserDAO;
import dao.UserDAOImpl;
import models.UserModel;

import java.util.List;

public class UserService implements UserDAO {

    private UserDAOImpl userDAO = new UserDAOImpl();

    @Override
    public List<UserModel> getUserList() {
        return this.userDAO.getUserList();
    }

    @Override
    public UserModel getUserById(int id) {
        return this.userDAO.getUserById(id);
    }

    @Override
    public UserModel getUserByLogin(String login) {
        return this.userDAO.getUserByLogin(login);
    }

}