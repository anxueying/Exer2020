package project.view;

import project.domain.Item;
import project.service.ItemService;
import java.io.*;
import java.security.Key;

/**
 * @author Mrs.An Xueying
 * 2020/5/21 16:38
 * 为应用程序的主控类，负责与用户交互，完成考试及成绩查询功能
 */
public class ExamView {

    private ItemService is = new ItemService();
    private char[] answer = new char[is.getQuestionNum()];

    public char getUserAction() {
        char answer = 0;
        char[] keys = new char[]{'A','B','C','D','N','P','F','Y','N','1','2','3'};
        try {
            InputStream in = System.in;
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader bw = new BufferedReader(isr);

            String str;
            if ((str = bw.readLine())!=null){
                answer = str.toUpperCase().charAt(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (char key : keys) {
            if (key == answer){
                return key;
            }
        }

        return 0;
    }

    public void displayItem(int no) {
        Item item = is.getItem(no);
        System.out.println(item.getQuestion());
        for (String option : item.getOptions()) {
            System.out.println(option);
        }
    }

    public void testExam() {
        System.out.println("帮助信息 所有题目皆为单选");
        System.out.println("上一题：P  \t 下一题：N \t 退出考试系统：F");
        System.out.println("按N开始答题");

        int questionNum = -1;
        for(;;){
            System.out.print("请输入你的选择：");
            char key = getUserAction();
            switch (key){
                case 'P':
                    if (questionNum!=0){
                        displayItem(--questionNum);
                    }else {
                        System.out.println("已是第1题");
                    }
                    break;
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                    answer[questionNum] = key;
                    System.out.println("正确答案为："+is.getRightAnswer(questionNum));
                case 'N':
                    if (questionNum==is.getQuestionNum()-1){
                        //考试结束
                        endExam();
                        return;
                    } else {
                        if (questionNum>=0){
                            answer[questionNum] = key;
                        }
                        displayItem(++questionNum);
                    }
                    break;
                case 'F':
                    System.out.print("是否确认结束考试（Y/N）：");
                    char yn = getUserAction();
                    if (yn=='Y') {
                        //考试结束
                        endExam();
                        return;
                    }else {
                        System.out.print("继续考试,");
                        continue;
                    }
                default:
                    break;
            }
        }
    }


    public void endExam(){
        is.saveAnswer(answer);
        System.out.println("考试已结束。");
        System.out.println("正确答案：");
        for (char c : is.getAllAnswer()) {
            System.out.print(c);
        }

        System.out.println("\n考生答案：");
        for (char c : is.getExamAnswer()) {
            if (c=='N'){
                System.out.print(" ");
            }else {
                System.out.print(c);
            }
        }

        System.out.print("\n你的分数为："+is.getScore());
        saveScore();

    }

    public void saveScore(){
        BufferedWriter bos = null;
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fos = new FileOutputStream("score.dat");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(is.getScore());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getLastScore(){
        ObjectInputStream ois = null;
        int score = 0;
        try {
            File file = new File("score.dat");
            if (!file.exists()){
                return 0;
            }
            FileInputStream fis = new FileInputStream("score.dat");
            ois = new ObjectInputStream(fis);
            score = (Integer) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois!=null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return score;
    }
}
