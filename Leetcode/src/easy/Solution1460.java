package easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mrs.An Xueying
 * 2020/5/30 23:10
 */
public class Solution1460 {
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
        for (int i = 0; i < arr.length; i++) {
            if (target[i]!=arr[i]){
                return false;
            }
        }
        return true;
    }


    /**
     * 这个方法最好
     * @param target
     * @param arr
     * @return
     */
    public static boolean canBeEqual1(int[] target, int[] arr) {
        int[] bucket = new int[1001];
        for (int i = 0; i < target.length; i++) {
            bucket[target[i]]++;
            bucket[arr[i]]--;
        }
        for (int i : bucket) {
            if (i!=0){
                return false;
            }
        }
        return true;
    }


    public static boolean canBeEqual2(int[] target, int[] arr) {
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for(int i = 0; i< target.length;i++){
            int i1 = target[i];
            int i2 = arr[i];
            hashMap.merge(i1, 1, Integer::sum);
            hashMap.merge(i2, 1, Integer::sum);
        }

        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue()%2!=0){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(canBeEqual1(new int[]{7,2}, new int[]{2,7}));
    }

}
