package exer;


import java.util.ArrayList;
import java.util.Random;

public class Test04 {
    public static void main(String[] args) {
        ArrayList<Integer> nums = getNum();
        System.out.println("10个随机值：" + nums);

        ArrayList<Integer> maxList = getTop3(nums);
        System.out.println("前3个最大的：" + maxList);

        //问题：  在getTop3(nums) 中删除  并没有返回nums， 为什么主方法中的nums会改变？？？
        System.out.println("删除后：" + nums);
    }

    public static ArrayList<Integer> getNum() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            int n = r.nextInt(100) + 1;
            list.add(n);
        }
        return list;
    }

    public static ArrayList<Integer> getTop3(ArrayList<Integer> list){
        ArrayList<Integer> maxList = new ArrayList<Integer>();
        for (int i = 0; i < 3; i++) {
            Integer max = (Integer) list.get(0);
            for (int j = 0; j < list.size(); j++) {
                Integer num = (Integer) list.get(j);
                if(max < num){
                    max = num;
                }

            }
            maxList.add(max);

            while(list.contains(max)){
                list.remove(max);
            }
        }

        return maxList;
    }
}

