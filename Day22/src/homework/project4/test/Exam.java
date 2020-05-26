package homework.project4.test;

import homework.project4.service.ItemService;
import homework.project4.view.ExamView;

public class Exam {
	
	public static void main(String[] args) {
		ItemService itemSvc = new ItemService();
		
		/*List<String> list = itemSvc.readTextFile("./Items.txt");
		
		Iterator<String> it = list.iterator();
		
		while(it.hasNext()){
			String str = it.next();
			System.out.println(str);
		}*/
		
		/*Item item = itemSvc.getItem(10);
		System.out.println(item);*/
		
		/*char[] answer = {'A', 'B', 'C'};
		itemSvc.saveAnswer(answer);*/
		
		//----------------------------------------------------------
		
		ExamView ev = new ExamView();
		/*char key = ev.getUserAction();
		System.out.println(key);*/
//		ev.testExam();
		ev.enterMainMenu();
	}

}
