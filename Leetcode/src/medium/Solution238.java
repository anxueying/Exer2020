package medium;

/**
 * @author Mrs.An Xueying
 * 2020/6/4 12:43
 */
public class Solution238 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4};
        for (int i = 0; i < nums.length; i++) {
            System.out.print(productExceptSelf(nums)[i]);
            System.out.print(productExceptSelf1(nums)[i]);
            System.out.print(productExceptSelf2(nums)[i]+"\n");
        }
    }
    /**
     * 最原始做法 时间复杂度O(n2)
     * @param nums 整数数组
     * @return 除自身以外数组的乘积的数组
     */
    public static int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        //result循环
        for(int i=0; i<nums.length;i++){
            result[i] = 1;
            //nums循环
            for(int j=0;j<nums.length;j++){
                if(i!=j){
                    result[i] *= nums[j];
                }
            }
        }
        return result;
    }

    /**
     * 不要使用除法，且在 O(n) 时间复杂度内完成此题。
     * @param nums 整数数组
     * @return 除自身以外数组的乘积的数组
     */
    public static int[] productExceptSelf1(int[] nums) {
        int length = nums.length;
        int[] result = new int[length];
        int[] left = new int[length];
        int[] right = new int[length];
        left[0] = 1;
        for (int i = 1; i < length; i++) {
            left[i] = nums[i-1]*left[i-1];
        }

        right[length-1] = 1;
        for (int i = length-2; i >=0; i--) {
            right[i] = nums[i+1]*right[i+1];
        }

        for (int i = 0; i < length; i++) {
            result[i] = left[i]*right[i];
        }

        return result;
    }


    /**
     * 不要使用除法，且在常数空间复杂度内完成。输出数组不被视为额外空间
     * @param nums 整数数组
     * @return 除自身以外数组的乘积的数组
     */
    public static int[] productExceptSelf2(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i-1]*nums[i-1];
        }

        int right = 1;
        for (int i = nums.length-1; i >= 0  ; i--) {
            result[i] = right * result[i];
            //R 需要包含右边所有的乘积，所以计算下一个结果时需要将当前值乘到 R 上
            right *= nums[i];
        }

        return result;
    }
}
