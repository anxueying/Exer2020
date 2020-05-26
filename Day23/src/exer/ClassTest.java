package exer;

import org.junit.Test;

/**
 * @author Mrs.An Xueying
 * 2020/5/25 15:03
 */
public class ClassTest{
    @Test
    public void test1() throws ClassNotFoundException {
        Person p = new Person();
        //1.通过调用类.class获取
        Class clazz1 = Person.class;

        //2.通过对象名.getClass()获取
        Class clazz2 = p.getClass();

        //3.通过Class.forName("类的全类名")获取 - 全类名：包含包名在内的类的全名称
        Class clazz3 = Class.forName("exer.Person");

        //4.通过类加载器（了解）
        //通过运时类的对象获取类加载器
        ClassLoader classLoader = this.getClass().getClassLoader();
        //通过类加器的loadClass(全类名)
        Class clazz4 = classLoader.loadClass("exer.Person");

    }
}
