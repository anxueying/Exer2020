package homework;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Mrs.An Xueying
 * 2020/5/27 20:28
 * 客户端给服务器端上传照片
 *
 * （1）客户端上传的照片，需要是jpg格式的，并且大小在2M（含）以内的，否则不能上传
 *
 * （2）要求上传成功后，服务器要返回上传成功，如果上传失败，服务器返回上传失败
 *
 * （3）客户端上传到服务器端的照片，存储在项目名下"photo"的文件夹中，并且以“照片原文件名+时间戳.jpg”命名
 */
public class SendPhotoTest {
    @Test
    public void client() throws IOException {
        //连接服务器
        Socket socket = new Socket(InetAddress.getLocalHost(),6789);
        //选择要上传的文件
        Scanner input =  new Scanner(System.in);
        System.out.println("请选择要上传的文件：");
        String fileStr = input.next();

        File file = new File(fileStr);

        //调用方法
        if(!photoCheck(file)){
            System.out.println("照片不符合条件，请重新上传");
            socket.close();
            return;
        }
        DataOutputStream dos = null;
        FileInputStream fis = null;

        //必须用try...catch 失败还要接收结果
        try {
            //用来发送文件名  数据流 readUTF()，writeUTF
            dos = new DataOutputStream(socket.getOutputStream());
            //去掉后缀的文件名
            dos.writeUTF(file.getName().substring(0, file.getName().lastIndexOf(".")));

            //发送文件内容
            fis = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int len = 0;


            while ((len=fis.read(bytes))!=-1){
                dos.write(bytes, 0, len);
            }
            //告诉服务器发送完毕
            socket.shutdownOutput();
        } catch (IOException e) {
            System.out.println("上传失败");
        } finally {
            fis.close();
        }

        //接收结果
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println(br.readLine());

        br.close();
        socket.close();
        input.close();
        dos.close();
    }

    /**
     * 需要是jpg格式的，并且大小在2M（含）以内的，否则不能上传
     * @param file 上传的照片
     * @return boolean
     */
    public boolean photoCheck(File file){
        if (file.length()>2*1024*1024  ){
            System.out.println("照片必须在2M(含)以内");
            return false;
        }else if(!file.getName().endsWith(".jpg")){
            System.out.println("照片必须是.jpg格式");
            return false;
        }
        return true;
    }

    @Test
    public void server() throws IOException {
        //开启服务器
        ServerSocket serverSocket = new ServerSocket(6789);
        //接收一个客户端的连接
        Socket socket = serverSocket.accept();
        //数据流传入，就用数据流接收
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        //获取输出流
        PrintStream ps = new PrintStream(socket.getOutputStream());

        //读文件名
        String filename = dis.readUTF();
        //生成文件名
        if (!new File("photo/").exists()){
            new File("photo/").mkdir();
        }
        String destfile = "photo/"+filename+System.currentTimeMillis()+".jpg";
        //读取文件内容，并写入目标文件
        FileOutputStream fos = new FileOutputStream(destfile);

        try {
            byte[] bytes = new byte[1024];
            int len;
            while ((len=dis.read(bytes))!=-1){
                //写入
                fos.write(bytes, 0, len);
            }
            //结果返回给客户端
            ps.println("接收成功！");
        } catch (IOException e) {
            ps.println("接收失败！");
        } finally {
            fos.close();
        }
        serverSocket.close();

    }
}
