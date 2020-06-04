#  一 JDBC概述

1.使用JavaAPI连接数据库的一套操作。

2.JDBC(Java Database Connectivity)是一个**独立于特定数据库管理系统**、**通用的****SQL****数据库存取和操作的公共接口**（一组API），定义了用来访问数据库的标准Java类库，（java.sql,javax.sql）使用这个类库可以以一种标准的方法、方便地访问数据库资源



# 二 获取数据库的连接

## 1.获取数据库连接前要注意的事情

①首先保证mysql是正常运行的。
②导入mysql驱动 - mysql版本和mysql驱动一定要兼容
    创建一个目录libs将驱动粘贴进来
    右键 Add as Library
③保证mysql的账号和密码必须正确。使用的数据库必须存在。



## 2. 获取的操作

```java
/*
      获取数据库的连接
     */
    @Test
    public void test() throws SQLException {
        //1.获取mysql驱动(Driver)的实例
        Driver driver = new com.mysql.jdbc.Driver();
        //2.连接数据库
        /*
            jdbc:mysql:  协议
            localhost : mysql的地址
            3306 : 数据库的端口号
            demo2 : 数据库的名字
         */
        String url="jdbc:mysql://localhost:3306/demo2";//连接数据库的地址。
        Properties p = new Properties();
        //注意user,password的名字是死的。对应的value值根据你自己的账号密码设置。
        p.put("user","root");//mysql登录的账号
        p.put("password","root");//mysql登录的密码
        Connection connect = driver.connect(url, p);

        System.out.println(connect);
        //就可以开始操作数据库了
    }

    /**
     * DriverManager : 可以帮我们管理Driver
     * @throws SQLException
     */
    @Test
    public void test2() throws SQLException {
        //1.创建Driver的实例
        Driver driver = new com.mysql.jdbc.Driver();
        //2.使用DriverManager进行注册
        DriverManager.registerDriver(driver);
        //3.获取Connection的实例
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo2",
                "root", "root");
        System.out.println(connection);
    }

    /**
     * 对于使用DriverManager获取连接 进行改良
     *
     * 省略了 1.创建Driver的实例  2.DriverManager的注册
     * 上面两步是在com.mysql.jdbc.Driver的类中已经执行了。
     */
    @Test
    public void test3() throws ClassNotFoundException, SQLException {
        //目的是为了，让Driver中的静态代码块执行
        Class.forName("com.mysql.jdbc.Driver");
        //通过DriverManager去获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo2",
                "root", "root");
        System.out.println(connection);
    }

    /**
     * 通过读取配置文件的方式获取连接
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    @Test
    public void test4() throws ClassNotFoundException, SQLException, IOException {
        //读取配置文件中的内容
        Properties properties = new Properties();
        InputStream is = JDBCTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        properties.load(is);
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        //目的是为了，让Driver中的静态代码块执行
        Class.forName(driver);
        //通过DriverManager去获取连接
        Connection connection = DriverManager.getConnection(url,user,password);
        System.out.println(connection);
    }
```

配置文件 - jdbc.properties

```
url=jdbc:mysql://localhost:3306/demo2?rewriteBatchedStatements=true
user=root
password=root
driver=com.mysql.jdbc.Driver
```

# 三 封装JDBCUtils

```java
public class JDBCUtils {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;
    private static InputStream is;

    static {
        try {
            //1.获取Connection的连接
            Properties properties = new Properties();
            is = JDBCTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(is);
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            driver = properties.getProperty("driver");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关流
            if (is != null){
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
        //目的是为了，让Driver中的静态代码块执行
        try {
            Class.forName(driver);
            //通过DriverManager去获取连接
            Connection connection = DriverManager.getConnection(url,user,password);
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            //如果在获取连接时发生了异常，那么就直接终止程序的运行
            throw  new RuntimeException();
        }
    }

    /**
     * 关闭资源
     * @param connection
     * @param ps
     */
    public static void close(Connection connection, PreparedStatement ps) {
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (ps != null){
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 关资源
     * @param connection
     * @param ps
     * @param rs
     */
    public static void close(Connection connection, PreparedStatement ps, ResultSet rs) {
        close(connection,ps);
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
```

# 四 对数据库的增，删，改，查的操作

