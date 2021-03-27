//几块石子 排成一行 ，每块石子都有一个关联值，关联值为整数，由数组 stoneValue 给出。 
//
// 游戏中的每一轮：Alice 会将这行石子分成两个 非空行（即，左侧行和右侧行）；Bob 负责计算每一行的值，即此行中所有石子的值的总和。Bob 会丢弃值最
//大的行，Alice 的得分为剩下那行的值（每轮累加）。如果两行的值相等，Bob 让 Alice 决定丢弃哪一行。下一轮从剩下的那一行开始。 
//
// 只 剩下一块石子 时，游戏结束。Alice 的分数最初为 0 。 
//
// 返回 Alice 能够获得的最大分数 。 
//
// 
//
// 示例 1： 
//
// 输入：stoneValue = [6,2,3,4,5,5]
//输出：18
//解释：在第一轮中，Alice 将行划分为 [6，2，3]，[4，5，5] 。左行的值是 11 ，右行的值是 14 。Bob 丢弃了右行，Alice 的分数现
//在是 11 。
//在第二轮中，Alice 将行分成 [6]，[2，3] 。这一次 Bob 扔掉了左行，Alice 的分数变成了 16（11 + 5）。
//最后一轮 Alice 只能将行分成 [2]，[3] 。Bob 扔掉右行，Alice 的分数现在是 18（16 + 2）。游戏结束，因为这行只剩下一块石头了。
//
// 
//
// 示例 2： 
//
// 输入：stoneValue = [7,7,7,7,7,7,7]
//输出：28
// 
//
// 示例 3： 
//
// 输入：stoneValue = [4]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= stoneValue.length <= 500 
// 1 <= stoneValue[i] <= 10^6 
// 
// Related Topics 动态规划 
// 👍 30 👎 0

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    int[] prefixSum;

    public int stoneGameV(int[] stoneValue) {
        prefixSum = new int[stoneValue.length];
        int sum = 0;
        for (int i = 0; i < stoneValue.length; i++) {
            sum += stoneValue[i];
            prefixSum[0] = sum;
        }
        return dfs(stoneValue, 0, stoneValue.length);

    }





    public int dfs(int[] stoneValue, int from, int to) {
        if(to == from){
            return stoneValue[to];
        }
        if (to - from == 1) {
            return Math.min(stoneValue[to - 1], stoneValue[from]);
        }
        int max = Integer.MAX_VALUE;
        for (int i = from; i < to; i++) {
            if ((prefixSum[i] - from - 1 >= 0 ? prefixSum[from - 1] : 0) > prefixSum[to - 1] - prefixSum[i]) {
                Math.max(prefixSum[to - 1] - prefixSum[i] + dfs(stoneValue, i + 1, to), max);
            } else if ((prefixSum[i] - from - 1 >= 0 ? prefixSum[from - 1] : 0) < prefixSum[to - 1] - prefixSum[i]) {
                Math.max((prefixSum[i] - from - 1 >= 0 ? prefixSum[from - 1] : 0) + dfs(stoneValue, from, i), max);

            } else {
                Math.max(prefixSum[to - 1] - prefixSum[i] + dfs(stoneValue, i + 1, to), max);
                Math.max((prefixSum[i] - from - 1 >= 0 ? prefixSum[from - 1] : 0) + dfs(stoneValue, from, i), max);
            }
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

