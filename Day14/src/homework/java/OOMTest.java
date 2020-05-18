package homework.java;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/*
jvm常见的oom异常，可以大致分为以下四种情况：

堆内存溢出，堆上对象分配空间不足。
栈内存溢出，栈内存问题有stackoverflowerror与outofmemoryerror两类，实际演示中stackoverflowerror比outofmemory更容易出现。
常量内存溢出
直接内存溢出
 */
class OOMTest1 {
    /*
    public static void main(String[] args) {
    //栈内存溢出 java.lang.StackOverflowError
        main(args);
    }
    */

/*
    public static void main(String[] args) {
        //java.lang.OutOfMemoryError  堆内存溢出，堆上对象分配空间不足。
            int[] arr = new int[Integer.MAX_VALUE];
    }
*/
   /* public static void main(String[] args) {
        //常量内存溢出 java.lang.OutOfMemoryError: GC overhead limit exceeded
        //-verbose:gc -Xms10M -Xmx10M -Xss128k -XX:+PrintGCDetails
        try {
            List<String> list = new ArrayList<String>();
            int item = 0;
            while(true){
                list.add(String.valueOf(item++).intern());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }*/


    private static final int ONE_MB = 1024*1024*1024;
    private static int count = 1;
    public static void main(String[] args) {
        //直接内存溢出 java.lang.OutOfMemoryError: Direct buffer memory
        //-verbose:gc -Xms10M -Xmx10M -XX:MaxDirectMemorySize=5M -Xss128k -XX:+PrintGCDetails
        try {
            while(true){
                ByteBuffer buffer = ByteBuffer.allocateDirect(ONE_MB);
                count++;
            }
        } catch (Exception e) {
            System.out.println("exception occur,instance count : "+count);
            e.printStackTrace();
        }
    }
}
