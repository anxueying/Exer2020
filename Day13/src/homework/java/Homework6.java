package homework.java;
/*
6、对字符串中字符进行自然顺序排序。
提示：
1）字符串变成字符数组。
2）对数组排序，选择，冒泡，Arrays.sort();
3）将排序后的数组变成字符串。
 */

import java.util.Arrays;

public class Homework6 {
    public static void main(String[] args) {
        String str = "fdnvuecsdjwo";
        System.out.println(sortString(str));
    }

    public static String sortString(String str){
        char[] chars = str.toCharArray();
       /* for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars.length-i-1; j++) {
                if (chars[j]>chars[j+1]){
                    char temp = chars[j];
                    chars[j] = chars[j+1];
                    chars[j+1] = temp;
                }
            }
        }*/
        Arrays.sort(chars);
        return new String(chars);
    }
}
