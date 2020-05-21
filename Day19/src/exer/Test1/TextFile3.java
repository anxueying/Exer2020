package exer.Test1;

import java.io.FileReader;
import java.io.IOException;

/**
 * @author Mrs.An Xueying
 * 2020/5/19 11:08
 * 打印当前代码文件内容（看看我有什么错）
 */
public class TextFile3 {
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
            while ((len=fi.read(chars))!=-1) {
                sb.append(chars, 0, len);
                //index最后会变为-1  再回来又从头找了 所以这里不能用++ 需要声明fromIndex
                while ((index = sb.indexOf("\n",index))!=-1){
                    sb.insert(++index, lineNum++ + ".");
                }
            }
            System.out.print(sb.toString());
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
