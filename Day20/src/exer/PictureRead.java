package exer;

import java.io.*;

/**
 * @author Mrs.An Xueying
 * 2020/5/21 11:39
 * 读取jpg并copy
 */
public class PictureRead {
    public static void main(String[] args) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\Lenovo\\Downloads\\1.jpg");
            bis = new BufferedInputStream(fis);

            FileOutputStream fos = new FileOutputStream("1.jpg");
            bos = new BufferedOutputStream(fos);

            byte[] b  = new byte[1024];
            int len = 0;

            while ((len=bis.read(b))!=-1){
                bos.write(b, 0, len);
            }

            System.out.println("\n复制完成");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bos!=null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bis!=null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
