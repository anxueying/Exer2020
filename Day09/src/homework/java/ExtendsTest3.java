package homework.java;

public class ExtendsTest3 {
    public static void main(String[] args) {
        Person p = new Person("张三",18);
        System.out.println(p.say());

        Student s = new Student("张三",18,89757,95.63);
        System.out.println(s.say());
    }
}

class Person{
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String say(){
        return "我是"+this.name+"，今年"+this.age+"岁";
    }
}

class Student extends Person{
    private int ID;
    private double Score;

    public Student() {
    }

    public Student(int ID, double score) {
        this.ID = ID;
        Score = score;
    }

    public Student(String name, int age, int ID, double score) {
        super(name, age);
        this.ID = ID;
        Score = score;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getScore() {
        return Score;
    }

    public void setScore(double score) {
        Score = score;
    }

    public String say(){
        return super.say()+"，学号是"+this.ID + "， 考试成绩是："+this.Score;
    }
}