package com.Service;

import com.Dao.UserDao;
import com.Entity.User;

public class LoginService{

    public int Login(String username, String password) {
        User user = new UserDao().getUser(username);
        if (user == null) {
            return -1;
        }
        return user.getPassword().equals(password) ? user.getUserID() : -2;
    }


}
