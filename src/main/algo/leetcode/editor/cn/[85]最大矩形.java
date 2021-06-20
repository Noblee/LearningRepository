//给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"]
//,["1","0","0","1","0"]]
//输出：6
//解释：最大矩形如上图所示。
// 
//
// 示例 2： 
//
// 
//输入：matrix = []
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：matrix = [["0"]]
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：matrix = [["1"]]
//输出：1
// 
//
// 示例 5： 
//
// 
//输入：matrix = [["0","0"]]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// rows == matrix.length 
// cols == matrix[0].length 
// 0 <= row, cols <= 200 
// matrix[i][j] 为 '0' 或 '1' 
// 
// Related Topics 栈 数组 哈希表 动态规划 
// 👍 945 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            int sum = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '1') {
                    sum += 1;
                    dp[i][j] = sum;
                } else {
                    sum = 0;
                    dp[i][j] = 0;
                }
            }
        }

        int ret = Integer.MIN_VALUE;
        for (int j = 0; j < dp[0].length; j++) {
            for (int i = 0; i < dp.length; i++) {
                int cur = dp[i][j];
                int lastNotZero = 0;
                for (int k = i; k >= 0; k--) {
                    if (dp[k][j] == 0) {
                        cur = Integer.MAX_VALUE;
                        lastNotZero = 0;
                        continue;
                    }
                    lastNotZero++;
                    cur = Math.min(cur, dp[k][j]);
                    ret = Math.max(ret, cur * lastNotZero);
                }
            }
        }
        return ret==Integer.MIN_VALUE? 0:ret;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

