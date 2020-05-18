package home.java;

import java.util.*;

/**
 * @author axy
 * day16 map课后练习 第1题
 */
public class CharOccurNum {
    public static void main(String[] args) {
        Random rand = new Random();
        ArrayList<String> list = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        String str = "Your future depends on your dreams, so go to sleep.";
        for (int i = 0; i < str.length(); i++) {
            String temp = str.substring(i,i+1);
            set.add(temp);
            list.add(temp);
        }

        ArrayList<String> alphaBet = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            alphaBet.add((char)(65+i)+"");
            alphaBet.add((char)(97+i)+"");
        }

        HashMap<String,Integer> hashMap = new HashMap<>(set.size());

        System.out.println(set);
        for (String s : set) {
            if (alphaBet.contains(s)){
                hashMap.put(s,listTest(list,s));
            }
        }

        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

        /*
        System.out.println("a : " + listTest(list, "a"));
        System.out.println("b : " + listTest(list, "b"));
        System.out.println("c : " + listTest(list, "c"));
        System.out.println("x : " + listTest(list, "x"));*/

    }

    public static int listTest(Collection list, String s){
        int num = 0;
        for (Object o : list) {
            if (o.equals(s)){
                num++;
            }
        }
        return num;
    }
}
