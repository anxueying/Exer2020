package exer;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * @author Mrs.An Xueying
 * 2020/5/25 15:09
 * 通过反射获取类中的结构
 */
public class ReflectTest {
    /**
     * 通过反射调用对象中属性(public)
     */
    @Test
    public void test1() throws Exception {
        Person p1 = new Person();
        System.out.println("赋值前====" + p1.idPublic);
        //Class是反射的源头，那么首先肯定要先获取Class的对象
        Class clazz = p1.getClass();

        //通过反射获取要调用的属性
        Field idPublic = clazz.getField("idPublic");
        /**
         * getField(属性的名称) ： 根据属性的名字获取该属性对应的对象
         * 注意 ：只能调用public修饰的属性
         */


        idPublic.set(p1,"axy");

        /**
         * set(Object obj,Object value) : 给属性赋值
         * obj : 指定赋值属性的对象
         * value : 给属性赋值的内容
         */

        System.out.println("赋值后====" + p1.idPublic);


        //通过反射获取要调用的方法
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        Method method = clazz.getDeclaredMethod("demoPublic", String.class, int.class);
        method.invoke(p1, "d",1);

        for (Type type : method.getGenericParameterTypes()) {
            System.out.println(type);
        }

        System.out.println(method.getGenericReturnType());

        System.out.println(p1.idPublic);
        System.out.println(p1.numberPublic);

        //
    }

    /**
     * 体会反射带给我们的便利
     * 1.可以调用私有的属性方法构造器等
     * 2.可以通过循环的方式给所有的属性进行赋值
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        String[] fieldNames = {"idPublic"};
        String[] fieldValues = {"aaa"};

        Person p1 = new Person();
        System.out.println("赋值前====" + p1.idPublic);
        for (int i = 0; i < fieldNames.length; i++) {
            Class clazz = p1.getClass();
            Field field = clazz.getField(fieldNames[i]);

            field.set(p1, fieldValues[i]);
        }

        System.out.println("赋值后====" + p1.idPublic);
    }

    /**
     * 通过给方法设置对象和属性值。方法就会实现把所有属性的值 赋值给属性
     *
     * 给所有的属性赋值
     */
    public void setValue(Object obj,Class clazz,String[] values) throws Exception {
        //获取所有的属性
        Field[] declaredFields = clazz.getDeclaredFields();
        //创建数组用来存放所有的属性的名称
        String[] fieldNames = new String[declaredFields.length];
        for (int i = 0; i < declaredFields.length; i++) {
            //将属性的值放到了数组中。
            fieldNames[i] = declaredFields[i].getName();
        }

        //把属性的名字填充到数组中
        for (int i = 0; i < fieldNames.length; i++) {
            /**
             * 考虑私有属性的问题
             */
            Field field = clazz.getDeclaredField(fieldNames[i]);
            field.setAccessible(true);
            /**
             * set(Object obj,Object value) : 给属性赋值
             * obj : 指定赋值属性的对象
             * value : 给属性赋值的内容
             */
            field.set(obj,values[i]);
        }
    }
}