``` java
 /*
      往数据库中插入一条数据
     */
    @Test
    public void test() throws SQLException {
        //1.获取连接
        Connection connection = JDBCUtils.getConnection();
        //2.提供带占位符的sql语句
        String sql = "insert into student(sid,sname,sage,ssex) values(?,?,?,?)";
        //3.预编译 - 给占位符赋值
        PreparedStatement ps = connection.prepareStatement(sql);
        //第一个参数：占位符的索引位置从1开始 第二个参数给占位符赋值的内容
        ps.setInt(1,1);
        ps.setString(2,"xiaolongge");
        ps.setInt(3,20);
        ps.setInt(4,0);//0表示男，1表示女
        //4.执行sql语句
        //execute,executeUpdate方法用来执行 ：增，删，改的语句
        /*
        //返回值如果为true表示这是一个查询语句，如果为false不是查询语句
          execute方法可以执行，增，删，改，查类型的语句。
         只有执行select类型的语句才返回true.但是没有意义。
        */

        boolean execute = ps.execute();
//        ps.executeUpdate();
        //5.关资源
       JDBCUtils.close(connection,ps);
    }

    /*
      删除数据库中的一条数据
     */
    @Test
    public void test2() throws SQLException {
        //1.获取连接
        Connection connection = JDBCUtils.getConnection();
        //2.提供一个带占位符的sql语句
        String sql = "delete from student where sid=?";
        //3.预编译
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,1);
        //4.执行sql语句
        int i = ps.executeUpdate();
        System.out.println("共有" + i + "条数据受到影响");
        //5.关资源
        JDBCUtils.close(connection,ps);
    }

    /*
        修改数据
    */
    @Test
    public void test3() throws SQLException {
        //1.获取连接
        Connection connection = JDBCUtils.getConnection();
        //2.提供一个带占位符的sql语句
        String sql = "update student set sage=? where sid=?";
        //3.预编译
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,20);
        ps.setInt(2,3);
        //4.执行sql语句!
        int i = ps.executeUpdate();
        System.out.println("共有" + i + "条数据受到影响");
        //5.关资源
        JDBCUtils.close(connection,ps);
    }

    /**
     * 查询数据
     */
    @Test
    public void test4() throws SQLException {
        //创建一个集合用来存储所有的对象(万事万物皆对象)
        List<Student> list = new ArrayList<Student>();

        //1.获取连接
        Connection connection = JDBCUtils.getConnection();
        //2.提供一个的sql语句
        String sql = "select * from student";
        //3.预编译
        PreparedStatement ps = connection.prepareStatement(sql);
        //4.执行sql语句
        ResultSet rs = ps.executeQuery();
        //5.从ResultSet中获取数据
        while(rs.next()){//判断是否还有数据
            //取数据
            /**
             * getInt(int columnIndex) : 根据 字段的索引位置（第几个字段） 获取对应的数据
             * getInt(String columnLable) : 根据 字段的名字 获取对应的数据
             */
            int sid = rs.getInt(1);
            String sname = rs.getString(2);
            int sage = rs.getInt(3);
            int ssex = rs.getInt(4);
            //输出所有内容
            System.out.println(sid + " - " + sname + " - " + sage + " - " + ssex);
            //将一行数据封装成一个对象
            Student student = new Student(sid, sname, sage, ssex);
            list.add(student);//将对象放入到集合中
        }

        //6.关资源
        JDBCUtils.close(connection,ps,rs);

    }
```

**总结：**

```sql
1.增，删，改可以使用excuteUpdate方法
2.查询 使用excuteQuery
3.excute可以增，删，改，查都使用，但是用在查询上没有意义返回true,执行增删改都返回false
```

# 五 数据库连接池

1 常见的数据库连接池 ：C3P0,DBCP,DRUID

2.Druid的使用

①将Druid包导入到项目中

②代码

``` java
 /**
     * 数据库连接池
     */
    @Test
    public void test() throws SQLException {
        //创建DruidDataSource对象用来获取连接
        DruidDataSource source = new DruidDataSource();
        //关于数据的一些配置
        source.setUrl("jdbc:mysql://localhost:3306/demo2");
        source.setUsername("root");
        source.setPassword("root");
        source.setDriverClassName("com.mysql.jdbc.Driver");
        //获取连接
        Connection connection = source.getConnection();
        System.out.println(connection);
    }

    /**
     * 数据库连接池 - 读取配置文件
     */
    @Test
    public void test2() throws Exception {
        Properties properties = new Properties();
        			properties.load(DruidTest.class.getClassLoader().
                                    getResourceAsStream("druid.properties"));

        //通过工厂类创建DataSource类的实例。
        DataSource source = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = source.getConnection();//获取连接
        System.out.println(connection);
    }
```

**配置文件** - druid.properties

```
url=jdbc:mysql://localhost:3306/demo2
username=root
password=root
driverClassName=com.mysql.jdbc.Driver
```

# 六 DBUtils

## 1.说明：

​	①DBUtils用来帮我们更快捷更方便的操作数据库。

​	②如果要使用DBUtils那么要先将jar包导入到项目中

## 2.代码：

