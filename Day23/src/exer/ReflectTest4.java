package exer;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 通过反射获取父类，接口，注解
 */
public class ReflectTest4 {
    /**
     * 获取父类
     */
    @Test
    public void test(){
        Class clazz = Student.class;
        /**
         * 获取父类。可以通过父类再获取父类中所有的结构
         */
        Class superclass = clazz.getSuperclass();
        System.out.println(superclass);
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println(method);
        }
    }

    /**
     * 获取接口
     */
    @Test
    public void test2(){
        Class clazz = Women.class;
        //获取接口
        Class[] interfaces = clazz.getInterfaces();
        for (Class c : interfaces) {
            System.out.println(c);
        }
    }

    /**
     * 获取注解
     */
    @Test
    public void test3() throws NoSuchMethodException {
        Class clazz = Women.class;
        //获取注解 - 获取的是类上的注解
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation a : annotations) {
            System.out.println(a);
        }

        System.out.println("-----------------如果想拿构造器（属性，方法）上的注解，先获取构造器（属性，方法）-----------------------------------------");

        //获取方法上的注解 --- 先获取该方法再获取该方法上的注解
        Method m = clazz.getMethod("info");
        //获取方法上的注解
        Annotation[] annotations1 = m.getAnnotations();
        for (Annotation a : annotations1) {
            System.out.println(a);
            //向下转型
            MyAnn ma = (MyAnn) a;
            System.out.println(ma.value());
        }
    }
}
