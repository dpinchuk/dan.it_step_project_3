package services;

import dao.UserDAOImpl;

public class UserServiceImpl implements UserService {

    private UserDAOImpl userDAO = new UserDAOImpl();

    @Override
    public boolean isUserExist(int id) {
        return this.userDAO.getUserById(id) != null;
    }

    public UserDAOImpl getUserDAO() {
        return userDAO;
    }

}