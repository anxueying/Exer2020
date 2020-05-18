package home.java;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

/**
 * @author 安雪莹
 * day16 list、set  第7题
 */
public class UnionLotto {
    /**
     * 双色球每注投注号码由6个红色球号码和1个蓝色球号码组成。
     * 红色球号码从1—33中选择；
     * 蓝色球号码从1—16中选择；
     * 请随机生成一注双色球号码。（要求同色号码不重复）
     * @param args
     */
    public static void main(String[] args) {
        TreeSet redSet = new TreeSet();
        while (redSet.size()<5){
            redSet.add((int)(Math.random()*32+1));
        }

        int blue = (int)(Math.random()*15+1);
        ArrayList unionList = new ArrayList();
        unionList.addAll(redSet);
        unionList.add(blue);
        System.out.print("双色球所有号码：");
        System.out.println(unionList);

        System.out.print("红色号码：");
        for (Object o : redSet) {
            System.out.print(o);
            System.out.print("\t");
        }

        System.out.print("蓝色号码："+blue);
    }
}
