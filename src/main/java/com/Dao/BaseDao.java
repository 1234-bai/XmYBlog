package com.Dao;

import com.Util.CONSTANTS;

import java.io.*;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;

public class BaseDao {

    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    static {
        BufferedReader bufferedReader = null;
        Properties properties = new Properties();
        try {
            String path = Objects.requireNonNull(BaseDao.class.getClassLoader().getResource("SQL.properties")).getPath();
            bufferedReader = new BufferedReader(new FileReader(path));
            properties.load(bufferedReader);
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("读取不到文件");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getClass() + " 其他异常！");
        }
        //获取key对应的value值
        URL = properties.getProperty("URL", CONSTANTS.DB_DATA.URL);
        USER = properties.getProperty("USER", CONSTANTS.DB_DATA.USER);
        PASSWORD = properties.getProperty("PASSWORD", CONSTANTS.DB_DATA.PASSWORD);
        String DRIVER_CLASS = properties.getProperty("DRIVER_CLASS", CONSTANTS.DB_DATA.DRIVER_CLASS);

        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("can't find sql driver!");
        }

    }

    public static Connection getConnection() {
        if(connection == null){
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("加载驱动，建立联系失败！！");
            }
        }
        return connection;
    }

    /**
     *
     * @param connection 同数据库建立的链接
     * @param sql SQL预编译语句
     * @param params SQL预编译语句的参数数组
     * @return 受影响的数据库条数
     */
    public static int executeUpdate(Connection connection, String sql, Object[] params) {
        int temp = -1;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            temp = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     *
     * @param connection 同数据库建立的链接
     * @param sql SQL预编译语句
     * @param params SQL预编译语句的参数数组
     * @return 查询的结果集
     */
    public static ResultSet executeQuery(Connection connection, String sql, Object[] params){
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static void close(){
        try {
            if(resultSet != null){
                resultSet.close();
                resultSet = null;
            }
            if (preparedStatement != null){
                preparedStatement.close();
                preparedStatement = null;
            }
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("DAO层关闭失败！");
        }
    }

}
