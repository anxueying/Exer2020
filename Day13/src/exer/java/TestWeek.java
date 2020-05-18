package exer.java;

import com.sun.xml.internal.bind.v2.WellKnownNamespace;

import java.util.Scanner;

public class TestWeek {
    public static void main(String[] args) {
        int num = Integer.parseInt(args[0]);
        Week[] weeks = Week.values();
        printWeek(weeks[num-1]);

        /*switch (num){
            case 1:
                System.out.println(Week.Monday);
                printWeek(Week.Monday);
                break;
            case 2:
                System.out.println(Week.Tuesday);
                printWeek(Week.Tuesday);
                break;
            case 3:
                System.out.println(Week.Wednesday);
                printWeek(Week.Wednesday);
                break;
            case 4:
                System.out.println(Week.Thursday);
                printWeek(Week.Thursday);
                break;
            case 5:
                System.out.println(Week.Friday);
                printWeek(Week.Friday);
                break;
            case 6:
                System.out.println(Week.Saturday);
                printWeek(Week.Saturday);
                break;
            case 7:
                System.out.println(Week.Sunday);
                printWeek(Week.Sunday);
                break;
        }*/
    }

    public static void printWeek(Week week){
        switch (week){
            case Monday:
                System.out.println("星期一");
                break;
            case Tuesday:
                System.out.println("星期二");
                break;
            case Wednesday:
                System.out.println("星期三");
                break;
            case Thursday:
                System.out.println("星期四");
                break;
            case Friday:
                System.out.println("星期五");
                break;
            case Saturday:
                System.out.println("星期六");
                break;
            case Sunday:
                System.out.println("星期日");
                break;
        }
    }
}


enum Week{
    Monday("Monday",1),
    Tuesday("Tuesday",2),
    Wednesday("Wednesday",3),
    Thursday("Thursday",4),
    Friday("Friday",5),
    Saturday("Saturday",6),
    Sunday("Monday",7)
    ;
    private final String weekName;
    private final  int weekDesc;

    Week(String weekName, int weekDesc) {
        this.weekName = weekName;
        this.weekDesc = weekDesc;
    }

    public String  toString(){
        return weekName+"\t"+weekDesc;
    }
}