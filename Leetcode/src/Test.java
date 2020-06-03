
import java.util.*;

/**
 * @author Mrs.An Xueying
 * 2020/6/1 23:48
 */
public class Test {
    public static void main(String[] args) {
        int[] nums = new int[]{2,3,5,7,29,1,30};
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int num : nums) {
            arrayList.add(num);
        }


        arrayList.removeIf(integer -> integer.equals(1));


        for (Integer integer : arrayList) {
            System.out.print(integer+"\t");
        }

    }
}
