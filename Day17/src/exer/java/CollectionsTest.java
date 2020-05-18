package exer.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author axy
 */
public class CollectionsTest {
    public static void main(String[] args) {
        /*
        1.请从键盘随机输入10个整数保存到List中，
        并按倒序、从大到小的顺序显示出来
         */
        Scanner scanner = new Scanner(System.in);
        ArrayList list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            System.out.print("请输入整数：");
            list.add(scanner.nextInt());
        }

        Collections.sort(list);

        System.out.println(list);
        for (int i = list.size(); i > 0 ; i--) {
            System.out.println(list.get(i-1));
        }
    }
}
