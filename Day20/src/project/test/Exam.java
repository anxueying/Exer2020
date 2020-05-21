package project.test;


import project.service.ExamException;
import project.service.ItemService;
import project.view.ExamView;

import java.util.List;

/**
 * @author Mrs.An Xueying
 * 2020/5/21 16:39
 * 程序入口
 */
public class Exam {
    public static void main(String[] args) {
        ExamView ev = new ExamView();
        boolean loopFlag = true;
        do{
            String c1 = "\n1. 进入考试";
            String c2 = "2. 显示上次考试成绩";
            String c3 = "3. 退出系统";
            System.out.println(c1+"\n"+c2+"\n"+c3);
            System.out.print("请选择：");
            char key = ev.getUserAction();
            switch (key){
                case '1':
                    ev.testExam();
                    break;
                case '2':
                    System.out.println("上次考试分数为："+ev.getLastScore());
                    break;
                case 'N':
                    System.out.println("返回主菜单");
                    break;
                case '3':
                    System.out.print("确认退出（Y/N）：");
                    char yn =  ev.getUserAction();
                    if (yn=='Y'){
                        System.out.println("退出考试系统，感谢使用！");
                        loopFlag=false;
                    }
                    break;
                default:
                    break;
            }
        }while (loopFlag);

    }
}
