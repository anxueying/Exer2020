package homework;

public class StudentScore {
    public static void main(String[] args) {

        Student[] students = new Student[20];
        for(int i = 0;i<students.length;i++){
            //创建对象
            students[i] = new Student();
            //赋值
            students[i].setNumber(i);
            students[i].setState((int)Math.round(Math.random()*10));
            students[i].setScore((int)Math.round(Math.random()*100));
            //符合条件打印
            if(students[i].getState()==3){
                System.out.println("homework.Student Number: "+students[i].getNumber()+", Score: "+students[i].getScore());
            }
        }
    }
}

class Student{
    private int number;//学号
    private int state;//年纪
    private int score;//成绩

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}