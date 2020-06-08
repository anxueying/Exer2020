package easy;

/**
 * @author Mrs.An Xueying
 * 2020/6/8 18:20
 */
public class Solution897 {
    private static int num = 0;

    public static void recursionMiddleorderTraversal(TreeNode root) {
        if (root != null) {
            recursionMiddleorderTraversal(root.left);
            System.out.print(root.val + " ");
            recursionMiddleorderTraversal(root.right);
        }
    }

    public static TreeNode convertBiNode(TreeNode root) {
        // 单链表的头指针哨兵
        TreeNode head = new TreeNode(0);
        // 开始中序遍历
        inorder(root,head);
        return head.right;
    }

    private static TreeNode inorder(TreeNode root, TreeNode prev){
        if (root != null){
            prev = inorder(root.left,prev);
            root.left = null;
            prev.right = root;
            prev = root;
            prev = inorder(root.right,prev);
        }
        return prev;
    }

    public static void main(String[] args) {
        TreeNode p = new TreeNode(4);
        p.left = new TreeNode(2);
        p.right = new TreeNode(5);
        p.left.left = new TreeNode(1);
        p.left.right = new TreeNode(3);
        p.left.left.left = new TreeNode(0);
        p.right.right = new TreeNode(6);

        TreeNode treeNode = convertBiNode(p);
    }

}

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }