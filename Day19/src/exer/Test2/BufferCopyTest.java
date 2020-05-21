package exer.Test2;

import java.io.*;

/**
 * @author Mrs.An Xueying
 * 2020/5/19 14:57
 *  txt运行时间：1
 * 视频运行时间：69
 * 图片运行时间：1
 */
public class BufferCopyTest {
    public static void main(String[] args) {
        String src = "D:\\developer_tools\\200421JavaSE\\Day19\\src\\exer\\test.txt";
        String desc = "D:\\developer_tools\\200421JavaSE\\Day19\\src\\exer\\test1.txt";

        String src1 = "C:\\Users\\Lenovo\\Desktop\\183231572-1-208.mp4";
        String desc1 = "C:\\Users\\Lenovo\\Desktop\\1.mp4";

        String src2 = "C:\\Users\\Lenovo\\Desktop\\1.png";
        String desc2 = "C:\\Users\\Lenovo\\Desktop\\2.png";

        long begin = System.currentTimeMillis();
        BufferCopy(src,desc);
        long end = System.currentTimeMillis();
        System.out.println("txt运行时间："+(end-begin));

        long begin1 = System.currentTimeMillis();
        BufferCopy(src1,desc1);
        long end1 = System.currentTimeMillis();
        System.out.println("视频运行时间："+(end1-begin1));

        long begin2 = System.currentTimeMillis();
        BufferCopy(src2,desc2);
        long end2 = System.currentTimeMillis();
        System.out.println("图片运行时间："+(end2-begin2));
    }

    public static void BufferCopy(String src,String dest){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            fis = new FileInputStream(src);
            fos = new FileOutputStream(dest);
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            int len = 0;
            byte[] b = new byte[10];

            while ((len=bis.read(b)) != -1 ){
                bos.write(b, 0, len);
            }
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
