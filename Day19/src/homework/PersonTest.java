package homework;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mrs.An Xueying
 * 2020/5/19 21:29
 * test1  三个不同person对象，序列化
 * test2  数组persons 序列化
 * test3  list person 序列化
 *
 * test4 反序列化person.bat
 * test5 反序列化persons.bat
 * test6 反序列化personList.bat
 */
public class PersonTest {
    @Test
    public void test1(){

        Person p1 = new Person("张三", 18, 50);
        Person p2 = new Person("李四", 20, 60);
        Person p3 = new Person("王五", 23, 70);

        BufferedOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fos = new FileOutputStream("person.dat");
            bos = new BufferedOutputStream(fos);
            oos = new ObjectOutputStream(bos);

            oos.writeObject(p1);
            oos.writeObject(p2);
            oos.writeObject(p3);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos!=null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos!=null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test2(){
        Person[] persons = new Person[3];
        persons[0] = new Person("张三", 18, 50);
        persons[1] = new Person("李四", 20, 60);
        persons[2] = new Person("王五", 23, 70);

        BufferedOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fos = new FileOutputStream("persons.dat");
            bos = new BufferedOutputStream(fos);
            oos = new ObjectOutputStream(bos);

            oos.writeObject(persons);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos!=null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos!=null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test3(){

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("张三", 18, 50));
        personList.add(new Person("李四", 20, 60));
        personList.add(new Person("王五", 23, 70));

        BufferedOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fos = new FileOutputStream("personList.dat");
            bos = new BufferedOutputStream(fos);
            oos = new ObjectOutputStream(bos);

            oos.writeObject(personList);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos!=null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos!=null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test4(){
        BufferedInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream("person.dat");
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);

            Person p1 = (Person)ois.readObject();
            Person p2 = (Person)ois.readObject();
            Person p3 = (Person)ois.readObject();

            System.out.println(p1);
            System.out.println(p2);
            System.out.println(p3);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois!=null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis!=null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test5(){
        BufferedInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream("persons.dat");
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);

            Person[] persons = (Person[]) ois.readObject();

            for (Person person : persons) {
                System.out.println(person);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois!=null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis!=null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test6(){
        BufferedInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream("personList.dat");
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);

            Object obj = ois.readObject();

            List<Person> result = new ArrayList<>();
            if (obj instanceof ArrayList<?>) {
                for (Object o : (List<?>) obj) {
                    result.add((Person)o);
                }
            }

            for (Person person : result) {
                System.out.println(person);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois!=null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis!=null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
