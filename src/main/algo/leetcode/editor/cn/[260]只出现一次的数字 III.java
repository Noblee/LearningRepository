//给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。 
//
// 
//
// 进阶：你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？ 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,1,3,2,5]
//输出：[3,5]
//解释：[5, 3] 也是有效的答案。
// 
//
// 示例 2： 
//
// 
//输入：nums = [-1,0]
//输出：[-1,0]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0,1]
//输出：[1,0]
// 
//
// 提示： 
//
// 
// 2 <= nums.length <= 3 * 104 
// -231 <= nums[i] <= 231 - 1 
// 除两个只出现一次的整数外，nums 中的其他数字都出现两次 
// 
// Related Topics 位运算 
// 👍 404 👎 0

import java.util.List;
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] singleNumber(int[] nums) {
        int temp = 0;
        for (int num : nums) {
            temp ^= num;
        }
        int i = 0;
        for (i = 0; i < 32; i++) {
            if ((temp %2)!=0) {
                break;
            } else {
                temp = temp >> 1;
            }
        }

        final String string = new String();
        int temp1 = 0;
        int temp2 = 0;
        int mask = 1 << i;
        for (int num : nums) {
            if ((num & mask) == 0) {
                temp1 ^= num;
            } else {
                temp2 ^= num;
            }
        }
        return new int[]{temp1, temp2};
    }

}
//leetcode submit region end(Prohibit modification and deletion)

