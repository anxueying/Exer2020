package exer.Test1;

import java.io.FileReader;
import java.io.IOException;

/**
 * @author Mrs.An Xueying
 * 2020/5/19 11:08
 * 打印当前代码文件内容（看打印路径版）
 */
public class TextFile2 {
    public static void main(String[] args) {
        FileReader fi = null;
        try {
            fi = new FileReader("D:\\developer_tools\\200421JavaSE\\Day19\\src\\exer\\test.txt");
            char[] chars = new char[3];
            int len=0;
            int index = 0;
            int fromIndex = 0;
            int lineNum = 1;
            StringBuilder sb = new StringBuilder();
            //第一行
            sb.append(lineNum++ +".");

            int outW = 1;
            int inW = 1;
            while ((len=fi.read(chars))!=-1) {
                System.out.println("outW:"+outW++);
                System.out.println("index="+index);
                sb.append(chars, 0, len);

                System.out.println("sb：  " + sb.toString());
                System.out.println("--------");
                //从0开始 index = 4 sb在4插入"1."  然后又继续读 sb又加3个字符，
                while ((index = sb.indexOf("\n",index))!=-1){
                    System.out.println("intW:"+inW++);
                    sb.insert(++index, lineNum++ + ".");
                    System.out.println("index：  " +index + "  sb:"+ sb.toString());
                    System.out.println("++++++++");
                }
            }
            //System.out.print(sb.toString());
        } catch (IOException f){
            f.printStackTrace();
        } finally {
            if (fi!=null){
                try {
                    fi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
