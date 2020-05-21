package exer.Test1;

import java.io.FileReader;
import java.io.IOException;

/**
 * @author Mrs.An Xueying
 * 2020/5/19 11:08
 * 打印当前代码文件内容（全部读，再全部改）
 */
public class TextFile1 {
    public static void main(String[] args) {
        FileReader fi = null;
        try {
            fi = new FileReader("D:\\developer_tools\\200421JavaSE\\Day19\\src\\exer\\TextFile.java");
            char[] chars = new char[10];
            int len=0;
            int index = 0;
            int fromIndex = 0;
            int lineNum = 1;
            StringBuilder sb = new StringBuilder();
            //第一行
            sb.append(lineNum++ +".");
            while ((len=fi.read(chars))!=-1) {
                //改进该程序，读取文件内容后，在每行开始加上行号，再连同内容一并输出到屏幕上。
                //提示：可将读出的char数组转换为StringBuilder，然后在字符串中搜索“\n”，并在其之后插入行号即可。
                sb.append(chars, 0, len);
                //方法记得不熟 indexOf(str,fromIndex)  从fromIndex位置开始搜
            }
            while ((index = sb.indexOf("\n",index))!=-1){
                //上面的fromIndex换成index可以吗？ 下面的index+1 变为：index++ 思考：只能放外面
                sb.insert(++index, lineNum++ + ".");
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
