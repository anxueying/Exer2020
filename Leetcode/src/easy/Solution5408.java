package easy;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mrs.An Xueying
 * 2020/5/30 23:10
 */
public class Solution5408 {
    /**
     * 给你两个长度相同的整数数组 target 和 arr 。
     * 每一步中，你可以选择 arr 的任意 非空子数组 并将它翻转。你可以执行此过程任意次。
     * 如果你能让 arr 变得与 target 相同，返回 True；否则，返回 False 。
     */
    public static boolean canBeEqual(int[] target, int[] arr) {
        if (target.length != arr.length) {
            return false;
        }

        Arrays.sort(target);
        Arrays.sort(arr);

        for (int i : target) {
            System.out.print(i);
        }

        for (int i : arr) {
            System.out.print(i);
        }


        for (int i = 0; i < arr.length; i++) {
            if (target[i]!=arr[i]){
                return false;
            }
        }

        return true;


        /*
        输入：target = [1,2,3,4], arr = [2,4,1,3]
        输出：true
        解释：你可以按照如下步骤使 arr 变成 target：
        1- 翻转子数组 [2,4,1] ，arr 变成 [1,4,2,3]
        2- 翻转子数组 [4,2] ，arr 变成 [1,2,4,3]
        3- 翻转子数组 [4,3] ，arr 变成 [1,2,3,4]
        上述方法并不是唯一的，还存在多种将 arr 变成 target 的方法。
         */

    }

    public static int[] reverse(int[] nums){
        if (nums==null||nums.length<2) {
            return nums;
        }

        int[] temp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            temp[i] = nums[nums.length-i-1];
        }

        return temp;
    }

    public static void main(String[] args) {
        System.out.println(canBeEqual(new int[]{1, 2, 3, 4}, new int[]{2, 4, 1, 3}));
    }

}
