package homework.java;

import java.util.Scanner;

public class Exercise3 {
    public static void main(String[] args) {

        for (;;) {
            System.out.print("请选择角色（-1退出）：");
            Scanner scanner = new Scanner(System.in);
            String choiceRole = scanner.next();

            if (choiceRole.equals("-1")){
                System.out.println("退出游戏");
                break;
            }

            if (Player.select(choiceRole) == null) {
                System.out.println("无此角色，请重新输入");
            } else if (Player.select(choiceRole) instanceof Mage) {
                Mage mage = (Mage) Player.select(choiceRole);
                mage.specialFight();
            } else {
                Warrior warrior = (Warrior) Player.select(choiceRole);
                warrior.specialFight();
            }
        }
    }
}


interface FightAble{
    void specialFight();
    default void commonFight(){
        System.out.println("普通打击");
    }
}

class Warrior implements FightAble{
    public void specialFight(){
        System.out.println("武器攻击");
        commonFight();
    }
}

class Mage implements FightAble{
    public void specialFight(){
        System.out.println("法术攻击");
        commonFight();
    }
}

class Player{

    public static FightAble select(String str){
        if (str.equals("法力角色")){
            return new Mage();
        }else if (str.equals("武力角色")){
            return new Warrior();
        }else {
            return null;
        }

    }
}