package com.Service;

import com.Dao.UserDao;
import com.Entity.User;

public class UserService {

    private final UserDao userDao = new UserDao();

    public int Login(String username, String password) {
        User user = userDao.getUser(username);
        if (user == null) {
            return -1;
        }
        return user.getPassword().equals(password) ? user.getUserID() : -2;
    }

    public boolean Register(String name, String password) {
        return userDao.addUser(name, password) == 1;
    }
}
