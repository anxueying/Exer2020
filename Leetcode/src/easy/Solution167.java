package easy;

/**
 * @author Mrs.An Xueying
 * 2020/5/30 14:14
 * 167. 两数之和 II - 输入有序数组
 */
public class Solution167 {
    /**
     * 考虑数组为从小到大排序过的，如何利用数组已排序的优势
     * 思路： 左指针left 右指针right
     * 时间复杂度：O(n) 空间复杂度：O(1)
     * 优点：如数组已排序，空间复杂度更低
     * 缺点：必须已排序，否则排序占用的时间复杂度为O(n*logn)
     *
     * 1. 最开始left=0，right=nums.length-1
     * 2. left固定，移动right，直到两数相加 小于 target
     * 3. right固定，移动left，直到两数相加 等于 target
     * 4. 当left=right时，仍未找到，则返回长度为2的空数组
     *
     * @param nums 数组
     * @param target 目标值
     * @return 两数之和为目标值的索引值数组
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums==null || nums.length<=1){return new int[2];}
        int left = 0 ,right =  nums.length-1;
        while (left<right){
            if (nums[left] + nums[right] > target){
                right--;
            }else if (nums[left] + nums[right] < target){
                left++;
            }else {
                return new int[]{left,right};
            }
        }
        return new int[2];
    }
}
