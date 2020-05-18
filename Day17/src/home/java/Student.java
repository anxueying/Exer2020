package home.java;

import java.util.Comparator;
import java.util.Objects;
import java.util.TreeSet;

/**
 * @author 安雪莹
 * day16 list、set  第8题
 */
public class Student implements Comparable{
    public static void main(String[] args) {
        Student s1 = new Student("liusan",20,90);
        Student s2 = new Student("lisi",22,90);
        Student s3 = new Student("wangwu",20,99);
        Student s4 = new Student("sunliu",22,100);

        TreeSet students = new TreeSet();

        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);

        System.out.println("按照成绩和年龄排序：");
        for (Object student : students) {
            System.out.println(student);
        }

        TreeSet students2 = new TreeSet(new Comparator() {
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Student && o2 instanceof Student){
                    Student s1 = (Student)o1;
                    Student s2 = (Student)o2;

                    return s1.name.compareTo(s2.name);
                }
                return 0;
            }
        });

        students2.addAll(students);

        System.out.println("按照姓名排序：");
        for (Object o : students2) {
            System.out.println(o);
        }
    }
    private String name;
    private Integer age;
    private double score;

    public Student() {
    }

    public Student(String name, Integer age, double score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Student){
            Student student = (Student)o;
            if (student.score==this.score){
                return this.age-student.age;
            }else {
                return (int) (student.score-this.score);
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Double.compare(student.score, score) == 0 &&
                name.equals(student.name) &&
                age.equals(student.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, score);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }
}
