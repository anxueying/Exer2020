package exer;

import java.io.*;

/**
 * @author Mrs.An Xueying
 * 2020/5/20 11:36
 * 编写程序，在main方法中读取键盘键入的10组数字，将这些数字保存在num.txt文件中；
 * 查看num.txt文件的内容，验证复制是否正确
 */
public class NumTxt {
    public static void main(String[] args) {
        //输入流
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            InputStream in = System.in;
            InputStreamReader isr = new InputStreamReader(in);
            br = new BufferedReader(isr);

            //输出流
            FileOutputStream fos = new FileOutputStream("num.txt");
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osw);


            String str = null;
            int count = 10;
            for (int i = 0; i < count; i++) {
                System.out.print("请输入数字:");
                //br输入
                if ((str = br.readLine())!=null){
                    //bw输出
                    bw.write(str);
                    bw.flush();
                    bw.newLine();
                    System.out.println("录入成功");
                }else {
                    System.out.println("录入失败");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }





    }
}
