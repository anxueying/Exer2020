package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mrs.An Xueying
 * 2020/6/1 11:16
 * 1431. 拥有最多糖果的孩子
 * 特殊情况：数组为null或只有一个元素，直接返回空数组即可
 * 遍历第一次：先取数组中最大的糖数max
 * 遍历第二次：每个元素与extra相加，大于等于max则返回true，否则为false
 * 返回数组
 */
public class Solution1431 {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {


        List<Boolean> result = new ArrayList<>();
        if(candies==null||candies.length==1){
            return result;
        }

        int max_candies = candies[0];
        for(int candie:candies){
            if(candie>max_candies){
                max_candies = candie;
            }
        }

        for(int candie:candies){
            if(candie+extraCandies>=max_candies){
                result.add(true);
            }else{
                result.add(false);
            }
        }

        return result;
    }
}
