package medium;

import java.util.Arrays;

/**
 * @author Mrs.An Xueying
 * 2020/6/14 12:39
 */
public class Solution1300 {
    public static int findBestValue(int[] arr, int target) {
        if (arr==null){return 0;}
        int arrSum = 0;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            int curr = (target - arrSum) / (arr.length - i);
            if (arr[i]>curr){
                int c1 = Math.abs(arrSum + curr * (arr.length - i) - target);
                int c2 = Math.abs(arrSum + (curr+1) * (arr.length - i) - target);
                return c1>c2?(curr+1):curr;
            }
            arrSum+=arr[i];
        }
        return arr[arr.length-1];
    }

    public static void main(String[] args) {
        int value = findBestValue(new int[]{4,9,3}, 10);
        System.out.println(value);
    }
}
