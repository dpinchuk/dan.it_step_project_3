package dao;

import models.UserModel;
import utils.Loader;

import java.util.List;
import java.util.NoSuchElementException;

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
        try {
            return this.userList
                    .stream()
                    .filter(e ->
                            e.getId() == id)
                    .findFirst()
                    .get();
        } catch (NoSuchElementException e) {
        }
        return null;
    }

    @Override
    public UserModel getUserByLogin(String login) {
        try {
            return this.userList
                    .stream()
                    .filter(e ->
                            e.getLogin() == login)
                    .findFirst()
                    .get();
        } catch (NoSuchElementException e) {
        }
        return null;
    }

    @Override
    public UserModel getUserByLoginAndPassword(String[] userLoginAndPassword) {
        try {
            return this.userList
                    .stream()
                    .filter(e ->
                            (e.getLogin().equals(userLoginAndPassword[0]) &&
                                    e.getPassword().equals(userLoginAndPassword[1])))
                    .findFirst()
                    .get();
        } catch (NoSuchElementException e) {
        }
        return null;
    }

}