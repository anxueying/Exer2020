package exer.java;

import exer.utils.JDBCUtils;
import org.junit.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Mrs.An Xueying
 * 2020/6/2 16:00
 * 事务
 */
public class TransactionTest {
    /**
     * 有两个账户 AA  BB
     * AA给BB转账100
     * AA-100
     * BB+100
     */
    @Test
    public void test() throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        //禁止自动提交
        connection.setAutoCommit(false);
        String sql = "update account set abalance = ? where aname = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        try {
            //AA-100
            ps.setInt(1, 900);
            ps.setString(2, "aa");
            ps.executeUpdate();
            //BB-100
            ps.setInt(1, 1100);
            ps.setString(2, "bb");
            ps.executeUpdate();
            //提交
            connection.commit();
        }catch (Exception e){
            //回滚
            connection.rollback();
        }finally {
            //无论成功或失败都要设置回原来的状态
            connection.setAutoCommit(true);
            JDBCUtils.close(connection ,ps );
        }
    }
}
