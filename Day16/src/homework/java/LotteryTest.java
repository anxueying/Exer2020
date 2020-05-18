package homework.java;

import java.util.HashSet;
import java.util.Scanner;


public class LotteryTest {

    public static void main(String[] args) {
        HashSet lotteryNum = new HashSet();
        while (lotteryNum.size()<11){
            lotteryNum.add((int)(Math.random()*49+1));
        }

        System.out.println("乐透号码已经生成，游戏开始：");

        HashSet buyerNum = new HashSet();
        Scanner scanner = new Scanner(System.in);
        for (int i = 1; i <= 10; i++) {
            System.out.print("请输入第"+i+"个数字[1-50]：");
            int num = scanner.nextInt();
            for(;;){
                if (buyerNum.contains(num)){
                    System.out.print("已有该数字，请重新输入：");
                    num = scanner.nextInt();
                }else if(num<1||num>50){
                    System.out.print("超过范围，请重新输入：");
                    num = scanner.nextInt();
                }else {
                    buyerNum.add(num);
                    break;
                }
            }
        }

        System.out.print("您输入的号码为：");
        System.out.println(buyerNum);
        System.out.print("乐透号码为：");
        System.out.println(lotteryNum);

        buyerNum.retainAll(lotteryNum);

        System.out.println(buyerNum);
        System.out.print("猜中了："+buyerNum.size()+"个数字");
    }
}
