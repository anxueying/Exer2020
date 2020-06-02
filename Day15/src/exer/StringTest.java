package exer;

import java.util.ArrayList;
import java.util.Arrays;

public class StringTest {
    public static void main(String[] args) {
        System.out.println(getSameString("abcvhelthelloyuiodef", "cvhellobnm"));
    }

    /**
     * 直接选择排序：排序字符串
     *
     * @param str
     * @return 自然排序的字符串
     */
    public static String sortString(String str) {
        char[] chars = str.toCharArray();
        //它的工作原理是每一次从待排序的数据元素中选出最小（或最大）的一个元素，
        //存放在序列的起始位置，直到全部待排序的数据元素排完。
        for (int i = 0; i < chars.length - 1; i++) {//须比较的元素位置
            //遍历比较的元素
            for (int j = i + 1; j < chars.length; j++) {
                //从第一个元素开始，如果第二次循环，则从第二个元素开始，以此类推，j = i
                //遍历元素数量是chars.length-1-i
                if (chars[i] > chars[j]) {  //从小到大排序
                    char temp = chars[i];
                    chars[i] = chars[j];
                    chars[j] = temp;
                }
            }
        }

        return new String(chars);
    }

    /**
     * 模拟一个trim方法，去除字符串两端的空格。
     *
     * @param str
     * @return
     */
    public static String trimString(String str) {

        if (str.equals("")) {
            return str;
        }
        char[] chars = str.toCharArray();
        int startIndex = 0, endIndex = chars.length - 1;

        while (chars[startIndex] == ' ' && startIndex < endIndex) {
            startIndex++;
        }

        while (chars[endIndex] == ' ' && startIndex < endIndex) {
            endIndex--;
        }

        String newStr = str.substring(startIndex, endIndex + 1);
        return newStr;
    }

    /**
     * 获取一个字符串在另一个字符串中出现的次数。
     *
     * @param str1 ababa
     * @param str2 ab
     * @return 次数
     */
    public static int getCount(String str1, String str2) {
        String strTemp = str1;
        int countNum = 0;
        int index = 0;
        while (index < str1.length()) {
            if (strTemp.indexOf(str2) == 0) {
                countNum++;
            }
            strTemp = str1.substring(++index);
        }
        return countNum;
    }

    /**
     * 获取两个字符串中最大相同子串。如有两个相同的长度的最大子串，返回个字符串数组
     *
     * @param str1 abcde
     * @param str2 qbcdf
     * @return
     */
    public static ArrayList getSameString(String str1, String str2) {
        String maxStr = str1.length() > str2.length() ? str1 : str2;
        String minStr = str1.length() > str2.length() ? str2 : str1;
        ArrayList arrayList = new ArrayList();

        for (int i = 0; i < minStr.length(); i++) {//循环次数

            //确定循环的起始位置  "abcde"  "ecda"
            for (int x = 0, y = minStr.length()-i; y < minStr.length()+1; x++, y++) {
                String tempStr = minStr.substring(x, y);
                System.out.println(tempStr);
                if (str1.contains(tempStr)) {
                    arrayList.add(tempStr);
                }
            }
            if (!arrayList.isEmpty()) {
                return arrayList;
            }
        }
        return arrayList;
    }
}
