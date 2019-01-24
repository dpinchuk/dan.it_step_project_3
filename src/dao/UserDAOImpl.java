package dao;

import models.UserModel;
import utils.Loader;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    private List<UserModel> userList;

    public UserDAOImpl() {
        try {
            this.userList = new Loader().getUserModelList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UserModel> getUserList() {
        return this.userList;
    }

    @Override
    public UserModel getUserById(int id) {
        return null;
    }

    @Override
    public UserModel getUserByLogin(String login) {
        return null;
    }

}