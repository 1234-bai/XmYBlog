package com.Dao;


import com.Entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public User getUser(String username) {
        User user = null;
        String sql = "select * from users where username = ?";
        ResultSet resultSet = BaseDao.executeQuery(BaseDao.getConnection(), sql, new Object[]{username});
        try{
            if(resultSet!=null && resultSet.next()){
                user = new User();
                user.setUserID(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
            }
            BaseDao.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    /**
     * @author qianxiaoyi
     * @return 返回正数说明正常意义是受改变的行数，返回0说明SQL表中信息插入不成功。返回-1说明出错
    */
    public static int addUser(String username, String password){
        String sql = "insert into myblog.users(username, password) values(?, ?)";
        int influencedStatements = BaseDao.executeUpdate(BaseDao.getConnection(), sql, new String[]{username, password});
        BaseDao.close();
        return influencedStatements;
    }

    public static int deleteUser(int userID, String username){
        return -1;
    }
}