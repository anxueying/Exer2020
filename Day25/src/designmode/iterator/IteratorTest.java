package designmode.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * @author Mrs.An Xueying
 * 2020/5/27 16:49
 *
 * 迭代器模式：提供一种方式，顺序访问一个集合对象中的各个元素不暴露该对象的内部信息
 *
 * 什么时候用： 自定义容器（集合）的时候
 *
 * 其实就是写一个iterator接口的实现类的对象
 */
public class IteratorTest {
    public static void main(String[] args) {
        MyList<String> list = new MyList<>();
        list.add("aaa");
        list.add("ccc");

        //遍历
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}

/**
 * 自定义一个容器
 * @param <T>
 */
class MyList<T>{

    private Object[] objects = new Object[10];
    /**
     * 统计元素的个数
     */
    private int size;

    public void add(T t){
        if (size>=objects.length){
            //扩容
            objects = Arrays.copyOf(objects, objects.length*2);
        }
        objects[size++] = t;
    }

    public Iterator iterator(){
        return new MyItr();
    }

    /**
     * 内部类
     * @param <T>
     */
    private class MyItr<T> implements Iterator<T>{
        //创建一个游标
        private int cursor;

        @Override
        public boolean hasNext() {
            //不可以cursor<objects.length  因为长度中不一定都有数据，要对比有效元素的个数
            return cursor<size;
        }

        @Override
        public T next() {
            return (T)objects[cursor++];
        }
    }
}
