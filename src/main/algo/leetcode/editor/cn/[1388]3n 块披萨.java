//There is a pizza with 3n slices of varying size, you and your friends will tak
//e slices of pizza as follows: 
//
// 
// You will pick any pizza slice. 
// Your friend Alice will pick next slice in anti clockwise direction of your pi
//ck. 
// Your friend Bob will pick next slice in clockwise direction of your pick. 
// Repeat until there are no more slices of pizzas. 
// 
//
// Sizes of Pizza slices is represented by circular array slices in clockwise di
//rection. 
//
// Return the maximum possible sum of slice sizes which you can have. 
//
// 
// Example 1: 
//
// 
//
// 
//Input: slices = [1,2,3,4,5,6]
//Output: 10
//Explanation: Pick pizza slice of size 4, Alice and Bob will pick slices with s
//ize 3 and 5 respectively. Then Pick slices with size 6, finally Alice and Bob wi
//ll pick slice of size 2 and 1 respectively. Total = 4 + 6.
// 
//
// Example 2: 
//
// 
//
// 
//Input: slices = [8,9,8,6,1,1]
//Output: 16
//Output: Pick pizza slice of size 8 in each turn. If you pick slice with size 9
// your partners will pick slices of size 8.
// 
//
// Example 3: 
//
// 
//Input: slices = [4,1,2,5,8,3,1,9,7]
//Output: 21
// 
//
// Example 4: 
//
// 
//Input: slices = [3,1,2]
//Output: 3
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 500 
// n % 3 == 0 
// 1 <= slices[i] <= 1000 
// 
// Related Topics 动态规划 
// 👍 71 👎 0


import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxSizeSlices(int[] slices) {
//        List<Integer> data = Arrays.asList(slices);

        int n = slices.length;
        int[][] dp = new int[n][n];
        int res = Integer.MIN_VALUE;
        for (int i = 1; i <= n / 3; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 1) {
//                    int max = Integer.MIN_VALUE;
//                    for (int k = j; k < j + 3; k++) {
//                        max = Math.max(max, slices[k % n]);
//                    }
                    dp[i][j] = slices[(j+1)%n];
                } else {
                    int maxmax = dp[i - 1][j] + dp[1][(j + (i - 1) * 3) % n];
                    int end = (j + i * 3) % n;
                    boolean flag = false;
                    if ((j + i * 3) / n != 0) {
                        flag = true;
                    }
                    for (int i1 = j + 1; i1 < j + i * 3; i1++) {
                        int r_i1 = i1 % n;
                        int max = Integer.MIN_VALUE;
                        for (int k = i1 + 3 * (i - 1); k < i1 + 3 * i; k++) {
                            int r_k = k % n;
                            if (flag && end != 0 && r_k >= end) {
                                r_k = (r_k % end + j) % n;
                            }
                            max = Math.max(max, slices[r_k]);
                        }
                        maxmax = Math.max(maxmax, max + dp[i - 1][r_i1]);
                    }
                    dp[i][j] = maxmax;
                    if (i == n / 3) {
                        res = Math.max(res, maxmax);
                    }
                }
//                for (int ii = 1; ii <= n / 3; ii++) {
//                    for (int jj = 0; jj < n; jj++) {
//                        System.out.print(dp[ii][jj] + " ");
//                    }
//                    System.out.println("");
//                }
//                System.out.println("*******");

            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
