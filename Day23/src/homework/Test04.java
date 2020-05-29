package homework;

import java.lang.reflect.Field;

/**
 * @author Mrs.An Xueying
 * 2020/5/28 14:28
 */
public class Test04 {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("homework.User");
        String className = clazz.getSimpleName();
        String tableName = clazz.getDeclaredAnnotation(Table.class).value();
        System.out.println(className+"类对应的数据库表："+tableName);

        for (Field field : clazz.getDeclaredFields()) {
            Column annotation = field.getDeclaredAnnotation(Column.class);
            String fieldName = field.getName();
            String name = annotation.name();
            String type = annotation.type();
            System.out.println(fieldName+"属性对应数据库表的字段："+name+"，类型："+type);
        }
    }
}
