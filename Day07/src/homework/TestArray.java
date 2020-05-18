package homework;

import java.util.Arrays;
public class TestArray {
    public static void main(String[] args) {
        int[] array3,array2;
        //简单数组：声明并赋值，同时可省略new int[]
        int[] array1 = {2,3,5,7,11,13,17,19};
        //array1 = new int[]{2,3,5,7,11,13,17,19};
        for (int a:array1){
            System.out.print(a+"\t");
        }
        System.out.println();

        //array2 = array1;//此时赋值的是地址值，所以array2修改了，array1也修改了
        array2 = new int[array1.length];
        for (int i = 0;i<array2.length;i++){
            array2[i] = array1[i];//拓展：实现复制操作
            if(i%2==0){
                array2[i]=i;
            }
        }

        //反转
        array3 = new int[array1.length];
        for(int i=0;i<array1.length/2;i++){
            array3[i] = array1[array1.length-i-1];
            array3[array1.length-i-1] = array1[i];
        }

        /* 自身反转
        for (int i=0;i<array1.length/2;i++){
            int temp = array1[i];
            array1[i] = array1[array1.length-1-i];
            array1[array1.length-1-i] = temp;
        }

         */
        

        for (int a:array1){
            System.out.print(a+"\t");
        }
        System.out.println();

        for (int a:array2){
            System.out.print(a+"\t");
        }

        System.out.println();

        for (int a:array3){
            System.out.print(a+"\t");
        }
        System.out.println();

        //排序
        Arrays.sort(array2);

        for (int a:array2){
            System.out.print(a+"\t");
        }

    }
}
