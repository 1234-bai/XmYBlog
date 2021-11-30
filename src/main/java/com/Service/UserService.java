package com.Service;

import com.Dao.UserDao;
import com.Entity.User;

public class UserService {

    private final UserDao userDao = new UserDao();

    public User Login(String username, String password) {
        User user = userDao.getUser(username);
        if(user == null || user.getPassword().equals(password)){    //利用或运算符的短路特性
            return user;
        }
        return null;
    }

    public boolean Register(String name, String password) {
        return userDao.addUser(name, password) == 1;
    }

    public boolean haveUser(String username){
        User user = userDao.getUser(username);  //由于MySQL不区分大小写，所以还需再判断一下
        return user != null && user.getUsername().equals(username);
    }

}
