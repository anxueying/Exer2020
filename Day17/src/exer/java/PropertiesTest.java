package exer.java;


import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Mrs. An Xueying
 */
public class PropertiesTest {

    @Test
    public void test() throws IOException{
        Properties props = new Properties();
        props.load(new FileInputStream("userInfo.properties"));

        String username = props.getProperty("username");
        String password = props.getProperty("password");

        System.out.println(username + '\t' + password);
    }
}
