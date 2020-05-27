package homework;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Mrs.An Xueying
 * 2020/5/26 22:18
 * 客户端模拟学生咨询，服务器端模拟咨询老师，进行交互。
 * 1. 如果是一个客户端与服务器端交互，怎么实现
 * 2. 如果是多个客户端与服务器交互，怎么实现
 */
public class CourseConsultantTest {
    /**
     * Socket
     * 客户端：接受信息
     * 注：中文，要使用转换流
     * 你好，我想报名就业班
     * 好吧，再见！
     */
    @Test
    public void client() throws Exception {

        Socket socket = new Socket(InetAddress.getLocalHost(),6789);

        //1. 向服务器端读
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        //2. 开始读
        System.out.println(br.readLine());


        //4. 向服务器写信息
        OutputStream os = socket.getOutputStream();
        PrintStream ps = new PrintStream(os);

        //5. 开始写
        ps.println("你好，我想报名就业班");
        System.out.println(br.readLine());


        ps.println("好吧，再见！");

        //7. 关闭资源
        socket.close();
    }


    /**
     * Socket 由 ServerSocket 通过端口接收后 调用accept方法创建
     * 1. 如果是一个客户端与服务器端交互，怎么实现
     * 客户端：收到信息
     * 欢迎咨询尚硅谷
     * 报满了,请报下一期吧
     */
    @Test
    public void service() throws Exception {

        ServerSocket serverSocket = new ServerSocket(6789);
        Socket socket = serverSocket.accept();

        //输出流
        OutputStream os = socket.getOutputStream();
        PrintStream ps = new PrintStream(os);
        ps.println("欢迎咨询尚硅谷");

        //输入流

        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        System.out.println(br.readLine());
        ps.println("报满了,请报下一期吧");

        System.out.println(br.readLine());

        socket.close();
        serverSocket.close();

    }


    /**
     * 如果是多个客户端与服务器交互，怎么实现
     * 开多线程，每多一个客户端，多一个线程
     * @throws Exception 异常全部抛出，暂不做处理
     */
    @Test
    public void services() throws Exception {

        ServerSocket serverSocket = new ServerSocket(6789);
        boolean flag = true;
        while (flag){
            Socket socket = serverSocket.accept();

            new Thread(new Runnable(){

                @Override
                public void run() {
                    //输出流
                    try {
                        OutputStream os = socket.getOutputStream();
                        PrintStream ps = new PrintStream(os);
                        ps.println("欢迎咨询尚硅谷");

                        //输入流

                        InputStream is = socket.getInputStream();
                        InputStreamReader isr = new InputStreamReader(is);
                        BufferedReader br = new BufferedReader(isr);

                        System.out.println(br.readLine());
                        ps.println("报满了,请报下一期吧");

                        System.out.println(br.readLine());
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
        serverSocket.close();

    }
}
