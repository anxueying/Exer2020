package homework.java;

/*
获取两个字符串中最大相同子串。比如：
   str1 = "abcwerthelloyuiodef“;str2 = "cvhellobnm"
   提示：将短的那个串进行长度依次递减的子串与较长
   的串比较。
 */
public class Homework5 {
    public static void main(String[] args) {
        /*String str1 = "abcwerthelloyuiodef";
        String str2 = "cvhellobnm";*/
        String str1 = "aHellob";
        String str2 = "cHello";
        System.out.println(getSameString(str1, str2));
    }

    public static String getSameString(String str1, String str2) {
        String maxStr = "";
        String minStr = "";
        if (str1.length() > str2.length()) {
            maxStr = str1;
            minStr = str2;
        } else {
            maxStr = str2;
            minStr = str1;
        }

        for (int x = 0; x < minStr.length(); x++) {
            //z 比较长度每轮减少1
            for (int y = 0, z = minStr.length() - x; z != minStr.length() + 1; y++, z++) {
               /*   x   y   z   temp
                    0	0	6	cHello
                    1	0	5	cHell
                    1	1	6	Hello
                    */
                String temp = minStr.substring(y, z);//[0,minStr.length() - x]
                System.out.println(x+"\t"+y+"\t"+z+"\t"+temp);
                if (maxStr.contains(temp))
                    return temp;
            }
        }
        return null;

    }
}
