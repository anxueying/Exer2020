package homework;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Mrs.An Xueying
 * 2020/5/28 2:24
 */
public class Test02 {
    public static void main(String[] args) throws Exception {
        //读配置文件包
        Properties properties = new Properties();
        //用类加载器 配合 读取指定资源的输入流
        properties.load(Test02.class.getClassLoader().getResourceAsStream("config.properties"));

        //使用反射，通过配置文件获取的全类名类创建实例，调用方法
        Class clazz = Class.forName(properties.getProperty("fruitName"));
        //创建实例
        Fruit f = (Fruit) clazz.newInstance();
        //将榨汁机和水果连接在一起
        Juicer j = new Juicer();
        j.run(f);
    }
}
