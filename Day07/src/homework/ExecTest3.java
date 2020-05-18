package homework;

import java.util.Scanner;

public class ExecTest3 {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.print("请输入学生人数：");
        int student = scan.nextInt();
        System.out.println("请输入" + student + "个成绩");
        int[] scores = new int[student];
        int maxScore = 0;
        for (int i = 0; i < student; i++) {
            scores[i] = scan.nextInt();
            maxScore = Math.max(scores[i], maxScore);
        }

        System.out.println("最高分是：" + maxScore);

        ExecTest3 et = new ExecTest3();

        char[] scoreline = new char[student];
        for (int i = 0; i < student; i++) {
            scoreline[i] = et.scoreline(scores[i], maxScore);
            System.out.println("student " + i + " score is " + scores[i] + "grade is " + scoreline[i]);
        }
    }

    public char scoreline(int score, int maxScore) {
        if (score >= maxScore - 10) {
            return 'A';
        } else if (score >= maxScore - 20) {
            return 'B';
        } else if (score >= maxScore - 30) {
            return 'C';
        } else {
            return 'D';
        }
    }

}
