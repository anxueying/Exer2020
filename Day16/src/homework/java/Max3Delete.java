package homework.java;

import javafx.beans.binding.When;

import java.util.*;

public class Max3Delete {
    public static void main(String[] args) {
        List list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add((int)(Math.random()*99+1));
        }

        System.out.print("10个随机值：");
        System.out.println(list);

        TreeSet treeSet = new TreeSet();
        treeSet.addAll(list);
        while (treeSet.size()>3){
            treeSet.pollFirst();
        }

        System.out.print("前3个最大的：");
        System.out.println(treeSet);

        list.removeAll(treeSet);
        System.out.print("删除后：");
        System.out.println(list);

    }
}
