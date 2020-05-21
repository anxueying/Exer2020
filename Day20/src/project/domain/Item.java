package project.domain;

import java.util.Arrays;

/**
 * @author Mrs.An Xueying
 * 2020/5/21 16:37
 * 考试题目类，每个Item对象对应一道题目
 */
public class Item {
    private String question;
    private String[] options;
    private String answer;

    public Item(String question, String[] options, String answer) {
        this.question = question;
        this.options = options;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Item{" +
                "question='" + question + '\'' +
                ", options=" + Arrays.toString(options) +
                ", answer='" + answer + '\'' +
                '}';
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
