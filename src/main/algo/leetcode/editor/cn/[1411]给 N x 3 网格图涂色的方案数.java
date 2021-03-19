//You have a grid of size n x 3 and you want to paint each cell of the grid with
// exactly one of the three colors: Red, Yellow, or Green while making sure that n
//o two adjacent cells have the same color (i.e., no two cells that share vertical
// or horizontal sides have the same color). 
//
// Given n the number of rows of the grid, return the number of ways you can pai
//nt this grid. As the answer may grow large, the answer must be computed modulo 1
//09 + 7. 
//
// 
// Example 1: 
//
// 
//Input: n = 1
//Output: 12
//Explanation: There are 12 possible way to paint the grid as shown.
// 
//
// Example 2: 
//
// 
//Input: n = 2
//Output: 54
// 
//
// Example 3: 
//
// 
//Input: n = 3
//Output: 246
// 
//
// Example 4: 
//
// 
//Input: n = 7
//Output: 106494
// 
//
// Example 5: 
//
// 
//Input: n = 5000
//Output: 30228214
// 
//
// 
// Constraints: 
//
// 
// n == grid.length 
// grid[i].length == 3 
// 1 <= n <= 5000 
// 
// Related Topics 动态规划 
// 👍 75 👎 0


import java.lang.reflect.Array;
import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numOfWays(int n) {
        long n4 = 6, n5 = 6;
        for (int i1 = 1; i1 < n; i1++) {
            long tn4 = n4, tn5 = n5;
            n4 = ((tn4 * 2) % 1000000007 + (tn5 * 2) % 1000000007) % 1000000007;
            n5 = ((tn4 * 2) % 1000000007 + (tn5 * 3) % 1000000007) % 1000000007;
        }
        return (int) (n4 + n5) % 1000000007;
    }

    int i = 0;

    private void dfs(int[][] ans, int depth, int[] cur) {
        for (int i1 = 0; i1 <= 2; i1++) {
            if (depth <= 0 || cur[depth - 1] != i1) {
                cur[depth] = i1;
                if (depth == 2) {
                    int[] temp = Arrays.copyOf(cur, 3);
                    ans[i++] = temp;
                } else {
                    dfs(ans, depth + 1, cur);
                }
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
