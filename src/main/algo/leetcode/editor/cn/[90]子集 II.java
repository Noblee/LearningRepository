//给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。 
//
// 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。 
//
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,2]
//输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0]
//输出：[[],[0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10 
// -10 <= nums[i] <= 10 
// 
// 
// 
// Related Topics 数组 回溯算法 
// 👍 428 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        func(nums, 0, ans, new ArrayList<>());
        return ans;
    }

    void func(int[] nums, int cur, List<List<Integer>> ans, List<Integer> path) {
        if (cur >= nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        int temp = 1;
        while (cur + temp < nums.length && nums[cur] == nums[cur + temp]) {
            temp++;
        }
        func(nums, cur + temp, ans, path);
        path.add(nums[cur]);
        func(nums, cur + 1, ans, path);
        path.remove(path.size() - 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

