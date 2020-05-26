package review;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Mrs.An Xueying
 * 2020/5/26 9:03
 * 给Person类中的私有属性设置，并调用私有方法
 */
public class PersonClassTest {
    public static void main(String[] args) throws Exception {
        Class<? extends Person> pClass = Person.class;

        Field name = pClass.getDeclaredField("name");
        //允许访问
        name.setAccessible(true);

        Person p = new Person();
        name.set(p,"axy");

        Method show = pClass.getDeclaredMethod("show", String.class);
        //允许访问
        show.setAccessible(true);
        show.invoke(p, "调用私有方法");

        System.out.println(p.getName());
    }
}
