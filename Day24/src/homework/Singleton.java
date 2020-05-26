package homework;

import review.Person;

/**
 * @author Mrs.An Xueying
 * 2020/5/26 21:47
 */
public class Singleton {
    public static void main(String[] args) {
        Person1 p1 = Person1.getInstance();
        Person2 p2 = Person2.getInstance();

        System.out.println(p1);
        System.out.println(p2);
    }

}

/**
 * 饿汉
 */
class Person1{
    /**
     * 1. 构建私有构造器
     * 2. 创建对象并初始化
     * 3. 创建public static getInstance方法获得对象
     */
    private Person1() {};
    private static Person1 person = new Person1();
    public static Person1 getInstance(){
        return person;
    }

}

/**
 * 懒汉
 */
class Person2{
    /**
     * 1. 构建私有构造器
     * 2. 创建对象并初始化
     * 3. 创建public static getInstance方法获得对象
     */
    private Person2() {};
    private static Person2 person = null;
    public static Person2 getInstance(){
        if (person==null){
            synchronized (Person2.class){
                if (person == null){
                    person = new Person2();
                }
            }
        }
        return person;
    }
}