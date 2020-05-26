package exer;

import org.junit.Test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author Mrs.An Xueying
 * 2020/5/26 14:23
 */
public class UDPTest {
    /**
     * 发送数据
     * 在数据包里：发送地址+发送内容
     * 1. 创建对象
     * 2. 发送数据
     * 3. 关闭
     */
    @Test
    public void send() throws Exception {
        /**
         * 创建数据包
         * (byte buf[], int offset, int length, InetAddress address, int port)
         */
        String message = "Hello";
        DatagramPacket dp = new DatagramPacket(message.getBytes(), 0,1, InetAddress.getLocalHost(),4567);
        DatagramSocket s = new DatagramSocket();
        s.send(dp);
        s.close();
    }

    /**
     * 接受数据
     * 1. 创建对象
     * 2. 接受数据
     * 3. 关闭
     */
    @Test
    public void reciever() throws Exception {
        /**
         * 创建数据包的对象 byte buf[], int offset, int length  按需选择构造器
         */
        byte[] bytes = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bytes,0,bytes.length);

        DatagramSocket r = new DatagramSocket(4567);
        r.receive(dp);
        //输出接收到的内容
        System.out.println(new String(bytes,0,dp.getLength()));

        r.close();
    }

}
