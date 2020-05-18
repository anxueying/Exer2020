package homework.java;

import java.security.PublicKey;
import java.util.*;

public class PokerRandom {
    private static final String[] pokerPoint = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    private static final String[] pokerSuit = {"黑桃","红桃","方片","梅花"};
    private static final String[] pokerJokers = {"大王","小王"};
    static HashSet pokers = new HashSet();

    public static void main(String[] args) {

        for (int i = 0; i < pokerSuit.length; i++) {
            for (int j = 0; j < pokerPoint.length; j++) {
                String poker = pokerSuit[i] + pokerPoint[j] ;
                pokers.add(poker);
            }
        }
        pokers.addAll(Arrays.asList(pokerJokers));

       /* Iterator it = pokers.iterator();
        while (it.hasNext()){
            System.out.print(it.next());
        }*/
        System.out.println(pokers);

        HashSet player1 = new HashSet();
        HashSet player2 = new HashSet();
        HashSet player3 = new HashSet();
        HashSet player4 = new HashSet();
        HashSet elsePokers = new HashSet();

        Iterator it = pokers.iterator();
        while (it.hasNext()){
            while (player1.size()<12){
                player1.add(it.next());
            }
            while (player2.size()<12){
                player2.add(it.next());
            }
            while (player3.size()<12){
                player3.add(it.next());
            }
            while (player4.size()<12){
                player4.add(it.next());
            }
            elsePokers.add(it.next());
        }

        System.out.print("发牌\n第1个人：");
        System.out.println(player1);
        System.out.print("第2个人：");
        System.out.println(player2);
        System.out.print("第3个人：");
        System.out.println(player3);
        System.out.print("第4个人：");
        System.out.println(player4);
        System.out.print("剩余：");
        System.out.println(elsePokers);

    }

}
