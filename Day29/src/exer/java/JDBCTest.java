package exer.java;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Mrs.An Xueying
 * 2020/6/2 11:48
 * JDBC: 使用JavaAPI连接数据库的一套操作。
 * 如果想对数据库进行操作，那么必须先获取数据库的连接
 *
 * 步骤：
 * 1. 保证mysql是正常运行的，账号，密码必须正确，使用的数据库必须存在
 * 2. 导入mysql驱动，mysql版本和驱动一定要兼容
 * 3.
 */
public class JDBCTest {
    /**
     * 获取数据库的连接
     * 如果是安装8.0的同学   ：有两个改变
     * 1.全类名 ： com.mysql.cj.jdbc.Driver
     * 2.jdbc:mysql://localhost:3306/库名/useSSL=false&serverTimezone=UTC
     *
     * 君哥的：
     * 2.jdbc:mysql://localhost:3306/库名?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
     */
    @Test
    public void test() throws SQLException {
        //1. 获取mysql驱动(Driver接口 实现类：com.mysql.jdbc.Driver  java.sql)的实例
        Driver driver = new com.mysql.jdbc.Driver();
        //2. 连接数据库
        /**
         * 数据库地址
         * jdbc:mysql  mysql协议
         * localhost  mysql地址
         * 3306 端口号
         * myemployees 数据库名
         */
        String url = "jdbc:mysql://localhost:3306/myemployees";
        //账号密码 user  password这两个是固定的，不同的框架可能不一样
        Properties p = new Properties();
        p.put("user", "root");
        p.put("password", "root");

        Connection connect = driver.connect(url, p);
        //拿到地址即可操作数据库了
        System.out.println(connect);
    }

    /**
     * 方式2  DriverManager 管理Driver
     */
    @Test
    public void test1() throws SQLException {
        //1. 创建Driver的实例
        Driver driver = new com.mysql.jdbc.Driver();
        //2. 使用DriverManager进行注册
        DriverManager.registerDriver(driver);
        //3. 获取Connection的实例
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myemployees", "root", "root");
        System.out.println(connection);
    }

    @Test
    public void test2(){
        try {
            Class<?> name = Class.forName("exer.java.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 改良：只有mysql有--静态代码块启用
     * 对于使用DriverManager获取连接进行改良
     *
     * 省略了：
     * 1. 创建Driver的实例
     * 2. DriverManager的注册
     * 这两步在com.mysql.jdbc.Driver的类中的静态代码块中执行了
     */
    @Test
    public void test3() throws ClassNotFoundException, SQLException {
        //目的是为了，让Driver中的静态代码块执行
        Class.forName("com.mysql.jdbc.Driver");
        //通过DriverManager获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myemployees", "root", "root");
        System.out.println(connection);
    }

    /**
     * 改良终极：配置文件+DriverManager+静态代码块
     *
     * 静态文件和本类放在同一src目录下即可，如：
     * 配置文件位置：D:\developer_tools\200421JavaSE\Day29\src\jdbc.properties
     * 本类位置：D:\developer_tools\200421JavaSE\Day29\src\exer\JDBCTest.java
     */
    @Test
    public void test4() throws IOException, SQLException, ClassNotFoundException {
        //读取配置文件
        Properties properties = new Properties();
        InputStream is = JDBCTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        properties.load(is);
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        //验证
        System.out.println(url);
        System.out.println(driver);
        System.out.println(user);
        System.out.println(password);
        //目的是为了，让Driver中的静态代码块执行
        Class.forName(driver);
        //通过DriverManager获取连接
        Connection connection = DriverManager.getConnection(url,user,password);
        System.out.println(connection);
    }
}

class Person{
    //静态代码块，随着类的加载而加载，且只加载一次
    static {
        System.out.println("加载");
    }
}
