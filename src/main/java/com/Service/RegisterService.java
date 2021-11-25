package com.Service;

import com.Dao.UserDao;

public class RegisterService {

    public boolean Register(String name, String password) {
        return UserDao.addUser(name, password) == 1;
    }
}
