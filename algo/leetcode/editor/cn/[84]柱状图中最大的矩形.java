//给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。 
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。 
//
// 
//
// 
//
// 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。 
//
// 
//
// 
//
// 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。 
//
// 
//
// 示例: 
//
// 输入: [2,1,5,6,2,3]
//输出: 10 
// Related Topics 栈 数组 
// 👍 1245 👎 0

import java.util.*;
import java.util.stream.Collectors;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        Deque<int[]> stack = new ArrayDeque<>();

        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && stack.peekLast()[1] > heights[i]) {
                int[] temp = stack.pollLast();
                int[] last = stack.peekLast();
                if (last != null) {
                    max = Math.max((i - last[0] - 1) * temp[1], max);
                } else {
                    max = Math.max(i * temp[1], max);
                }
            }
            stack.addLast(new int[]{i, heights[i]});
        }

        while (!stack.isEmpty()) {
            int[] cur = stack.pollLast();
            int[] last = stack.peekLast();
            if (last != null) {
                max = Math.max((heights.length - 1 - last[0]) * cur[1], max);
            } else {
                max = Math.max(heights.length * cur[1], max);
            }
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
