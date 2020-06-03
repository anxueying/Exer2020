package capture2;

/**
 * @author Mrs.An Xueying
 * 2020/6/2 22:34
 * 选择排序
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] ints = new int[]{5, 3, 6, 2, 10};
        selectionSort(ints);
        for (int i : ints) {
            System.out.println(i);
        }
    }

    public static void selectionSort(int[] nums){
        int length = nums.length;
        int temp = 0;
        int minIndex;

        for (int i = 0; i < length-1; i++) {
            minIndex = i;
            for (int j = i; j < length-1; j++) {
                if (nums[minIndex] > nums[j+1]) {
                    minIndex = j+1;
                }
            }
            temp = nums[minIndex];
            nums[minIndex] = nums[i];
            nums[i] = temp;
        }

    }

}
