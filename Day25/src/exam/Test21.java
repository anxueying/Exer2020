package exam;

/**
 * @author Mrs.An Xueying
 * 2020/5/28 9:52
 * 1、请写一个冒泡排序，实现{5,7,3,9,2}从小到大排序
 */
public class Test21 {
    public static void main(String[] args) {
        int[] nums = new int[]{5,7,3,9,2};
        sort(nums);
        for (int num : nums) {
            System.out.print(num);
        }
    }

    public static void sort(int[] ints){
        if (ints.length<2){return;}
        for (int i = 0; i < ints.length-1; i++) {
            for (int j = 0; j < ints.length-i-1; j++) {
                if (ints[j]>ints[j+1]){
                    int temp = ints[j];
                    ints[j] = ints[j+1];
                    ints[j+1] = temp;
                }
            }
        }
    }
}
