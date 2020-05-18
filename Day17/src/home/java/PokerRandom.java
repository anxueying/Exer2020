package home.java;

import java.text.CollationElementIterator;
import java.util.*;

/**
 * @author axy
 * day16 map课后练习 第4题
 */
public class PokerRandom {
    private static final String[] POKER_POINT = {"3","4","5","6","7","8","9","10","J","Q","K","A","2"};
    private static final String[] POKER_SUIT = {"方片", "梅花", "红桃", "黑桃" };
    private static final String[] POKER_JOKERS = {"大王","小王"};
    private static ArrayList<Integer> POKER_INDEX = new ArrayList<>(54);
    static HashMap<Integer,String> pokers = new HashMap<>();

    public static void main(String[] args) {

        int pokerIndex = 0;
        for (int i = 0; i < POKER_SUIT.length; i++) {
            for (int j = 0; j < POKER_POINT.length; j++) {
                POKER_INDEX.add(++pokerIndex);
                String poker = POKER_SUIT[i] + POKER_POINT[j] ;
                pokers.put(pokerIndex,poker);
            }
        }

        for (int i = 0; i < POKER_JOKERS.length; i++) {
            POKER_INDEX.add(++pokerIndex);
            pokers.put(pokerIndex,POKER_JOKERS[i]);
        }


        Collections.shuffle(POKER_INDEX);
        //System.out.println(pokers);

        //按照索引大小排序，索引大小就是牌的大小
        TreeSet<String> player1 = new TreeSet<>();
        TreeSet<String> player2 = new TreeSet<>();
        TreeSet<String> player3 = new TreeSet<>();
        TreeSet<String> player4 = new TreeSet<>();
        TreeSet<String> elsePokers = new TreeSet<>();

        //依次取list中的索引，发给四位牌友

        int num = 13;
        for (int i = 0; i < (POKER_INDEX.size()/4)*4; i++) {
            System.out.println("\n左边玩家的牌是：");
            while (player1.size()<num){
                player1.add(pokers.get(POKER_INDEX.get(i)));
                System.out.print(pokers.get(POKER_INDEX.get(i))+"\t");
                i++;
            }

            System.out.println("\n右边玩家的牌是：");
            while (player2.size()<num){
                player2.add(pokers.get(POKER_INDEX.get(i)));
                System.out.print(pokers.get(POKER_INDEX.get(i))+"\t");
                i++;
            }

            System.out.println("\n上边玩家的牌是：");
            while (player3.size()<num){
                player3.add(pokers.get(POKER_INDEX.get(i)));
                System.out.print(pokers.get(POKER_INDEX.get(i))+"\t");
                i++;
            }

            System.out.println("\n我的牌是：");
            while (player4.size()<num){
                player4.add(pokers.get(POKER_INDEX.get(i)));
                System.out.print(pokers.get(POKER_INDEX.get(i))+"\t");
                i++;
            }
        }


        System.out.println("\n底牌的牌是：");
        for (int i = player1.size()*4;i<POKER_INDEX.size();i++){
            elsePokers.add(pokers.get(POKER_INDEX.get(i)));
            System.out.print(pokers.get(POKER_INDEX.get(i))+"\t");
        }
    }

}
