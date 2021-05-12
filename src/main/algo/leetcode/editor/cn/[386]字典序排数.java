//给定一个整数 n, 返回从 1 到 n 的字典顺序。 
//
// 例如， 
//
// 给定 n =1 3，返回 [1,10,11,12,13,2,3,4,5,6,7,8,9] 。 
//
// 请尽可能的优化算法的时间复杂度和空间复杂度。 输入的数据 n 小于等于 5,000,000。 
// 👍 162 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>(n);
        if (n <= 0) {
            return ans;
        }
        for (int i = 1; i <= 9; i++) {
            func(n, i, ans);
        }
        return ans;
    }

    public void func(int n, int cur, List<Integer> ans) {
        if (cur <= n) {
            ans.add(cur);
        } else {
            return;
        }
        for (int i = 0; i <= 9; i++) {
            func(n, cur * 10 + i, ans);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

