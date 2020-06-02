package exer;

public class StringTest2 {
    public static void main(String[] args) {
        String str1 = "a";
        String str2 = "b";
        String str3 = "a" + "b";
        String str4 = (str1 + str2).intern();//调用常量池的向量,如果没有再创建

        System.out.println(str3 == str4);
        //System.out.println(str4);
    }
}
