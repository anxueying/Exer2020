package exer.java;

import org.junit.Test;

import java.util.*;

/**
 * @author Lenovo
 *
 *
 * 一、泛型：在 Java 中以“<>”的形式呈现，“<>” 中写引用数据类型，用于限制数据类型
 *
 * 1、集合中使用泛型：若集合中不使用泛型，意味着集合中可以添加任意类型的对象，
 *                   若需要具体到某一个类型，则需要强制类型转换，有可能引发 ClassCastException
 *
 *
 */
public class GenericTest  {
    @Test
    public void test1(){
        List<String> list = new ArrayList<String>();
        list.add("AA");
        list.add("BB");
        list.add("CC");
        list.add("DD");
        list.add("EE");

        for(String str : list){
            System.out.println(str.toLowerCase());
        }
    }
    
    @Test
    public void test2(){
        Set<Person> set = new HashSet<>();
        set.add(new Person("张三", 18));
        set.add(new Person("李四", 20));

        Iterator<Person> it = set.iterator();
        while (it.hasNext()){
            System.out.println(it.next().getName());
        }
    }
    
    @Test
    public void test3(){
        Map<String,Integer> map = new HashMap<String,Integer>(2);
        map.put("张三", 99);
        map.put("李四", 88);
        //方式一
        Set<String> keySet = map.keySet();
        for (String s : keySet) {
            Integer num = map.get(s);
            System.out.println(s+"\t"+num);
        }
        //方式二
        Collection<Integer> coll = map.values();

        Iterator<Integer> it = coll.iterator();

        while(it.hasNext()){
            Integer num = it.next();
            System.out.println(num);
        }

        //方式三
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();

        for(Map.Entry<String, Integer> entry : entrySet){
            String key = entry.getKey();
            Integer value = entry.getValue();

            System.out.println(key + "=" + value);
        }

        System.out.println("------------------------------------------");

        Iterator<Map.Entry<String, Integer>> it2 = entrySet.iterator();

        while(it2.hasNext()){
            Map.Entry<String, Integer> entry = it2.next();

            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + "=" + value);
        }
    }
}

