package homework.project4.domain;

import java.util.Arrays;

public class Item {

	private String question;
	private String[] options;
	private char answer;

	public Item() {
	}

	public Item(String question, String[] options, char answer) {
		this.question = question;
		this.options = options;
		this.answer = answer;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String[] getOptions() {
		return options;
	}

	public void setOptions(String[] options) {
		this.options = options;
	}

	public char getAnswer() {
		return answer;
	}

	public void setAnswer(char answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "Item [question=" + question + ", options=" + Arrays.toString(options) + ", answer=" + answer + "]";
	}

}
