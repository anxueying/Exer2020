package exer;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Mrs.An Xueying
 * 2020/5/26 14:50
 */
public class URLTest {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://192.168.10.250:8000/hello/index.jsp");

        //ip地址
        System.out.println(url.getHost());
        //端口号
        System.out.println(url.getPort());
        //获取访问的资源
        System.out.println(url.getFile());

        URL baidu = new URL("http://www.baidu.com");
        //创建连接对象
        HttpURLConnection connection = (HttpURLConnection)baidu.openConnection();
        //连接
        connection.connect();

        InputStream is = connection.getInputStream();
        int len  = 0;
        byte[] bytes = new byte[1024];
        while ((len=is.read(bytes))!=-1){
            System.out.println(new String(bytes,0,len));
        }

    }
}
