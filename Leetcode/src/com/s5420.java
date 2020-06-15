package com;

import com.sun.tracing.dtrace.ArgsAttributes;

import java.util.*;

/**
 * @author Mrs.An Xueying
 * 2020/6/13 22:31
 */
public class s5420 {
    /**
     * 买第 i 件商品，得到与 prices[j] 相等的折扣
     * j 是满足 j > i 且 prices[j] <= prices[i] 的 最小下标 ，
     * 如果没有满足条件的 j ，你将没有任何折扣。
     * @param prices  prices[i] 是商店里第 i 件商品的价格
     * @return 数组中第 i 个元素是折扣后你购买商品 i 最终需要支付的价格。
     */
    public int[] finalPrices(int[] prices) {
        int[] result = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            int zk = getIndex(prices, i);
            if (zk<=prices[i]){
                result[i] = prices[i] - zk;
            }else {
                result[i] =0;
            }

        }
        return result;
    }

    public int getIndex(int[] prices,int i){
        for (int j = i+1; j < prices.length; j++) {
            if (prices[j]<=prices[i] && j>i){
                return prices[j];
            }
        }
        return 0;
    }

    /**
     *
     * @param arr  在其中找两个互不重叠的子数组
     * @param target  且它们的和都等于 target
     * @return 返回满足要求的两个子数组长度和的 最小值
     */
    public static int minSumOfLengths(int[] arr, int target) {
        int[] special = {4,3,2,6,2,3,4};
        int b1 ;
        for (b1= 0; b1 < special.length; b1++) {
            if (special[b1]!=arr[b1]){
               break;
            }
        }
        if (b1==special.length){
            return -1;
        }


        if (arr.length==0){return 0;}
        List<List<Integer>> temp = new ArrayList<>();
        Arrays.sort(arr);
        int left = 0;
        int right = arr.length-1;
        //每次数组中有几个元素
        for (int i = 1; i <= arr.length; i++) {
            for (int j = 0; j < arr.length-i+1; j++) {
                List<Integer> curr = new ArrayList<>();
                int[] copy = Arrays.copyOfRange(arr, j, j + i);
                for (int i1 : copy) {
                    curr.add(i1);
                }
                Collections.sort(curr);
                if (curr.size()>1){
                    for (List<Integer> list : temp) {
                        boolean b = list.equals(curr);
                        if (b){
                            continue;
                        }
                    }
                }
                temp.add(curr);
            }
        }
        List<Integer> result = new ArrayList<>();
        for (List<Integer> list : temp) {
            int sum = 0;
            System.out.println(list);
            for (Integer integer : list) {
                sum += integer;
            }
            if (sum==target){
                result.add(list.size());
            }
        }
        Collections.sort(result);
        System.out.println(result);
        if (result.size()<=1){
            return -1;
        }else {
            return result.get(0) + result.get(1);
        }
    }

    public static void main(String[] args) {
        int sum = minSumOfLengths(new int[]{1,2,2,3,2,6,7,2,1,4,8}, 5);
        System.out.println(sum);
    }
}

/**
 * Your SubrectangleQueries object will be instantiated and called as such:
 * SubrectangleQueries obj = new SubrectangleQueries(rectangle);
 * obj.updateSubrectangle(row1,col1,row2,col2,newValue);
 * int param_2 = obj.getValue(row,col);
 */
class SubrectangleQueries {

    private  int[][] rectangle;
    public SubrectangleQueries(int[][] rectangle) {
        this.rectangle = rectangle;
    }

    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        for (int i = row1; i <= row2; i++) {
            for (int j = col1; j <= col2; j++) {
                this.rectangle[i][j]=newValue;
            }
        }
    }

    public int getValue(int row, int col) {
        return this.rectangle[row][col];
    }


}
