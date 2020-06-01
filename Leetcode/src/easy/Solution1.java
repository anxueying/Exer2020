package easy;

import java.util.HashMap;

/**
 * @author Mrs.An Xueying
 * 2020/5/30 11:20
 * 1. 两数之和
 * 解题思路：
 * (先尝试自己解决，执行通过了再提交。实在没思路再看题解，然后整理思路默写）
 * 1. 分析需求，分析特殊情况
 * 2. 与面试者讨论
 * 3. 根据讨论的结果，使用适合的算法
 * 4. 暴力算法并非一无是处，先解决问题是所有优化动作的起点。
 */
class Solution1 {
    public static void main(String[] args) {
        Solution1 s = new Solution1();
        for (int i : s.twoSum2(new int[]{2, 3, 4, 5}, 6)) {
            System.out.print(i+"\t");
        }
    }

    /**
     * 暴力解法:
     *   外层遍历控制元素位置
     *   内层遍历，从元素位置右侧第一个元素开始遍历
     *   两数相加等于目标值，结束遍历，返回数组
     *   为空返回空数组
     * 时间复杂度 O(n2) 空间复杂度 O(1)
     * 优点：代码可读性高
     * 缺点：效率最低
     *
     * @param nums 整数数组
     * @param target 目标值
     * @return 两数之和为目标值的索引值数组
     */
    public int[] twoSum1(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i]+nums[j]==target){
                    return new int[]{   i,j};
                }
            }
        }
        return new int[]{};
    }

    /**
     * 哈希表优势：
     * lookup time O(1)
     * update/store time O(1)
     *
     * 思路：一个HashMap，key-num，value-index，遍历一次
     * 1. 遍历数组，遍历时：
     * 2. 如target-num的值在HashMap中无法找到：存储num和index
     * 3. 如可以找到，返回HashMap中的value和num的index即可
     * 4. 遍历结束仍未找到，返回空数组
     * 思路2：遍历两遍，第一遍存储，第二遍查找，此种思路比较简单，时、空复杂度与本思路一样
     *
     * 时间复杂度 O(n)  空间复杂度 O(n)
     * @param nums 数组
     * @param target 目标值
     * @return 两数之和为目标值的索引值数组
     */
    public int[] twoSum2(int[] nums, int target) {
        //HashMap中只能存放object
        HashMap<Integer,Integer> hashMap = new HashMap<>(nums.length);

        for (int i = 0; i < nums.length; i++) {
            if (hashMap.get(target-nums[i])==null){
                hashMap.put(nums[i],i);
            }else {
                return new int[]{hashMap.get(target-nums[i]),i};
            }
        }
        return new int[]{};
    }
}

