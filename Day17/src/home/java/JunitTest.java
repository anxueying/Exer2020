package home.java;

import org.junit.Test;

/**
 * @author Lenovo
 * junitTest 必须为public才能运行
 */

public class JunitTest{
    @Test
    public void test(){
        Dao<User> userDao = new Dao<>();
        userDao.save("001",new User(1,10,"Lay"));
        userDao.save("002",new User(2,28,"Dilireba"));
        userDao.update("001",new User(1,30,"Lay"));
        userDao.delete("002");
        System.out.println(userDao.list());
    }
}
