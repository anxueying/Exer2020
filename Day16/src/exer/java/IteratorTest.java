package exer.java;

import org.junit.Test;

import java.util.*;

public class IteratorTest {
    @Test
    public void test4(){
        Set set = new HashSet();
        set.add("张三");
        set.add("李四");
        set.add("王五");

        Iterator it = set.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }

    @Test
    public void test3(){
        ArrayList list = new ArrayList();
        list.add("AA");
        list.add("BB");
        list.add("CC");
        list.add("DD");
        list.add("EE");

        ListIterator listIterator = list.listIterator();
        while (listIterator.hasNext()){
            Object obj = listIterator.next();
            if (obj.equals("EE")){
                listIterator.add("FF");
            }
            System.out.println(obj);
        }
        System.out.println(list);
    }


    @Test
    public void test2(){
        ArrayList list = new ArrayList();
        list.add("AA");
        list.add("BB");
        list.add("CC");
        list.add("DD");
        list.add("EE");

        Iterator it = list.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }


    @Test
    public void test1(){
        ArrayList list = new ArrayList();
        list.add("AA");
        list.add("BB");
        list.add("CC");
        list.add("DD");
        list.add("EE");

        for (Object o : list) {
            System.out.println(o);
        }
    }
}
