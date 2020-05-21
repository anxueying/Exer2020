package exer;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Mrs.An Xueying
 * 2020/5/20 11:17
 */
public class ConsoleTest {
    public static void main(String[] args) {
        InputStream in = null;
        try {
            System.out.print("请输入：");
            in = System.in;
            byte[] b = new byte[1024];
            int len = 0;
            String inStr = null;
            while ((len = in.read(b)) != -1) {
                inStr = new String(b, 0, len);
                //不用trim也可以用readline来读取
                if ("e".equalsIgnoreCase(inStr.trim())||"exit".equalsIgnoreCase(inStr)) {
                    System.out.println("退出");
                    return;
                } else {
                    System.out.println("大写："+inStr.toUpperCase());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
