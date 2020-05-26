package exer;

import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * 获取构造器和创建对象
 */
public class ReflectTest3 {

    /**
     * 通过反射获取类的构造器
     */
    @Test
    public void test() throws Exception {
        Class clazz = Women.class;
        //获取类中的构造器
        /*
         getConstructor(Class<?>... parameterTypes) : 获取指定的构造器
         参数 ： 形参的类型
         */
        Constructor constructor = clazz.getConstructor(String.class);
        //创建对象
        /**
         * newInstance(Object ... initargs) : 创建对象
         * 参数 ： 实参
         */
        Women w = (Women) constructor.newInstance("伟哥");

        //========================如果有public修饰的空参构造器可以直接创建对象==============

        clazz.newInstance();

    }

    /**
     * 通过反射获取类的构造器
     */
    @Test
    public void test2() throws Exception {

        Class clazz = Man.class;
        //获取私有构造器创建对象 - 可以获取所有权限修饰符修饰的构造器
        Constructor constructor = clazz.getDeclaredConstructor(int.class);
        //允许访问
        constructor.setAccessible(true);
        //创建对象
        constructor.newInstance(18);
    }

    @Test
    public void test3(){
        Class clazz = Women.class;
        //获取所有的构造器
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor c : declaredConstructors) {
            System.out.println(c);
        }
    }
}
