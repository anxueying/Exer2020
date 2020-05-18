package home.java;

import exer.java.GenericTest;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

/**
 * @author axy
 * day16 map课后练习 第5题
 */
public class WorldCupYear {
    public static void main(String[] args) {
        HashMap<Integer,String> worldCup = new HashMap<>(21);
        worldCup.put(1930,"乌拉圭");
        worldCup.put(1934,"意大利");
        worldCup.put(1938,"意大利");
        worldCup.put(1950,"乌拉圭");
        worldCup.put(1954,"西德");
        worldCup.put(1958,"巴西");
        worldCup.put(1962,"巴西");
        worldCup.put(1966,"英格兰");
        worldCup.put(1970,"巴西");
        worldCup.put(1974,"西德");
        worldCup.put(1978,"阿根廷");
        worldCup.put(1982,"意大利");
        worldCup.put(1986,"阿根廷");
        worldCup.put(1990,"西德");
        worldCup.put(1994,"巴西");
        worldCup.put(1998,"法国");
        worldCup.put(2002,"巴西");
        worldCup.put(2006,"意大利");
        worldCup.put(2010,"西班牙");
        worldCup.put(2014,"德国");
        worldCup.put(2018,"法国");

        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入一个年份：");
        int year = scanner.nextInt();
        //查询
        System.out.println(year+"年，获得世界杯冠军的是："+worldCup.get(year));


        System.out.print("请输入一个国家名称：");
        String country = scanner.next();

        HashSet<Integer> years = new HashSet<>();
        for (Map.Entry<Integer, String> entry : worldCup.entrySet()) {
            if(entry.getValue().equals(country)){
                years.add(entry.getKey());
            }
        }

        if (years.size()==0){
            System.out.println("没有获得过世界杯");
        }else {
            for (Integer integer : years) {
                System.out.println(integer);
            }
        }

    }
}
