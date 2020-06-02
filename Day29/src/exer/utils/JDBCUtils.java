package exer.utils;

import exer.java.JDBCTest;

import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.sql.*;
import java.util.Properties;

/**
 * @author Mrs.An Xueying
 * 2020/6/2 14:37
 */
public class JDBCUtils {
    private static String url = null;
    private static String driver = null;
    private static String user = null;
    private static String password = null;
    private static InputStream is = null;

    static {
        try {
            //读取配置文件
            Properties properties = new Properties();
            is = JDBCTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(is);
            url = properties.getProperty("url");
            driver = properties.getProperty("driver");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    /**
     * 获取数据库的连接
     */
    public static Connection getConnection() {
        Connection connection =null;
        //目的是为了，让Driver中的静态代码块执行
        try {
            Class.forName(driver);
            //通过DriverManager获取连接
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(Connection connection, PreparedStatement ps){
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (ps!=null){
            try {
                ps.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public static void close(Connection connection, PreparedStatement ps, ResultSet rs){
        close(connection, ps);
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }
}
