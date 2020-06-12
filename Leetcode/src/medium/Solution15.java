package medium;

import java.awt.font.NumericShaper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Mrs.An Xueying
 * 2020/6/12 23:22
 */
public class Solution15 {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0) {
            return result;
        }

        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if ( i>0 && nums[i] == nums[i-1] ) {
                continue;
            }

            int target = -nums[i];
            int left = i+1;
            int right = nums.length-1;
            while (left<right){
                if (nums[left]+nums[right]==target){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);
                    left++;right--;
                    while (left<nums.length &&nums[left]== nums[left-1]){left++;}
                    while (right> left && nums[right]== nums[right+1]){right--;}
                }else if(nums[left]+nums[right]>target){
                    right--;
                }else {
                    left++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println(result);
    }
}
