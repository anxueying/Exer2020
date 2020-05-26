package homework.project4.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import homework.project4.domain.Item;

public class ItemService {

	private Item[] items;

	private final String ITEM_FILENAME = "./Items.txt";
	private final String ANSWER_FILENAME = "./answer.dat";

	public final int TOTAL_ITEM;
	private final int LINE_NUM = 6;

	public ItemService() {

		List<String> list = readTextFile(ITEM_FILENAME);

		TOTAL_ITEM = list.size() / LINE_NUM;

		items = new Item[TOTAL_ITEM];

		for (int i = 0; i < TOTAL_ITEM; i++) {

			String question = list.get(i * LINE_NUM + 0);

			String[] options = { list.get(i * LINE_NUM + 1), list.get(i * LINE_NUM + 2), list.get(i * LINE_NUM + 3),
					list.get(i * LINE_NUM + 4) };

			char answer = list.get(i * LINE_NUM + 5).charAt(0);

			items[i] = new Item(question, options, answer);
		}
	}

	/**
	 * 根据题号获取 Item 对象
	 * 
	 * @param no
	 * @return
	 */
	public Item getItem(int no) {// no : 题号
		if (no < 1 || no > TOTAL_ITEM) {
			return null;
		}

		return items[no - 1];
	}

	/**
	 * 使用流的链接读取文本文件的内容
	 * 
	 * @param fileName
	 * @return
	 */
	private List<String> readTextFile(String fileName) {
		List<String> list = new ArrayList<>();

		BufferedReader br = null;
		try {
			FileReader fr = new FileReader(fileName);
			br = new BufferedReader(fr);

			String str = null;
			while ((str = br.readLine()) != null) {
				if (!str.trim().equals(""))
					list.add(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	/**
	 * 使用流的链接将对象保存到磁盘中（对象的序列化）
	 * 
	 * @param answer
	 */
	public void saveAnswer(char[] answer) {
		ObjectOutputStream oos = null;
		try {
			FileOutputStream fos = new FileOutputStream(ANSWER_FILENAME);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			oos = new ObjectOutputStream(bos);

			oos.writeObject(answer);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 使用流的链接读取磁盘中存储的数组（对象的反序列化）
	 * @return
	 */
	public char[] readAnswer() {
		ObjectInputStream ois = null;
		char[] answer = new char[0];
		try {
			FileInputStream fis = new FileInputStream(ANSWER_FILENAME);
			BufferedInputStream bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis);

			answer = (char[]) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return answer;
	}
}
