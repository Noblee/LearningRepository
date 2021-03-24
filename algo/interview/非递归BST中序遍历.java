package interview;

import java.util.Stack;

public class 非递归BST中序遍历 {

    static class TreeNode {
        int val;
        TreeNode right;
        TreeNode left;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static boolean isBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Integer last = null;
        TreeNode cur = root;
        while (cur != null || !stack.empty()) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (last != null && cur.val < last) {
                return false;
            }
            last = cur.val;
            cur = cur.right;
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(1);
        node1.left = node2;
        node2.left = node4;
        node1.right = node3;
        System.out.println(isBST(node1));
        TreeNode node5 = new TreeNode(2);
        node3.right = node5;
        System.out.println(isBST(node1));
    }

}
