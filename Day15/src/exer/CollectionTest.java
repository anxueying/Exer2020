package exer;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionTest {
    public static void main(String[] args) {
        Collection coll = new ArrayList();
        coll.add("AA");
        coll.add(new String("BB"));
        coll.add(new Person("张三", 18));
        coll.add(123);//自动装箱
        coll.add('a');
        coll.add(true);
        /*System.out.println("coll.size() = " + coll.size());
        coll.clear();
        System.out.println(coll.isEmpty());*/

        Collection coll1 = new ArrayList();
        coll1.add('a');
        coll1.add(false);
        //coll1.remove(false);

        /*coll.addAll(coll1);
        System.out.println(coll);*/

        System.out.println("coll.containsAll(coll1) = " + coll.containsAll(coll1));
        System.out.println("coll.contains(true) = " + coll.contains(true));

        System.out.println(coll);
//        coll.removeAll(coll1);

        coll.retainAll(coll1);
        System.out.println(coll);


        Object[] objs = coll1.toArray();
        for (Object obj : objs) {
            System.out.println(obj);
        }


    }
}
