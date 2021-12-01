package com.Dao;


import com.Entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends BaseDao{

    public User getUser(String username) {
        User user = null;
        String sql = "select * from users where username = ?";
        ResultSet resultSet = executeQuery(getConnection(), sql, new Object[]{username});
        try{
            if(resultSet!=null && resultSet.next()){
                user = new User();
                user.setUserID(resultSet.getInt("userID"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setNickname(resultSet.getString("nickname"));
                user.setAvatarType(resultSet.getString("avatarType"));
            }
            close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    /**
     * @author qianxiaoyi
     * @return 返回正数说明正常意义是受改变的行数，返回0说明SQL表中信息插入不成功。返回-1说明出错
    */
    public int addUser(String username, String password){
        String sql = "insert into myblog.users(username, password) values(?, ?)";
        int influencedStatements = executeUpdate(getConnection(), sql, new String[]{username, password});
        close();
        return influencedStatements;
    }

    public int deleteUser(int userID, String username){
        return -1;
    }
}
