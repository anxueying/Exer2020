package homework;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Mrs.An Xueying
 * 2020/5/27 19:35
 * 模拟客户端输入要查询的中文，服务器返回对应的英文单词
 * （1）服务器端有一个map，key是中文词语，value是对应的单词
 *
 * （2）服务器接收到客户端的词语后，从map中get，如果可以找到，就返回单词，如果找不到，就返回“没有找到”
 */
public class SearchWordTest {

    @Test
    public void client() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(),6789);


        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要查询的中文：");
        String str = scanner.next();

        // 获取输出流

        PrintStream ps = new PrintStream(socket.getOutputStream());
        ps.println(str);

        InputStream isServer = socket.getInputStream();
        InputStreamReader isrServer = new InputStreamReader(isServer);
        BufferedReader brServer = new BufferedReader(isrServer);

        System.out.println(brServer.readLine());

        socket.close();
        scanner.close();
    }

    @Test
    public void service() throws Exception {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("花", "flower");
        hashMap.put("水", "water");
        hashMap.put("香蕉", "banana");

        ServerSocket serverSocket = new ServerSocket(6789);
        Socket socket = serverSocket.accept();

        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String result = hashMap.get(br.readLine());

        OutputStream os = socket.getOutputStream();
        PrintStream ps = new PrintStream(os);

        if(result==null){
            ps.println("没有找到");
        }else {
            ps.println(result);
        }

        socket.close();
        serverSocket.close();

    }

}
