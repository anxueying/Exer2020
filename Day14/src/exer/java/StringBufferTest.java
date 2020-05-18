package exer.java;

import org.junit.Test;

/*
一、StringBuffer 和 StringBuilder ： 可变的字符序列。二者具有兼容的 API

StringBuffer ：是线程安全的，因此效率低
StringBuilder ： 是线程不安全的，因此效率高

 * StringBuffer 和 StringBuilder 的常用方法：
 * 	① StringBuffer append(String str) : 添加
 * 	 StringBuffer insert(int offset, String str) ： 插入
 * 	 StringBuffer replace(int start, int end, String str)：替换
 *
 *  ② int indexOf(String str) ：返回子串的位置索引
 * 	 int lastIndexOf()
 *
 *  ③ String substring(int start, int end)：取子字符串序列
 *  ④ StringBuffer delete(int start, int end)：删除一段字符串
 *      StringBuffer deleteCharAt(int index):删除指定位置字符
 *  ⑤ String toString()：转换为String对象
 */
public class StringBufferTest {
    @Test
    public void test2(){
        StringBuilder sb = new StringBuilder("abcdef");
        sb.append("啦啦啦");//abcdef啦啦啦
        sb.insert(1,"插");//a插bcdef啦啦啦
        sb.replace(1,2,"替换");//a替换bcdef啦啦啦

        System.out.println(sb);

        System.out.println(sb.indexOf("替换"));//1
        System.out.println(sb.lastIndexOf("换"));//2
        System.out.println(sb.lastIndexOf("啦"));//10

        System.out.println(sb.substring(1));//替换bcdef啦啦啦
        System.out.println(sb.substring(1, 3));//替换

        sb.delete(1,3);
        System.out.println(sb);//abcdef啦啦啦

        sb.deleteCharAt(5);
        System.out.println(sb);//abcde啦啦啦

        String str = sb.toString();
        System.out.println(str);//abcde啦啦啦
    }
}
