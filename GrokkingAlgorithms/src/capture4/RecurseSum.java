package capture4;

/**
 * @author Mrs.An Xueying
 * 2020/6/4 18:04
 * 练习题4.1、4.2、4.3、4.4
 */
public class RecurseSum {
    public static void main(String[] args) {

        int[] nums = {2, 4, 6};
        int sumNums = sumNums(nums, 0);
        int countNums = countNums(nums, 0);
        int maxNums = maxNums(nums, 0);
        int indexNums = indexNums(nums, 0,nums.length,6);
        System.out.println(indexNums);


    }

    /**
     * 练习4.1
     *
     * @param nums  数组
     * @param begin 开始位置
     * @return 数组和
     */
    public static int sumNums(int[] nums, int begin) {
        if (begin == nums.length - 1) {
            return nums[nums.length - 1];
        }
        return nums[begin] + sumNums(nums, ++begin);
    }

    /**
     * 练习4.2
     *
     * @param nums  数组
     * @param begin 开始位置
     * @return 数组元素个数
     */
    public static int countNums(int[] nums, int begin) {
        if (begin == nums.length - 1) {
            return 1;
        }
        return 1 + countNums(nums, ++begin);
    }

    /**
     * 练习4.3
     *
     * @param nums  数组
     * @param begin 开始位置
     * @return 数组最大元素
     */
    public static int maxNums(int[] nums, int begin) {
        if (begin == nums.length - 1) {
            return nums[nums.length - 1];
        }
        return Math.max(nums[begin], maxNums(nums, ++begin));
    }

    /**
     * 二分查找（默认数组已排好序） 递归实现
     * @param nums 数组
     * @return 查找元素的index
     */
    public static int indexNums(int[] nums, int start, int end, int n) {
        //基线条件
        if (start<=end){
            //这里是start+end/2
            // [11,12,13,14,15] start2,end 4 ,取中应该为2+4/2=3
            // 而非4-2/2=1  这是数组截断后的坐标
            int middle = (end+start)/2;
            //递归
            if (nums[middle] < n) {
                return indexNums(nums,middle,end, n);
            } else if (nums[middle] > n) {
                return indexNums(nums,0,middle,n);
            } else{
                return middle;
            }
        }else {
            return -1;
        }

    }
}
