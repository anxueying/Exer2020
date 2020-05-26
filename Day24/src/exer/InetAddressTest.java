package exer;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Mrs.An Xueying
 * 2020/5/26 11:27
 */
public class InetAddressTest {
    public static void main(String[] args) throws UnknownHostException {
        //根据主机名获取该主机的信息--主机名和IP地址
        InetAddress baidu = InetAddress.getByName("www.baidu.com");
        //获取主机名
        System.out.println(baidu.getHostName());
        //获取IP地址
        System.out.println(baidu.getHostAddress());

        //根据主机名获取该主机的信息--主机名和IP地址（localhost是局域网内）
        InetAddress local = InetAddress.getByName("localhost");
        //获取主机名
        System.out.println(local.getHostName());
        //获取IP地址
        System.out.println(local.getHostAddress());

        //根据本机的信息--主机名和IP地址
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost.getHostName());
        System.out.println(localHost.getHostAddress());
    }
}
