//给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看
//做它自身的一棵子树。 
//
// 示例 1: 
//给定的树 s: 
//
// 
//     3
//    / \
//   4   5
//  / \
// 1   2
// 
//
// 给定的树 t： 
//
// 
//   4 
//  / \
// 1   2
// 
//
// 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。 
//
// 示例 2: 
//给定的树 s： 
//
// 
//     3
//    / \
//   4   5
//  / \
// 1   2
//    /
//   0
// 
//
// 给定的树 t： 
//
// 
//   4
//  / \
// 1   2
// 
//
// 返回 false。 
// Related Topics 树 
// 👍 470 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        StringBuilder sb1 = new StringBuilder();
        func(s, sb1);
        StringBuilder sb2 = new StringBuilder();
        func(t, sb2);
        Pattern pattern = Pattern.compile(sb2.toString());
        Matcher aaa = pattern.matcher(sb1.toString());
        return aaa.find();
    }

    void func(TreeNode s, StringBuilder sb1r) {
        if (s == null) {
            sb1r.append("Anull");
            return;
        }
        sb1r.append("A" + s.val);
        func(s.left, sb1r);
        func(s.right, sb1r);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

