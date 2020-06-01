package easy;
import easy.Solution653.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Mrs.An Xueying
 * 2020/5/31 0:44
 * 101. 对称二叉树
 */
public class Solution101 {
    /**
     * 递归
     * 首先是要对称，那就把左分支右分支单独拿出来，作为两棵树
     * 单独写check方法，用来检验两棵树是否对称
     * 考虑特殊情况 ①左右分支都为空 对称 ② 一个为空一个不为空 不对称
     * 一般情况：根节点必须相同（否则都不是一颗树，更别提对称了）
     * 递归：根节点相同，继续比较左右分支。 对称的条件：左分支的左=右分支的右，左分支的右=右分支的左
     * 将递归调用的方法结果返回即可
     * @param root 二叉树
     * @return 是否对称
     */
    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    public boolean check(TreeNode root1,TreeNode root2){
        if (root1==null&&root2==null){
            return true;
        }

        if (root1==null||root2==null){
            return false;
        }

        if (root1.val==root2.val){
            return check(root1.left, root2.right) && check(root1.right, root2.left);
        }

        return false;
    }

    /**
     * 迭代
     * 利用Queue先进先出的特性
     * 添加元素 offer boolean
     * 返回元素并删除 poll 空null
     *
     * @param root 二叉树
     * @return 是否对称
     */
    public boolean isSymmetric1(TreeNode root) {

        if (root==null){
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root.left);
        queue.offer(root.right);
        while (queue.size()!=0){
            TreeNode l = queue.poll();
            TreeNode r = queue.poll();
            //有可能只是到达了某个叶子节点，接下来还要继续遍历其他分支，所以要continue，不能return true
            if (l==null&&r==null){
                continue;
            }

            if (l==null||r==null){
                return false;
            }

            if (l.val != r.val){
                return false;
            }

            queue.offer(l.left);
            queue.offer(r.right);
            queue.offer(l.right);
            queue.offer(r.left);
        }

        return true;
    }
}
