package easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Mrs.An Xueying
 * 2020/5/30 14:50
 * 653. 两数之和 IV - 输入 BST
 * 给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 *
 * 思路：
 *  val   num = k-val  如set中含有num，返回true，否则继续遍历
 *  //1.向右加，如小于k，则继续向右
 */
public class Solution653 {
    public boolean findTarget(TreeNode root, int k) {
        HashSet<Integer> hashSet = new HashSet<>();
        return find(root, k, hashSet);
    }

    public boolean find(TreeNode root, int k, HashSet<Integer> hashSet){
        if (root==null){
            return false;
        }
        if (hashSet.contains(k-root.val)){
            return true;
        }
        hashSet.add(root.val);
        return find(root.left,k, hashSet) || find(root.right,k,hashSet);
    }

    /**
     * Definition for a binary tree node.
     * 特点：对于每个节点，left < val ,right>val
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 方法2 BFS+HashSet
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget1(TreeNode root, int k) {
        HashSet<Integer> hashSet = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if (node!=null){
                boolean b = hashSet.contains(k - node.val);
                if (b){
                    return true;
                }
                hashSet.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        return false;
    }


    /**
     * 方法3 BFS 待理解二叉树
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget2(TreeNode root, int k) {
        return false;
    }
}
