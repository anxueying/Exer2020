package exer;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetTest {
    @Test
    public void test5(){
        //内部类（匿名类，匿名对象）
        TreeSet ts = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Person && o2 instanceof Person){
                    Person p1 = (Person) o1;
                    Person p2 = (Person) o2;

                    if(p1.getAge().equals(p2.getAge())){
                        return p1.getName().compareTo(p2.getName());
                    }else{
                        return -p1.getAge().compareTo(p2.getAge());
                    }
                }

                return 0;
            }
        });//直接调用

        ts.add(new Person("张三", 18));
        ts.add(new Person("李四", 20));
        ts.add(new Person("王五", 35));
        ts.add(new Person("赵六", 9));
        ts.add(new Person("田七", 22));

        System.out.println(ts);
    }
    
    
    @Test
    public void test4(){
        //内部类（匿名类） new MyComparator1()和类定义一起进行  用接口创建多态
        Comparator com = new Comparator(){

            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Person && o2 instanceof Person){
                    Person p1 = (Person) o1;
                    Person p2 = (Person) o2;

                    if(p1.getAge().equals(p2.getAge())){
                        return p1.getName().compareTo(p2.getName());
                    }else{
                        return -p1.getAge().compareTo(p2.getAge());
                    }
                }

                return 0;
            }
        };

        TreeSet ts = new TreeSet(com);//直接调用多态接口
        ts.add(new Person("张三", 18));
        ts.add(new Person("李四", 20));
        ts.add(new Person("王五", 35));
        ts.add(new Person("赵六", 9));
        ts.add(new Person("田七", 22));

        System.out.println(ts);
    }


    @Test
    public void test3(){
        //把类放在内部  只对当前类有效
        class MyComparator1 implements Comparator {

            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Person && o2 instanceof Person){
                    Person p1 = (Person) o1;
                    Person p2 = (Person) o2;

                    if(p1.getAge().equals(p2.getAge())){
                        return p1.getName().compareTo(p2.getName());
                    }else{
                        return -p1.getAge().compareTo(p2.getAge());
                    }
                }

                return 0;
            }
        }


        TreeSet ts = new TreeSet(new MyComparator1());//需创建对象new MyComparator1()
        ts.add(new Person("张三", 18));
        ts.add(new Person("李四", 20));
        ts.add(new Person("王五", 35));
        ts.add(new Person("赵六", 9));
        ts.add(new Person("田七", 22));

        System.out.println(ts);
    }

    @Test
    public void test2(){//自定义排序
        TreeSet ts = new TreeSet(new MyComparator());//这里是TreeSet的构造器
        ts.add(new Person("张三", 18));
        ts.add(new Person("李四", 20));
        ts.add(new Person("王五", 35));
        ts.add(new Person("赵六", 9));
        ts.add(new Person("田七", 22));

        ts.add(new Person("张三", 18));

        System.out.println(ts);
    }

    @Test
    public void test1(){//无法比较,需重写compareTo，实现接口Comparable
        TreeSet ts = new TreeSet();
        ts.add(new Person("张三", 18));
        ts.add(new Person("李四", 20));
        ts.add(new Person("王五", 35));
        ts.add(new Person("赵六", 9));
        ts.add(new Person("田七", 22));

        ts.add(new Person("张三", 18));

        System.out.println(ts);
    }



}
