package exer;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Mrs.An Xueying
 * 2020/5/26 11:45
 * TCP协议  基于Socket
 */
public class TCPTest {
    /**
     * 客户端
     */
    @Test
    public void client() throws Exception {
        /**
         * 创建客户端对象
         * 第一个参数：获取服务器的地址
         * 第二个参数：访问服务器的端口号
         */
        Socket socket = new Socket(InetAddress.getLocalHost(), 6789);
        //向服务器写数据- 获取向服务器写数据的流
        OutputStream os = socket.getOutputStream();
        //大家在socket中使用转换流  一定要flush
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);

        //开始写数据  要写中文，将字节流转成字符流
        bw.write("我是中文要注意哦");
        bw.flush();

        //通知写完了
        socket.shutdownOutput();


        //关闭资源
        bw.close();
        //为了演示，发一次就关了 一般不关  全部完成再关
        socket.close();
    }

    /**
     * 服务器（先运行）
     * 注意：不能同时启动多个使用统一端口号的服务器，否则会报异常
     */
    @Test
    public void service() throws Exception {
        /**
         * 创建服务器的对象
         * 参数：用来接收消息的端口号
         */
        ServerSocket serverSocket = new ServerSocket(6789);
        //允许客户端的请求   每接收一次就获取到了可以和该客户端通信的对象（有几个客户端--上面的port，就要有几个accept）
        Socket socket = serverSocket.accept();
        //获取客户端数据的输入流
        InputStream is = socket.getInputStream();

        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len=is.read(bytes))!=-1){
            System.out.println(new String(bytes,0,len));
        }


        //通知读完了
        socket.shutdownInput();
        //关闭资源
        is.close();
        //为了演示，收一次就关了 一般不关  全部完成再关
        serverSocket.close();
    }

}
