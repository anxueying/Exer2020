package exer;

import java.util.ArrayList;

/*

2、创建 ArrayList ，添加 5 个 String 元素。使用增强 for
循环遍历集合
（与遍历数组方式一样，取对象按 Object 类型取），
将 String 全部转换成大写，并打印
 */
public class Review2 {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        list.add("dd");
        list.add("ee");

        for (Object o : list) {
            System.out.println(o.toString().toUpperCase());
        }
    }
}
