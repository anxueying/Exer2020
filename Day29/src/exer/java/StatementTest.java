package exer.java;

import exer.utils.JDBCUtils;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Mrs.An Xueying
 * 2020/6/2 18:50
 * Statement 一开始使用用来操作数据库的对象
 * 存在sql注入问题(了解即可，现在都用PrepareStatement)
 */
public class StatementTest {
    @Test
    public void test() throws SQLException {
        //sql注入语句
        String name = "abcddd' or '1'='1";

        Connection connection = JDBCUtils.getConnection();
        //获取Statement的对象，用来处理sql语句
        Statement st = connection.createStatement();
        String sql = "select * from account where aname='"+name+"'";
        System.out.println(sql);
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()){
            System.out.println(rs.getString(1));
            System.out.println(rs.getString(2));
        }

        connection.close();
        st.close();
        rs.close();
    }

    @Test
    public void test1(){


    }
}
