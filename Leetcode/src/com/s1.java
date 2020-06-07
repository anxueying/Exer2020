package com;

import java.util.HashMap;

/**
 * @author Mrs.An Xueying
 * 2020/6/7 10:31
 */
public class s1 {
    /**
     * 大神的更节省内存
     * @param nums
     * @param n
     * @return
     */
    public int[] shuffle1(int[] nums, int n) {
        int[] result = new int[2 * n];
        for (int i = 0; i < n; i++) {
            result[2 * i] = nums[i];
            result[2 * i + 1] = nums[i + n];
        }
        return result;
    }


    public static int[] shuffle(int[] nums, int n) {
        /**
         * index 0 1 2 3
         *       1 2 3 4
         *       0 2 1 3
         */
        //新建数组
        int[] ret = new int[nums.length];
        int nums_index1= 0;
        int nums_index2= n;

        for (int i = 0; i < nums.length; i++) {
            ret[i] = nums[nums_index1++];
            ret[++i] = nums[nums_index2++];
        }
        return  ret;
    }

    public static void main(String[] args) {
        int[] ints = shuffle(new int[]{1, 2, 3, 4, 4, 3, 2, 1}, 4);
        for (int i : ints) {

            System.out.println(i);
        }
    }
}
