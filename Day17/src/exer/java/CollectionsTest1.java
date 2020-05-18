package exer.java;

import java.util.*;

/**
 * @author Lenovo
 * 请把学生名与考试分数录入到Map中，并按分数显示前三名成绩学员的名字。
 */
public class CollectionsTest1 {
    private static final Object PRESENT = new Object();
    public static void main(String[] args) {

        TreeMap studentScores = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Student && o2 instanceof Student){
                    Student s1  = (Student)o1;
                    Student s2  = (Student)o2;
                    if (s1.getScore().equals(s2.getScore())){
                        return s1.getName().compareTo(s2.getName());
                    }else {
                        return -s1.getScore().compareTo(s2.getScore());
                    }
                }
                return 0;
            }
        });
        studentScores.put(new Student("a",99.9),PRESENT);
        studentScores.put(new Student("b",100.0),PRESENT);
        studentScores.put(new Student("c",80.0),PRESENT);
        studentScores.put(new Student("d",30.0),PRESENT);
        studentScores.put(new Student("e",55.0),PRESENT);

        //获取成绩为前三的学员

        Set keySet = studentScores.keySet();

        Object[] objs = keySet.toArray();

        for (int i = 0; i < 3; i++) {
            //System.out.println(objs[i]);
            System.out.println(((Student) objs[i]).getName());
        }
    }
}

class Student{
    private String name;
    private Double score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        Student student = (Student) o;
        return Double.compare(student.score, score) == 0 &&
                name.equals(student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score);
    }

    public Student(String name, Double score) {
        this.name = name;
        this.score = score;
    }
}