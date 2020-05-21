package project.service;

import project.domain.Item;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mrs.An Xueying
 * 2020/5/21 16:38
 * 封装了与考试题目访问相关的业务方法
 */
public class ItemService {
    private Item[] items;

    /**
     * 构造器 将文件内容存到items中
     */
    public ItemService() {
        List<String> stringList = readTextFile("D:\\developer_tools\\200421JavaSE\\Items.txt");
        int questionNum= stringList.size()/ 6;
        this.items = new Item[questionNum];
        for (int i = 0; i <  questionNum; i++) {
            String question = stringList.get(i*6);

            String[] options = new String[]{stringList.get(i*6+1),
                                            stringList.get(i*6+2),
                                            stringList.get(i*6+3),
                                            stringList.get(i*6+4)};
            String answer = stringList.get(i*6+5);
            Item item = new Item(question,options,answer);
            this.items[i] = item;
        }
    }

    /**
     * 创建answer.dat二进制文件
     * 将数组中的内容以对象形式写入到文件中保存
     * @param answer 考生输入的答案
     */
    public void saveAnswer(char[] answer){
        BufferedWriter bos = null;
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fos = new FileOutputStream("answer.dat");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(answer);
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

    /**
     * 获取考生答案
     * @return 考生答案
     */
    public char[] getExamAnswer(){
        ObjectInputStream ois = null;
        char[] answers = new char[getQuestionNum()];
        try {
            FileInputStream fis = new FileInputStream("answer.dat");
            ois = new ObjectInputStream(fis);
            answers = (char[]) ois.readObject();
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

        return answers;
    }

    /**
     * 返回ItemService中保存的由no指定的Item对象
     * @param no 编号
     * @return Item对象
     */
    public Item getItem(int no) {
        return this.items[no];
    }

    public int getQuestionNum(){
        return this.items.length;
    }

    public char getRightAnswer(int no){
        return getItem(no).getAnswer().charAt(0);
    }

    public char[] getAllAnswer(){
        char[] answers = new char[getQuestionNum()];
        for (int i = 0; i < getQuestionNum(); i++) {
            answers[i] = getRightAnswer(i);
        }
        return answers;
    }

    public int getScore(){
        char[] rightAnswers = getAllAnswer();
        char[] examAnswers = getExamAnswer();
        int score = 0;
        for (int i = 0; i < getQuestionNum(); i++) {
            if (rightAnswers[i]==examAnswers[i]){
                score += 100/getQuestionNum();
            }
        }
        return score;
    }

    /**
     * 读取文本文件内容，存到数组中
     * @param fileName  文件url
     */
    private List<String> readTextFile(String fileName){
        //输入流
        List<String> questions = new ArrayList<>();
        BufferedReader bis = null;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            bis = new BufferedReader(isr);

            String str = "";
            while ((str = bis.readLine()) != null){
                if (!"".equals(str.trim())){
                    questions.add(str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return questions;
    }
}
