package home.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

/**
 * @author 安雪莹
 * day16 list、set  第5题
 */
public class OccurrenceNum {
    public static void main(String[] args) {
        Random rand = new Random();
        ArrayList list = new ArrayList();
        for (int i = 0; i < 30; i++) {
            list.add((char)(rand.nextInt(26)+97)+"");
        }
        System.out.println(list);


        System.out.println("a : " + listTest(list, "a"));
        System.out.println("b : " + listTest(list, "b"));
        System.out.println("c : " + listTest(list, "c"));
        System.out.println("x : " + listTest(list, "x"));

    }

    public static int listTest(Collection list,String s){
        int num = 0;
        for (Object o : list) {
            if (o.equals(s)){
                num++;
            }
        }
        return num;
    }
}
