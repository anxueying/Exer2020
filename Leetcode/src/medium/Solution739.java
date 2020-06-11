package medium;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Mrs.An Xueying
 * 2020/6/11 11:33
 * 739. 每日温度
 */
public class Solution739 {
    /**
     * 暴力破解
     * @param T 气温列表
     * @return 对应位置的输出是需要再等待多久温度才会升高超过该日的天数
     */
    public int[] dailyTemperatures(int[] T) {
        int[] ret= new int[T.length];
        ret[T.length-1] = 0;
        for(int i=0;i<T.length-1;i++){
            ret[i] = higherTemperatures(T,i);
        }
        return ret;
    }

    public int higherTemperatures(int[] T, int left){
        if(left>T.length-1){
            return 0;
        }
        for(int i=left;i<T.length;i++){
            if(T[i]>T[left]){
                return i-left;
            }
        }
        return 0;
    }

    /**
     * 利用栈
     * @param T 气温列表
     * @return  对应位置的输出是需要再等待多久温度才会升高超过该日的天数
     */
    public int[] dailyTemperatures1(int[] T) {
        Deque<Integer> stack = new LinkedList<Integer>();
        int len = T.length;
        int[] ret = new int[len];
        for (int i = 0; i < T.length; i++) {
            int temp = T[i];
            while (!stack.isEmpty()&&temp>T[stack.peek()]){
                Integer prevIndex = stack.pop();
                ret[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ret;
    }
}
