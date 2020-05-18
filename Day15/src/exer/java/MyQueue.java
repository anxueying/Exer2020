package exer.java;

import java.util.LinkedList;

/*
堆栈：先进后出
队列：先进先出
 */
public class MyQueue {
    public static void main(String[] args) {
       /* StackTest st = new StackTest();
        st.add("AA");
        st.add("BB");
        st.add("CC");
        st.add("DD");
        st.add("EE");
        while (!st.isNull()) {
            Object obj = st.get();
            System.out.println(obj);
        }*/

        QueueTest qt = new QueueTest();
        qt.add("AA");
        qt.add("BB");
        qt.add("CC");
        qt.add("DD");
        qt.add("EE");
        while (!qt.isNull()) {
            Object obj = qt.get();
            System.out.println(obj);
        }
    }
}


class StackTest {
    private LinkedList linkedList = new LinkedList();

    //添加
    public void add(Object obj) {
        linkedList.addFirst(obj);
    }

    //获取
    public Object get() {
        return linkedList.removeFirst();
    }

    //判断是否为空集
    public boolean isNull() {
        return linkedList.isEmpty();
    }

}

class QueueTest {
    private LinkedList linkedList = new LinkedList();

    //添加
    public void add(Object obj) {
        linkedList.addFirst(obj);
    }

    //获取
    public Object get() {
        return linkedList.removeLast();
    }

    //判断是否为空集
    public boolean isNull() {
        return linkedList.isEmpty();
    }

}



