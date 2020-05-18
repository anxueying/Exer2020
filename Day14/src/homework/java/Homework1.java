package homework.java;

public class Homework1 {
    public static void main(String[] args) {
        int test = test(3,5);
        System.out.println(test);//8
    }

    public static int test(int x, int y){
        int result = x;
        try{
            if(x<0 || y<0){
                return 0;
            }
            result = x + y;
            return result;//(1)先把result的值8放到操作数栈中，（2）然后走finally（3）结束当前方法
        }finally{
            result = x - y;//这个result=-2的值，不会放到操作数栈中，因为没有return
        }
    }
}
