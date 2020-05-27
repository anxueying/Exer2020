package homework;

import org.junit.Test;

import java.net.*;

/**
 * @author Mrs.An Xueying
 * 2020/5/28 0:46
 * 使用UDP群发, 模拟给全部同学群发“欢迎来到尚硅谷”
 */
public class GroupSendMessageTest {
    @Test
    public void send() throws Exception {
        //(1)先建立一个DatagramSocket
        DatagramSocket ds = new DatagramSocket();
        //(2)准备发送的数据
        String message = "欢迎来到尚硅谷";
        //getBytes方法，将string转为字节数组
        byte[] bytes = message.getBytes();

        //(3)把数据包装成一个数据报
        //DatagramPacket(byte[] buf, int length, InetAddress address, int port)
        /*
         * 第一个参数：要发送的数据的字节数组
         * 第二个参数：数组的长度
         * 第三个参数：接收方的IP地址
         * 第三个参数：接收方的端口号
         *
         * 好比发快递，需要填写接收方的IP和端口号
         */
        for (int i = 0; i <=255 ; i++) {
            //这里可以批量改ip地址 （局域网内尾号在0-255之间）
            DatagramPacket dp  = new DatagramPacket(bytes,bytes.length, InetAddress.getLocalHost(),6789);
            //(4)发送数据报
            //通过socket发送
            ds.send(dp);
        }

        //(5)断开
        ds.close();
    }

    @Test
    public void receive() throws Exception {
        //1、准备一个socket，用来接收消息
        //接收方，先确定端口号，监听数据的端口号
        //好比，要收到快递，需要先确定自己的地址和手机号，然后对方才能给你发
        DatagramSocket ds = new DatagramSocket(6789);
        //2、准备一个数据报，来接收数据  64k 最多只能接64k 准备多大的包裹就能接多大的数据
        //DatagramPacket(byte[] buf, int length)
        byte[] bytes = new byte[1024*64];
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
        //3、接收数据
        ds.receive(dp);
        //4、拆包裹
        byte[] data = dp.getData();
        int len = dp.getLength();
        System.out.println(new String(data,0,len));

        //5、关闭
        ds.close();

    }
}
