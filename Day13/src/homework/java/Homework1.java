package homework.java;

/*
打印字符串长度；
删除字符串开始和结尾处的空白，以获得新字符串，并打印输出新串的长度；
判断新字符串是否以“abc”开头，是否以“xyz”结尾，打印判断结果；
获取该新串第3位至第6位间的子串，将其转换为大写并打印；
查找该新串是否包含“lnm ”子串，并打印子串在字符串中的位置。
 */
public class Homework1 {
    public static void main(String[] args) {
        System.out.println("args[0] = " + args[0]);
        System.out.println("args[0].length() = " + args[0].length());
        String newStr = args[0].trim();
        System.out.println("newStr.length() = " + newStr.length());
//        String beginStr = newStr.substring(0,3);
//        System.out.println("beginStr = " + beginStr);
//        String endStr = newStr.substring(newStr.length()-3,newStr.length());
//        System.out.println("endStr = " + endStr);
        if (newStr.substring(0,3).equals("abc")&&newStr.substring(newStr.length()-3,newStr.length()).equals("xyz")){
            System.out.println("是否以“abc”开头:"+true);
        }else {
            System.out.println("是否以“abc”开头:"+false);
        }

        System.out.println("newStr.substring(2,6).toUpperCase() = " + newStr.substring(2, 6).toUpperCase());
        System.out.println("newStr.indexOf(\"lnm\") = " + newStr.indexOf("lnm"));

    }
}
