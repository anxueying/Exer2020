package homework.project4.view;

import java.util.Scanner;

import homework.project4.domain.Item;
import homework.project4.service.ItemService;

public class ExamView {
	
	private ItemService itemSvc = new ItemService();
	
	private char[] answer;
	
	public ExamView(){
		answer = new char[itemSvc.TOTAL_ITEM];
		
		for (int i = 0; i < answer.length; i++) {
			answer[i] = ' ';
		}
	}
	
	public void enterMainMenu(){
		boolean loopFlag = true;
		
		do{
			
			displayMainMenu();
			
			char key = getUserAction();
			
			switch(key){
				case '1':
					//进入考试
					testExam();
					break;
				case '2':
					//查看上一次考试成绩
					lastResultAnswer();
					break;
				case '3':
					//退出
					System.out.print("确认是否退出(Y/N):");
					char yn = getUserAction();
					if(yn == 'Y') loopFlag = false;
					break;
			}
			
		}while(loopFlag);
		
	}
	
    private void displayMainMenu() {
        System.out.println();
        System.out.println();
        System.out.println("-------欢迎使用在线考试系统-------");
        System.out.println();
        System.out.println("       1 进入考试");
        System.out.println("       2 查看成绩");
        System.out.println("       3 系统退出");
        System.out.println();
        System.out.print("       请选择...");
    }
	
	public void displayWelcomeInfo(){
        System.out.println();
        System.out.println();
        System.out.println("-----------欢迎进入考试-----------");
        System.out.println();
        System.out.println("       使用以下按键进行考试：");
        System.out.println();
        System.out.println("        A-D：选择指定答案");
        System.out.println("        P  ：显示上一题");
        System.out.println("        N  ：显示下一题");
        System.out.println("        F  ：结束考试");
        System.out.println();
        System.out.print("        请按N键进入考试...");
        
        while(true){
            char ch = getUserAction();
            
            if(ch == 'N'){
            	return;
            }
        }
	}
	
	/**
	 * 进入考试
	 */
	public void testExam(){
		
		displayWelcomeInfo();
		
		int currNum = 1;
		
		while(true){
			displayItem(currNum);
			
			char key = getUserAction();
			
			switch(key){
				case 'A':
				case 'B':
				case 'C':
				case 'D':
					answer[currNum-1] = key;
					//break;
				case 'N':
					//显示下一题
					if(currNum >= itemSvc.TOTAL_ITEM){
						System.out.println("已经到达最后一题！");
						
						/*char ch = getUserAction();
						
						if(ch == 'N'){
							itemSvc.saveAnswer(answer);
						}
						
						return;*/
					}else{
						currNum++;
					}
					break;
				case 'P':
					//显示上一题
					if(currNum <= 1){
						System.out.println("已经到达第一题！");
					}else{
						currNum--;
					}
					break;
				case 'F':
					System.out.print("确认是否结束考试(Y/N):");
					char yn = getUserAction();
					if(yn == 'Y'){
						//1. 保存答案
						itemSvc.saveAnswer(answer);
						
						//2. 自动判分
						resultAnswer(answer);
						
						return;
					}
					break;
			}
		}
		
	}
	
	/**
	 * 查看上一次考试成绩
	 */
	public void lastResultAnswer(){
		char[] answer = itemSvc.readAnswer();
		resultAnswer(answer);
	}
	
	/**
	 * 自动判分
	 * @param answer
	 */
	public void resultAnswer(char[] answer){
		int score = 0;
		
		for (int i = 0; i < itemSvc.TOTAL_ITEM; i++) {
			
			Item item = itemSvc.getItem(i+1);
			
			if(item.getAnswer() == answer[i]){
				score += 10;
			}
			
		}
		
		System.out.println("题号\t正确答案\t你的答案");
		
		for (int i = 0; i < itemSvc.TOTAL_ITEM; i++) {
			
			Item item = itemSvc.getItem(i+1);
			
			System.out.println((i+1) + "\t" + item.getAnswer() + "\t" + answer[i]);
		}
		
		System.out.println("恭喜，您的成绩为：" + score);
	}
	
	/**
	 * 根据题号显示考题内容
	 * @param no
	 */
	public void displayItem(int no){
		
		if(no < 1 || no > itemSvc.TOTAL_ITEM){
			return;
		}
		
		Item item = itemSvc.getItem(no);
		
		String question = item.getQuestion();
		System.out.println(question);
		
		String[] options = item.getOptions();
		for (String option : options) {
			System.out.println(option);
		}

		//
		if(answer[no-1] != ' '){
			System.out.println("您已经选择答案：" + answer[no-1]);
		}
	}
	
	/**
	 * 从键盘读取指定的字符
	 * @return
	 */
	public char getUserAction(){
		char[] validKey = {'A', 'B', 'C', 'D', 'N', 'P', 'Y', 'F', '1', '2', '3'};
		
		Scanner scan = new Scanner(System.in);
		
		while(true){
			String str = scan.next().toUpperCase();
			
			if(str.length() != 1)
				continue;
			
			char key = str.charAt(0);
			
			for (char k : validKey) {
				if(k == key){
					return key;
				}
			}
		}
		
	}

}
