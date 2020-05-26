package exer;

/**
 * @author Mrs.An Xueying
 * 2020/5/26 19:34
 * 单一职责原则 ：只做一件事
 *
 * 1.往小的说小到一个方法，往大了说，一个类，一个包，一个模块都负责一个职责。
 *
 * 例 ：
 *      ①Arrays.sort() :只排序
 *      ②Arrays工具类 ： 只用来处理数组
 *      ③java.io : 该包中都是关于和流有关的类
 */
public class SRPTest {
    public static void main(String[] args) {
        int[] numbers = new int[]{10,90,32,-10};
        sort(numbers);
        print(numbers);

    }

    /**
     * 排序
     * @param numbers 数组
     */
    public static void sort(int[] numbers){
        int temp = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length-1; j++) {
                if (numbers[j]>numbers[j+1]){
                    temp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = temp;
                }
            }
        }
    }

    /**
     * 输出结果
     * @param numbers 数组
     */
    public static void print(int[] numbers){
        for (int number : numbers) {
            System.out.print(number+"\t");
        }

    }
}
