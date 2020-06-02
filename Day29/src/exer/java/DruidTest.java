package exer.java;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Mrs.An Xueying
 * 2020/6/2 16:31
 * 第三方数据库连接池 Druid（德鲁伊） --阿里
 */
public class DruidTest {

    @Test
    public void test() throws SQLException {
        DruidDataSource source = new DruidDataSource();
        source.setUrl("jdbc:mysql://localhost:3306/myemployees");
        source.setUsername("root");
        source.setPassword("root");
        source.setDriverClassName("com.mysql.jdbc.Driver");

        Connection connection = source.getConnection();
        System.out.println(connection);
    }

    /**
     * 使用配置文件 配置名都是固定的，不可以改
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        Properties properties = new Properties();
        properties.load(DruidTest.class.getClassLoader().getResourceAsStream("druid.properties"));
        DataSource ds = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = ds.getConnection();
        System.out.println(connection);
    }
}
