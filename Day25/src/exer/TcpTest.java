package exer;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Mrs.An Xueying
 * 2020/5/27 8:41
 * 1. 从客户端发送文件给服务端
 * 2. 服务端保存到本地
 * 3. 并返回“发送成功”给客户端
 * 4. 并关闭相应的连接
 */
public class TcpTest {
    /**
     * 1. 发送文件
     * 2. 接收信息
     * 3. 关闭连接
     */
    @Test
    public void client() throws Exception {
        //建立连接
        Socket socket = new Socket("localhost",6789);
        //发送文件
        //读本地
        FileInputStream fis = new FileInputStream("D:\\developer_tools\\200421JavaSE\\Items.txt");
        OutputStream os = socket.getOutputStream();

        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len=fis.read(bytes))!=-1){
            os.write(bytes,0,len);
        }
        //发送完毕
        socket.shutdownOutput();

        //接收信息
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);

        char[] chars = new char[1024];
        len = 0;

        while ((len=isr.read(chars))!=-1){
            System.out.println(new String(chars,0,len));
        }
        //接收完毕
        socket.shutdownInput();

        socket.close();

    }

    /**
     * 1. 接收文件
     * 2. 发送信息
     * 3. 关闭连接
     */
    @Test
    public void service() throws Exception {
        //建立连接
        ServerSocket serverSocket = new ServerSocket(6789);
        Socket socket = serverSocket.accept();
        //接收文件
        //从客户端读
        InputStream is = socket.getInputStream();

        //在服务器保存
        FileOutputStream fos = new FileOutputStream("ItemService.txt");
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len=is.read(bytes))!=-1){
            fos.write(bytes, 0, len);
        }
        //接收完成
        socket.shutdownInput();


        //发送信息
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        osw.write("Send success!");
        //一定要flush，否则会抛异常
        osw.flush();

        socket.shutdownOutput();
        //关闭
        socket.close();
        serverSocket.close();
    }
}
