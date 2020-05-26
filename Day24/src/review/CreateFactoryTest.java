package review;

import exer.MyAnn;

import java.lang.reflect.Field;

/**
 * @author Mrs.An Xueying
 * 2020/5/26 9:16
 * 通过反射获取注解的内容创建对象，并给属性赋值
 * 思路：
 * 1. 先获取CreateFactory的Class实例
 * 2. 获取属性p
 * 3. 获取属性上的注解
 * 4. 获取注解中的信息--全类名
 * 5. 根据全类名创建出对象
 * 6. 把对象赋值给属性p
 */
public class CreateFactoryTest {
    public static void main(String[] args) throws Exception {
        CreateFactory createFactory = new CreateFactory();

        //先获取CreateFactory的Class实例
        Class clazz = CreateFactory.class;
        //获取属性
        Field p = clazz.getDeclaredField("p");
        //获取属性上的注解  是什么类型的注解就创建什么类的对象
        MyAnn ann = p.getAnnotation(MyAnn.class);
        //类转换
        String value = ann.value();
        Class classTemp = Class.forName(value);
        Person person = (Person) classTemp.newInstance();
        p.set(createFactory, person);

        //验证 可输出则赋值成功
        System.out.println(createFactory.p.getName());

    }
}
