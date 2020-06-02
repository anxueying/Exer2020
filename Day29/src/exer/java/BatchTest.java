package exer.java;

import exer.utils.JDBCUtils;
import org.junit.Test;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Mrs.An Xueying
 * 2020/6/2 18:34
 * 批处理
 */
public class BatchTest {
    /**
     * 插入十万行数据--效率极低
     */
    @Test
    public void test() throws SQLException {
        long start = System.currentTimeMillis();

        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into t2 values(?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        for (int i = 1; i <=100000 ; i++) {
            ps.setString(1, i+"");
            ps.executeUpdate();
        }

        JDBCUtils.close(connection, ps);

        long end = System.currentTimeMillis();
        //很长
        System.out.println("time="+(end-start));
    }

    /**
     * 插入十万行数据--批处理
     * url后面加?rewriteBatchedStatements=true
     */
    @Test
    public void test1() throws SQLException {
        long start = System.currentTimeMillis();

        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into t2 values(?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        for (int i = 1; i <=100000 ; i++) {
            ps.setString(1, i+"");
            //将执行的语句添加到批处理中,相当于容器
            ps.addBatch();
            if (i%1000==0){
                //每1000条数据执行一次批处理操作
                ps.executeBatch();
                //执行完要清空容器
                ps.clearBatch();
            }
        }

        JDBCUtils.close(connection, ps);

        long end = System.currentTimeMillis();
        //time=978
        System.out.println("time="+(end-start));
    }
}
