package exer;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Mrs.An Xueying
 * 2020/5/26 16:09
 */
public class TCPTest1 {
    /**
     * 客户端程序（工程）
     */
    @Test
    public void client() throws Exception {
        //创建客户端对象
        /*
            第一个参数 ：获取服务器的地址
            第二个参数 ：访问的服务器的端口号
         */
        Socket socket = new Socket(InetAddress.getLocalHost(), 6789);
        //向服务器端写数据 - 获取向服务端写数据的流
        OutputStream os = socket.getOutputStream();
        //开始写数据 - 如果要写中文可以将字节流转成字符流
        os.write("abc".getBytes());
        //通知服务器写完了
        socket.shutdownOutput();


        /**
         * 客户端读服务器
         */
        //读取服务器的内容
        InputStream is = socket.getInputStream();
        //读数据
        int len = 0;
        byte[] bytes = new byte[1024];
        while((len = is.read(bytes)) != -1){
            System.out.println("客户端" + new String(bytes,0,len));
        }
        //通知服务器端读完了
        socket.shutdownInput();



        //关资源
        os.close();
        is.close();
        socket.close();
    }

    /**
     * 服务器程序（工程）
     *
     * 注意 ： 不能同时启动多个使用同一端口号的服务器（否则就会抛异常）
     */
    @Test
    public void server() throws IOException {
        //创建服务器的对象
        /**
         * 参数 ： 用来接收消息的端口号
         */
        ServerSocket serverSocket = new ServerSocket(6789);
        //允许客户端的请求 - 每接收一次就获取到了可以和该客户端通信的对象
        Socket socket = serverSocket.accept();
        //获取客户端的数据的输入流
        InputStream is = socket.getInputStream();
        //读数据
        int len = 0;
        byte[] bytes = new byte[1024];
        while((len = is.read(bytes)) != -1){
            //new String(bytes,0,len) : 将数组转成字符串
            System.out.println("服务器" + new String(bytes,0,len));
        }
        //通知客户端读完了
        socket.shutdownInput();

        /**
         * 服务端给客户端写数据
         */
        //先获取输出流 --- 如果要写中文 1.使用转换流将字节流转成字符流（可以再加一个缓冲流）。
        OutputStream os = socket.getOutputStream();
        os.write("heihei haha memeda".getBytes());
        //通知客户端数据已经写完
        socket.shutdownOutput();

        //关闭资源
        is.close();
        os.close();
        socket.close();
        serverSocket.close();
    }
}
