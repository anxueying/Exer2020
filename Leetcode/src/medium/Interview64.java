package medium;

/**
 * @author Mrs.An Xueying
 * 2020/6/2 21:01
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 */
public class Interview64 {
    /**
     * 主要有三种：平均计算、迭代、递归。
     * 除了 if 和 switch 等判断语句外，是否有其他方法可用来终止递归？
     *
     * 逻辑运算符的短路效应：
     * 常见的逻辑运算符有三种，即 “与 \&\&&& ”，“或 ||∣∣ ”，“非 !! ” ；而其有重要的短路效应
     * @param n
     * @return
     */
    public int sumNums(int n) {
        boolean flag = n>0&&  (n+=sumNums(n-1))>0;
        return n;
    }
}
