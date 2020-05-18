package home.java;

import java.util.Comparator;
import java.util.TreeSet;


/**
 * @author 安雪莹
 *
 * 实验1
 */
public class Employee { //implements Comparable

    public static void main(String[] args) {
        Employee e1 = new Employee("a",21,new MyDate(12,21,1990));
        Employee e2 = new Employee("a",21,new MyDate(12,21,1990));
        //Employee e2 = new Employee("b",22,new MyDate(12,22,1990));
        Employee e3 = new Employee("c",23,new MyDate(12,11,1990));
        Employee e4 = new Employee("d",24,new MyDate(12,10,1990));
        Employee e5 = new Employee("e",25,new MyDate(12,5,1990));

        /*TreeSet ts = new TreeSet();
       */

        //2). 创建 TreeSet 时传入 Comparator对象，按生日日期的先后排序
        TreeSet ts = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Employee && o2 instanceof Employee){
                    Employee e1 = (Employee)o1;
                    Employee e2 = (Employee)o2;
                    if (e1.name.equals(e2.name)){
                        return e1.birthday.compareTo(e2.birthday);
                    }else {
                        return e1.name.compareTo(e2.name);
                    }
                }
                return 0;
            }
        });
        ts.add(e1);
        ts.add(e2);
        ts.add(e3);
        ts.add(e4);
        ts.add(e5);
        System.out.println(ts);

    }

    private String name;
    private Integer age;
    private MyDate birthday;

    public Employee(String name, Integer age, MyDate birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

   /* @Override
    *//**
     * 1). 使Employee 实现 Comparable 接口，并按 name 排序
     *//*
    public int compareTo(Object o) {
        if (o instanceof Employee){
            Employee employee = (Employee)o;
            if (employee.name.equals(this.name)){
                return employee.birthday.compareTo(this.birthday);
            }else {
                return employee.name.compareTo(this.name);
            }
        }
        return 0;
    }*/

    public Employee() {
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

    public MyDate getBirthday() {
        return birthday;
    }

    public void setBirthday(MyDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return name + "\t" + age + "\t" + birthday.toString() ;
    }
}


class MyDate implements Comparable{
    private int month;
    private int day;
    private int year;

    public MyDate() {
    }

    public MyDate(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return  year + "年"+ month + "月" + day + "日" ;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof MyDate){
            MyDate birthday = (MyDate)o;
            if (birthday.year == this.year){
                if (birthday.month == this.month){
                    return birthday.day-this.day;
                }else {
                    return birthday.month-this.month;
                }
            }else {
                return birthday.year-this.year;
            }
        }
        return 0;
    }
}
