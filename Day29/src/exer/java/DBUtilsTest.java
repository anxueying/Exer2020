package exer.java;

import exer.bean.Student;
import exer.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Mrs.An Xueying
 * 2020/6/2 16:49
 * 封装了对数据库的操作：增删改都是update，查都是query
 * 使我们在操作数据库的时候更方便，效率更高
 */
public class DBUtilsTest {

    /**
     * 删除
     * @throws SQLException
     */
    @Test
    public void test() throws SQLException {
        //通过queryRunner操作数据库
        QueryRunner queryRunner = new QueryRunner();
        String sql = "delete from student where sid = ?";
        int update = queryRunner.update(JDBCUtils.getConnection(), sql, 1);
        System.out.println("共有"+update+"条数据收到影响");
    }

    /**
     * 插入数据
     * @throws SQLException
     */
    @Test
    public void test1() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "insert into student(sid,sname) values(?,?)";
        int update = queryRunner.update(JDBCUtils.getConnection(), sql, 2,"张艺兴");
        System.out.println("共有"+update+"条数据收到影响");
    }

    /**
     * 查询一条数据
     * @throws SQLException
     */
    @Test
    public void test2() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from student where sid = ?";
        /**
         * 注意：列名与属性名一致，否则无法转换，该属性无返回值。（可以使用别名来解决这个问题）
         * 注意：字段类型一定也要对应上，可以识别导入（创建类的时候要对应）
         * public <T> T query(java.sql.Connection conn,  数据库连接对象
         *                    String sql,
         *                    org.apache.commons.dbutils.ResultSetHandler<T> rsh,  根据需要的返回的结果不同，创建相应的对象
         *                      1. BeanHandler：把结果集转为一个Bean
         *                      2. BeanListHandler  把结果集转为一个Bean的集合
         *                    Object... params)
         */
        Student student = queryRunner.query(JDBCUtils.getConnection(), sql, new BeanHandler<Student>(Student.class), 1);
        System.out.println(student);
    }

    /**
     * 查询多条数据
     * @throws SQLException
     */
    @Test
    public void test3() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from student";
        List<Student> list = queryRunner.query(JDBCUtils.getConnection(), sql, new BeanListHandler<Student>(Student.class));
        for (Student student : list) {
            System.out.println(student);
        }
    }

}
