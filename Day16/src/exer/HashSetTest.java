package exer;

import org.junit.Test;

import java.util.HashSet;

public class HashSetTest {
    @Test
    public void test1(){
        HashSet set = new HashSet();
        set.add("DD");
        set.add("CC");
        set.add("AA");
        set.add("EE");


        set.add(new String("BB"));

        System.out.println(set);
    }
}
