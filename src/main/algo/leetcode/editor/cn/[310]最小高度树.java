//树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。 
//
// 给你一棵包含 n 个节点的树，标记为 0 到 n - 1 。给定数字 n 和一个有 n - 1 条无向边的 edges 列表（每一个边都是一对标签），其中
// edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条无向边。 
//
// 可选择树中任何一个节点作为根。当选择节点 x 作为根节点时，设结果树的高度为 h 。在所有可能的树中，具有最小高度的树（即，min(h)）被称为 最小高度
//树 。 
//
// 请你找到所有的 最小高度树 并按 任意顺序 返回它们的根节点标签列表。 
//树的 高度 是指根节点和叶子节点之间最长向下路径上边的数量。
//
// 
//
// 示例 1： 
//
// 
//输入：n = 4, edges = [[1,0],[1,2],[1,3]]
//输出：[1]
//解释：如图所示，当根是标签为 1 的节点时，树的高度是 1 ，这是唯一的最小高度树。 
//
// 示例 2： 
//
// 
//输入：n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
//输出：[3,4]
// 
//
// 示例 3： 
//
// 
//输入：n = 1, edges = []
//输出：[0]
// 
//
// 示例 4： 
//
// 
//输入：n = 2, edges = [[0,1]]
//输出：[0,1]
// 
//
// 
//
// 
// 
//
// 提示： 
//
// 
// 1 <= n <= 2 * 104 
// edges.length == n - 1 
// 0 <= ai, bi < n 
// ai != bi 
// 所有 (ai, bi) 互不相同 
// 给定的输入 保证 是一棵树，并且 不会有重复的边 
// 
// Related Topics 广度优先搜索 图 
// 👍 325 👎 0

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    boolean[] flag;
    Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        flag = new boolean[n];
        if (edges.length == 0) {
            return Arrays.asList(0);
        }
        List<List<Integer>> temp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            temp.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            temp.get(edge[0]).add(edge[1]);
            temp.get(edge[1]).add(edge[0]);
        }
        int max = Integer.MIN_VALUE;
        int max_index = -1;
        for (int i = 0; i < temp.size(); i++) {
            List<Integer> cur = temp.get(i);
            if (cur.size() == 1 && flag[i] == false) {
                int num = func(temp, i, -1);
                if (num >= max) {
                    max = num;
                    max_index = i;
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        find(temp, max_index, -1, max - 1, max, ans);
        return ans;
    }

    int func(List<List<Integer>> temp, int i, int last) {
        List<Integer> cur = temp.get(i);
        if (cur.size() == 1 && cur.get(0) == last) {
            flag[cur.get(0)] = true;
            return 1;
        } else {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < cur.size(); j++) {
                if (cur.get(j) != last) {
                    int a = -1;
                    if (map.containsKey(cur.get(j)) && map.get(cur.get(j)).containsKey(i)) {
                        a = map.get(cur.get(j)).get(i);
                    } else {
                        a = func(temp, cur.get(j), i);
                        if (map.containsKey(cur.get(j))) {
                            map.get(cur.get(j)).put(i, a);
                        } else {
                            Map<Integer, Integer> x = new HashMap<>();
                            x.put(i, a);
                            map.put(cur.get(j), x);
                        }
                    }
                    max = Math.max(max, a + 1);
                }
            }
            return max;
        }
    }

    boolean find(List<List<Integer>> temp, int i, int last, int count, int k, List<Integer> ans) {
        List<Integer> cur = temp.get(i);
        if (cur.size() == 1 && cur.get(0) == last) {
            if (count == 0) {
                if (count == k / 2 || (k % 2 == 0 && ((count == k / 2) || (count == k / 2 - 1)))) {
                    ans.add(i);
                }
                return true;
            }
            return false;
        } else {
            for (int j = 0; j < cur.size(); j++) {
                if (cur.get(j) != last) {
                    if (count == k / 2 || (k % 2 == 0 && ((count == k / 2) || (count == k / 2 - 1)))) {
                        ans.add(i);
                    }
                    if (find(temp, cur.get(j), i, count - 1, k, ans)) {
                        return true;
                    }
                    if (count == k / 2 || (k % 2 == 0 && ((count == k / 2) || (count == k / 2 - 1)))) {
                        ans.remove(ans.size() - 1);
                    }
                }
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

