package exer.Test1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Mrs.An Xueying
 * 2020/5/19 11:08
 * 打印当前代码文件内容（边读边改）
 */
public class TextFile {
    public static void main(String[] args) {
        FileReader fi = null;
        BufferedReader br = null;
        try {
            fi = new FileReader("D:\\developer_tools\\200421JavaSE\\Day19\\src\\exer\\test.txt");
            br = new BufferedReader(fi);
            String str="";
            int lineNum = 1;
            StringBuilder sb = new StringBuilder();
            while ((str=br.readLine())!=null){
                sb.append(lineNum++ +"."+str+"\n");
            }
            System.out.print(sb.toString());
        } catch (IOException f){
            f.printStackTrace();
        } finally {
            if (br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
