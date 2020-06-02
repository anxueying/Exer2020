package exer.java;

import exer.bean.Student;
import exer.utils.JDBCUtils;
import org.junit.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mrs.An Xueying
 * 2020/6/2 14:43
 */
public class CRUDTest {
    /**
     * 向数据库中插入一条数据
     */
    @Test
    public void test1() throws SQLException {
        //1.获取连接
        Connection connection = JDBCUtils.getConnection();
        //2.提供带占位符（？）的sql语句
        String sql = "insert into student(sid,sname,sage,ssex) values(?,?,?,?)";
        //3.预编译 - 给占位符赋值
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, 1);
        ps.setString(2, "迪丽热巴");
        ps.setInt(3, 18);
        ps.setInt(4, 1);
        //4. 执行sql语句  execute、executeUpdate
        //该方法用来执行：增删改的语句，true表示是查询语句，false则不是  （但一般查询不用他）
        boolean execute = ps.execute();
        //5. 关闭资源
        JDBCUtils.close(connection, ps);
    }

    /**
     * 删除数据库中的一条数据
     */
    @Test
    public void test2() throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "delete from student where sid = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, 1);
        int execute = ps.executeUpdate();
        System.out.println("共有"+execute+"条数据受到影响");
        JDBCUtils.close(connection, ps);
    }

    /**
     * 修改数据库中的一条数据
     */
    @Test
    public void test3() throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "update  student set sage = ?  where sid = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, 28);
        ps.setInt(2, 1);
        int execute = ps.executeUpdate();
        System.out.println("共有"+execute+"条数据受到影响");
        JDBCUtils.close(connection, ps);
    }

    /**
     * 查询数据库中的一条数据
     */
    @Test
    public void test4() throws SQLException {
        //封装获得的数据（万物皆对象）
        List<Student> list = new ArrayList<>();
        //连接数据库
        Connection connection = JDBCUtils.getConnection();
        //加条件就用占位符，不然就直接写
        String sql = "select * from student";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        //从rs中获取数据
        while (rs.next()){
            //取数据 可以根据列索引/列名来获取数据，索引从1开始
            int sid = rs.getInt(1);
            String sname = rs.getString("sname");
            int sage = rs.getInt(3);
            int ssex = rs.getInt(4);
            System.out.println(sid+"\t"+sname+"\t"+sage+"\t"+ssex);
            //将一行数据封装成一个对象
            Student student = new Student(sid, sname, sage, ssex);
            list.add(student);
        }
        JDBCUtils.close(connection, ps,rs);
    }
}
