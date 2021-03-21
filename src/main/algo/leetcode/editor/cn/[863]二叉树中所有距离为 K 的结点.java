//给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。 
//
// 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
//输出：[7,4,1]
//解释：
//所求结点为与目标结点（值为 5）距离为 2 的结点，
//值分别为 7，4，以及 1
//
//
//
//注意，输入的 "root" 和 "target" 实际上是树上的结点。
//上面的输入仅仅是对这些对象进行了序列化描述。
// 
//
// 
//
// 提示： 
//
// 
// 给定的树是非空的。 
// 树上的每个结点都具有唯一的值 0 <= node.val <= 500 。 
// 目标结点 target 是树上的结点。 
// 0 <= K <= 1000. 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 
// 👍 254 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Arrays;
import java.util.Collections;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    int distance = 0;
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> ret = new ArrayList<Integer>();
        if (K == 0) {
            return Arrays.asList(target.val);
        }
        dfs(root, ret, target, 0, K);
        return ret;
    }

    public boolean dfs(TreeNode root, List<Integer> ret, TreeNode target, int depth, int K) {
        if (root == null) {
            return false;
        }
        if (root == target) {
            func(root, K - distance, ret, null);
            distance++;
            return true;
        }
        boolean lfind = dfs(root.left, ret, target, depth + 1, K);
        boolean rfind = false;
        if (lfind) {
            func(root, K - distance, ret, true);
            distance++;
        } else {
            rfind = dfs(root.right, ret, target, depth + 1, K);
            if (rfind) {
                func(root, K - distance, ret, false);
                distance++;
            }
        }
        return lfind || rfind;
    }
    public void func(TreeNode root, int depth, List<Integer> ret, Boolean isright) {
        if (depth < 0 || root == null) {
            return;
        }
        if (depth == 0) {
            ret.add(root.val);
            return;
        }
        if (isright == null) {
            func(root.left, depth - 1, ret, null);
            func(root.right, depth - 1, ret, null);
        } else if (isright) {
            func(root.right, depth - 1, ret, null);
        } else {
            func(root.left, depth - 1, ret, null);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