``` java
   /**
     * 查 多条数据
     */
    @Test
    public void test4() throws SQLException {
        //通过QueryRunner的对象可以操作数据库
        QueryRunner queryRunner = new QueryRunner();
        //sql语句
        String sql = "select sid,sname,sage,ssex from student";
        //query(Connection conn, String sql, ResultSetHandler<T> rsh, Object... params)
        /*
            conn : 数据库连接对象
            sql : sql语句
            ResultSetHandler ： 根据需要的返回的结果的不同，
            	可以创建对应的ResultSetHandler实现类的对象。
                ①BeanHandler:把结果集转为一个 Bean
                ②BeanListHandler:把结果集转为一个 Bean 的集合
         */
        List<Student> list = queryRunner.query(JDBCUtils.getConnection(), sql,
                new BeanListHandler<Student>(Student.class));

        for (Student s : list) {
            System.out.println(s);
        }
    }

    /**
     * 查 -条数据
     */
    @Test
    public void test3() throws SQLException {
        //通过QueryRunner的对象可以操作数据库
        QueryRunner queryRunner = new QueryRunner();
        //sql语句
        String sql = "select sid,sname,sage,ssex from student where sid=?";
        //query(Connection conn, String sql, ResultSetHandler<T> rsh, Object... params)
        /*
            conn : 数据库连接对象
            sql : sql语句
            ResultSetHandler ： 根据需要的返回的结果的不同，
            	可以创建对应的ResultSetHandler实现类的对象。
                ①BeanHandler:把结果集转为一个 Bean
                ②BeanListHandler:把结果集转为一个 Bean 的集合

         */
        Student student = queryRunner.query(JDBCUtils.getConnection(), sql,
                new BeanHandler<Student>(Student.class), 1);

        System.out.println(student);
    }

    /**
     * 插入
     * @throws SQLException
     */
    @Test
    public void test2() throws SQLException {
        //通过QueryRunner的对象可以操作数据库
        QueryRunner queryRunner = new QueryRunner();

        String sql = "insert into student(sid,sname) values(?,?)";
        int update = queryRunner.update(JDBCUtils.getConnection(), sql, 3,"xiaolongge");
        System.out.println("共有" + update + "条数据受到影响");
    }

    /**
     * 删除
     * @throws SQLException
     */
    @Test
    public void test() throws SQLException {
        //通过QueryRunner的对象可以操作数据库
        QueryRunner queryRunner = new QueryRunner();

        String sql = "delete from student where sid=?";
        int update = queryRunner.update(JDBCUtils.getConnection(), sql, 3);
        System.out.println("共有" + update + "条数据受到影响");
    }
```

## 3.注意

```
1.在使用DBUtils查数据时，数据库中的字段名（类型）必须和JavaBean中的字段名（类型）一致。
才能将数据库中查到的数据替我们封装成对象。如果字段名对应不上。那么返回的对象中的该字段没有值。

2.解决办法（表中的字段名和JavaBean中的字段名不一致） ：可以使用别名的方式。
```

# 七 事务

## 1.案例：

```
有两个账户AA,BB
AA给BB转账100

AA - 100 (sql语句)
BB + 100 (sql语句)

必须保证转账的操作要么同时成功，要么同时失败。
```

## 2.代码

``` java
//1.获取连接
Connection connection = JDBCUtils.getConnection();
//2.禁止自动提交
connection.setAutoCommit(false);
String sql = "update account set abalance=? where aname=?";
//预编译
PreparedStatement ps = connection.prepareStatement(sql);

try{
    //aa-100的操作
    ps.setInt(1,900);
    ps.setString(2,"aa");
    //执行sql语句
    ps.executeUpdate();

    System.out.println(1 / 0);

    //bb+100
    ps.setInt(1,1100);
    ps.setString(2,"bb");
    //执行sql语句
    ps.executeUpdate();

    //提交
    connection.commit();
}catch (Exception e){
    //回滚
    connection.rollback();
}finally {
    //无论成功或失败都要设置成原来的状态
    connection.setAutoCommit(true);
    //关资源
    JDBCUtils.close(connection,ps);
}
```

# 八 批处理

 **1.在url中添加** ：rewriteBatchedStatements=true
       jdbc:mysql://localhost:3306/demo2?rewriteBatchedStatements=true

  **2.API**
       addBatch();添加批处理
       executeBatch();执行批处理中的数据
       clearBatch();清空批处理

**3.代码：**

```java
long start = System.currentTimeMillis();

//获取连接
Connection connection = JDBCUtils.getConnection();

String sql = "insert into t2 values(?)";
//预编译
PreparedStatement ps = connection.prepareStatement(sql);

for (int i = 1; i <= 100000; i++) {
    ps.setString(1,i+"");
    //将执行的语句添加到批处理中
    ps.addBatch();

    if (i % 1000 == 0){//每1000条数据的时候执行一次
        ps.executeBatch();//执行批处理操作
        ps.clearBatch();//清空批处理
    }
}

//关闭资源
connection.close();
ps.close();

long end = System.currentTimeMillis();
System.out.println("time=" + (end - start));
```

# 九 sql注入的问题（知道）

**案例：**

``` java
  		String name="abcddd' or '1'='1";

//      select * from account where aname='abcddd' or '1'='1'

        Connection connection = JDBCUtils.getConnection();
        //获取Statement的对象用来处理sql语句
        Statement st = connection.createStatement();
        //sql语句
        String sql = "select * from account where aname='" + name + "'";
        System.out.println(sql);
        //执行sql语句
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            System.out.println(rs.getString(1));
            System.out.println(rs.getString(2));
        }

        connection.close();
        st.close();
        rs.close();
```

