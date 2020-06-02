package exer;

import org.junit.Test;

import java.util.*;

public class MapTest {
    @Test
    public void test1() {
        Map map = new HashMap();
        map.put("AA", 99);
        map.put("CC", 66);
        map.put("DD", 55);
        map.put("BB", 77);
        map.put("EE", 88);

        map.put("BB", 123);//若出现重复的 key ，后面的 value 将原来的 value 覆盖

        System.out.println(map);
    }

    /*
    添加、删除操作：
    Object put(Object key,Object value)
    Object remove(Object key)
    void putAll(Map t)
    void clear()
     */
    @Test
    public void test2() {
        Map map = new HashMap();
        map.put("AA", 99);
        map.put("CC", 66);
        map.put("DD", 55);
        map.put("BB", 77);
        map.put("EE", 88);

        map.remove("BB");

        Map map2 = new HashMap();
        map2.put("GG", 66);
        map2.put("JJ", 99);

        System.out.println(map);

        map.putAll(map2);

        System.out.println(map);

        map.clear();

        System.out.println(map);
    }

    /*
  元素查询的操作：
  Object get(Object key) : 根据 key 获取对应的 value
  boolean containsKey(Object key)
  boolean containsValue(Object value)
  int size()
  boolean isEmpty()
  boolean equals(Object obj)
   */
    @Test
    public void test3() {
        Map map = new HashMap();
        map.put("AA", 99);
        map.put("CC", 66);
        map.put("DD", 55);
        map.put("BB", 77);
        map.put("EE", 88);

        Object value = map.get("BB");
        System.out.println(value);

        Map map2 = new HashMap();
        map2.put("AA", 99);
        map2.put("CC", 66);
        map2.put("DD", 55);
        map2.put("BB", 77777);
        map2.put("EE", 88);

        System.out.println("是否包含指定的 key:" + map.containsKey("BBBBBB"));
        System.out.println("是否包含指定的 value:" + map.containsValue(66));
        System.out.println("获取map有几对有效元素：" + map.size());
        System.out.println("判断 map 是否为空：" + map.isEmpty());

        System.out.println("判断两个 Map 是否相同：" + map.equals(map2));
    }

    /*
       Map 集合的遍历
    */
    @Test
    public void test4() {
        Map map = new HashMap();
        map.put("AA", 99);
        map.put("CC", 66);
        map.put("DD", 55);
        map.put("BB", 77);
        map.put("EE", 88);

        //遍历 Map 的方式一：获取 Map 中所有的 key 组成的 Set。
        Set keySet = map.keySet();

        for (Object o : keySet) {
            System.out.print(o);
            System.out.print('\t');
            Object value = map.get(o);
            System.out.println(value);
        }
    }

    @Test
    public void test5() {
        Map map = new HashMap();
        map.put("AA", 99);
        map.put("CC", 66);
        map.put("DD", 55);
        map.put("BB", 77);
        map.put("EE", 88);

        //遍历 Map 的方式二：获取 Map 中所有的 value 组成的 Collection
        Collection coll = map.values();//接口-类 多态

        Iterator it = coll.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }


    @Test
    public void test6() {
        Map map = new HashMap();
        map.put("AA", 99);
        map.put("CC", 66);
        map.put("DD", 55);
        map.put("BB", 77);
        map.put("EE", 88);

        //遍历 Map 的方式三：获取Map中所有的 Entry（Entry是 Map 的内部类，一个 Entry对应着一个key和value）组成Set
        Set entrySet = map.entrySet();

        for (Object o : entrySet) {
            Map.Entry entry = (Map.Entry) o;
            Object key = entry.getKey();
            Object value = entry.getValue();

            System.out.println(key + "=" + value);
        }
    }

}

