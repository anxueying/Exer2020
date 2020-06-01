package hard;


/**
 * @author Mrs.An Xueying
 * 2020/5/30 8:46
 * 84. 柱状图中最大的矩形
 * 解题思路
 * 矩形面积=长*高
 * 遍历数组，高为每一次遍历时，该位置数组的数字，长度需向左向右遍历，取面积
 * 向左（向右）遍历终止条件：该数字小于高，长度按遍历次数自增
 * 取最大面积
 *
 * 作者：anxueying
 * 链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/bao-li-jie-fa-by-anxueying/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution84 {
    public static int largestRectangleArea(int[] heights) {
        int area = 0;
        for (int i = 0; i < heights.length; i++) {
            int l = 1;
            //向左遍历
            for (int j = i - 1; j >= 0; j--) {
                if (heights[j]<heights[i]){
                    break;
                }
                l++;
            }
            //向右遍历
            for (int j = i+1; j < heights.length; j++) {
                if (heights[j]<heights[i]){
                    break;
                }
                l++;
            }
            area = Math.max(area,heights[i]*l);
        }
        return area;
    }


    public static void main(String[] args) {
        System.out.println(largestRectangleArea(new int[]{}));
    }
}
