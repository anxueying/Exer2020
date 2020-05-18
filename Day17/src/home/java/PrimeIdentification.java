package home.java;

import java.util.ArrayList;

/**
 * @author 安雪莹
 * day16 list/set  第4题
 */
public class PrimeIdentification {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 30; i++) {
            arrayList.add((int)(Math.random()*98+2));
        }
        System.out.println("随机数为：");
        System.out.println(arrayList);

        ArrayList primeList = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            int num = Integer.parseInt(arrayList.get(i).toString());
            if(ifPrime(num)){
                primeList.add(arrayList.get(i));
            }
        }

        System.out.println("其中的质数为：");
        System.out.println(primeList);
    }

    public static boolean ifPrime(int num){
        for (int i = 2; i < num; i++) {
            if (num%i==0){
                return false;
            }
        }
        return true;
    }
}
