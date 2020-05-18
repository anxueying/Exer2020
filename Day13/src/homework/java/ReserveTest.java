package homework.java;

import javax.xml.bind.annotation.XmlType;

public class ReserveTest {
    /*
    将一个字符串进行反转。将字符串中指定部分进行反转。比如将“abcdefg”反转为”abfedcg”
     */
    public static void main(String[] args) {
        System.out.println(ReserveStr("ab", 0, 2));
    }

    public static String ReserveStr(String str, int a , int b){

        if(a>str.length()||a<0||b<0){
            return str;
        }

        if (b-a>=str.length()) {
            b = str.length()-1;
        }

            char[]  chars = str.toCharArray();
            for (int i = a; i <b-a; i++) {
                char temp = chars[i];
                chars[i] = chars[b];
                chars[b--] = temp;
            }
            return new String(chars);
        }
}
