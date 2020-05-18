package exer.java;

import org.junit.Test;

import java.util.*;

/*
创建该类的 5 个对象，并把这些对象放入 TreeSet 集合中分别按以下两种方式对集合中的元素进行排序，并遍历输出：

1). 使Employee 实现 Comparable 接口，并按 name 排序
2). 创建 TreeSet 时传入 Comparator对象，按生日日期的先后排序。

提示：Employee类是否需要重写equals()方法？不需要
MyDate类呢？需要
 */
public class Employee implements Comparable{ //

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


     /**
     * 1). 使Employee 实现 Comparable 接口，并按 name 排序
     */
     @Override
     public int compareTo(Object o) {
        if (o instanceof Employee){
            Employee employee = (Employee)o;
            if (employee.name.equals(this.name)){
                return employee.age.compareTo(this.age);
            }else {
                return employee.name.compareTo(this.name);
            }
        }
        return 0;
     }

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


/**
 * 中定义一个Map 成员变量，Map 的键为 String 类型，值为 T 类型。
 * @param <T>
 */
class Dao<T>{
    private Map<String,T> hashMap;

    /**
     * 保存 T 类型的对象到 Map 成员变量中
     * @param id
     * @param entity
     */
    public void save(String id,T entity){
        hashMap.put(id,entity);
    }

    /**
     * 从 map 中获取 id 对应的对象
     * @param id
     * @return
     */
    public T get(String id){
        return hashMap.get(id);
    }

    /**
     * 替换 map 中key为id的内容,改为 entity 对象
     * @param id
     * @param entity
     */
    public void update(String id,T entity){
        Collections.replaceAll(Arrays.asList(hashMap),get(id),entity);
    }

    /**
     * 返回 map 中存放的所有 T 对象
     * @return
     */
    public List<T> list(){
        return (List)hashMap.values();
    }

    /**
     * 删除指定 id 对象
     * @param id
     */
    public void delete(String id){
        hashMap.remove(id);
    }
}


/**
 * 该类包含：private成员变量（int类型） id，age；（String 类型）name。
 */
class User{
    private int id;
    private int age;
    private String name;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {
    }

    public User(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }
}

