package homework;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Test01{
    @Test
    public void test01() throws ClassNotFoundException{
        Class clazz = Class.forName("homework.AtguiguDemo");

        ClassLoader classLoader = clazz.getClassLoader();
        System.out.println("类加载器：" + classLoader);

        Package pkg = clazz.getPackage();
        System.out.println("包名：" + pkg.getName());

        int cMod = clazz.getModifiers();
        System.out.println("类的修饰符：" + Modifier.toString(cMod));

        System.out.println("类名：" + clazz.getName());
        System.out.println("父类：" + clazz.getSuperclass().getName());
        Class[] interfaces = clazz.getInterfaces();
        System.out.println("父接口们："+Arrays.toString(interfaces));

        Field[] declaredFields = clazz.getDeclaredFields();
        for (int i =0 ;i<declaredFields.length; i++) {
            System.out.println("第" + (i+1) + "个字段：");
            int fMod = declaredFields[i].getModifiers();
            System.out.println("修饰符：" + Modifier.toString(fMod));
            System.out.println("数据类型："  + declaredFields[i].getType().getName());
            System.out.println("属性名：" + declaredFields[i].getName());
        }

        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for (int i = 0; i < declaredConstructors.length; i++) {
            System.out.println("第" + (i+1) + "个构造器：");
            int csMod = declaredConstructors[i].getModifiers();
            System.out.println("修饰符：" + Modifier.toString(csMod));
            System.out.println("构造器名：" + declaredConstructors[i].getName());
            System.out.println("形参列表：" + Arrays.toString(declaredConstructors[i].getParameterTypes()));
        }

        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (int i = 0; i < declaredMethods.length; i++) {
            System.out.println("第" + (i+1) + "个成员方法：");
            int csMod = declaredMethods[i].getModifiers();
            System.out.println("修饰符：" + Modifier.toString(csMod));
            System.out.println("返回值类型：" + declaredMethods[i].getReturnType().getName());
            System.out.println("方法名：" + declaredMethods[i].getName());
            System.out.println("形参列表：" + Arrays.toString(declaredMethods[i].getParameterTypes()));
        }
    }
    
    
    @Test
    public void test02() throws Exception {
        Class clazz = Class.forName("homework.AtguiguDemo");
        Field field = clazz.getDeclaredField("school");
        //必须开放权限才可写
        field.setAccessible(true);
        //用null即可取，是因为该属性是static，否则要用newInstance方法创建实例
        Object value = field.get(null);
        System.out.println("修改前： school = " + value);

        field.set(null, "尚硅谷大学");
        value = field.get(null);
        System.out.println("修改后： school = " + value);
    }


    @Test
    public void test03() throws Exception {
        Class clazz = Class.forName("homework.AtguiguDemo");
        Object o = clazz.newInstance();
        Field field = clazz.getDeclaredField("className");
        field.setAccessible(true);
        Object value = field.get(o);
        System.out.println("修改前： className = " + value);
        field.set(o,"大数据0421");
        value = field.get(o);
        System.out.println("修改后： className = " + value);
    }

    @Test
    public void test04() throws Exception {
        Class clazz = Class.forName("homework.AtguiguDemo");
        //调用有参构造器
        Constructor constructor = clazz.getDeclaredConstructor(String.class);
        Object o1 = constructor.newInstance("深圳大数据0421");
        Object o2 = constructor.newInstance("深圳大数据0522");

        Method compareTo = clazz.getDeclaredMethod("compareTo", Object.class);
        System.out.println(compareTo.invoke(o1, o2));

    }

}