package home.java;

import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * @author 安雪莹
 * day16 list、set  第6题
 */
public class DuplicateChar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入一串字母：");
        String str = scanner.next();
        System.out.println("str = " + str);
        System.out.print("去重后：");

        LinkedHashSet lhs = new LinkedHashSet();
        for (int i = 0; i < str.length(); i++) {
            lhs.add(str.charAt(i));
        }
        for (Object o : lhs) {
            System.out.print(o);
        }
    }
}
