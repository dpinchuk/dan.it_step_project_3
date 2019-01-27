package dao;

import models.UserModel;

import java.util.List;

public interface UserDAO {

    List<UserModel> getUserList();

    UserModel getUserById(int id);

    UserModel getUserByLogin(String login);

    UserModel getUserByLoginAndPassword(String[] userLoginAndPassword);

}