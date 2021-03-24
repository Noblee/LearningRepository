//给你一个整数数组 arr 和一个整数 k ，其中数组长度是偶数，值为 n 。 
//
// 现在需要把数组恰好分成 n / 2 对，以使每对数字的和都能够被 k 整除。 
//
// 如果存在这样的分法，请返回 True ；否则，返回 False 。 
//
// 
//
// 示例 1： 
//
// 输入：arr = [1,2,3,4,5,10,6,7,8,9], k = 5
//输出：true
//解释：划分后的数字对为 (1,9),(2,8),(3,7),(4,6) 以及 (5,10) 。
// 
//
// 示例 2： 
//
// 输入：arr = [1,2,3,4,5,6], k = 7
//输出：true
//解释：划分后的数字对为 (1,6),(2,5) 以及 (3,4) 。
// 
//
// 示例 3： 
//
// 输入：arr = [1,2,3,4,5,6], k = 10
//输出：false
//解释：无法在将数组中的数字分为三对的同时满足每对数字和能够被 10 整除的条件。
// 
//
// 示例 4： 
//
// 输入：arr = [-10,10], k = 2
//输出：true
// 
//
// 示例 5： 
//
// 输入：arr = [-1,1,-2,2,-3,3,-4,4], k = 3
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// arr.length == n 
// 1 <= n <= 10^5 
// n 为偶数 
// -10^9 <= arr[i] <= 10^9 
// 1 <= k <= 10^5 
// 
// Related Topics 贪心算法 数组 数学 
// 👍 37 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canArrange(int[] arr, int k) {
        int[] temp = new int[k];
        for (int i : arr) {
            int j = i % k;
            if (j < 0) {
                j += k;
            }
            temp[j]++;
        }
        if (temp[0] % 2 != 0) {
            return false;
        }
        if (k % 2 == 0 && temp[k / 2] % 2 != 0) {
            return false;
        }
        for (int i = 1; i <= k / 2; i++) {
            if (!(temp[i] == temp[k - i])) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
