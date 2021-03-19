//You are given an array routes representing bus routes where routes[i] is a bus
// route that the ith bus repeats forever. 
//
// 
// For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in
// the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever. 
// 
//
// You will start at the bus stop source (You are not on any bus initially), and
// you want to go to the bus stop target. You can travel between bus stops by buse
//s only. 
//
// Return the least number of buses you must take to travel from source to targe
//t. Return -1 if it is not possible. 
//
// 
// Example 1: 
//
// 
//Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
//Output: 2
//Explanation: The best strategy is take the first bus to the bus stop 7, then t
//ake the second bus to the bus stop 6.
// 
//
// Example 2: 
//
// 
//Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target =
// 12
//Output: -1
// 
//
// 
// Constraints: 
//
// 
// 1 <= routes.length <= 500. 
// 1 <= routes[i].length <= 105 
// All the values of routes[i] are unique. 
// sum(routes[i].length) <= 105 
// 0 <= routes[i][j] < 106 
// 0 <= source, target < 106 
// 
// Related Topics 广度优先搜索 
// 👍 113 👎 0


import java.util.*;
import java.util.Collections;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        Set[] sets = new HashSet[routes.length];
        Set<Integer> sources = new HashSet<>();
        Set<Integer> targets = new HashSet<>();
        for (int i = 0; i < routes.length; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j : routes[i]) {
                if (j == source) {
                    sources.add(i);
                }
                if (j == target) {
                    targets.add(i);
                }
                set.add(j);
            }
            sets[i] = set;
        }
        boolean mmmm[][] = new boolean[routes.length][routes.length];

        for (int i = 0; i < routes.length; i++) {
            for (int j = i; j < routes.length; j++) {
                mmmm[i][j] = !Collections.disjoint(sets[i], sets[j]);
            }
        }
        int minmin = 10000000;
        for (Integer integer : sources) {
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{integer, 1});
            boolean[] color = new boolean[routes.length];
            while (!queue.isEmpty()) {
                int[] i = queue.poll();
                if (targets.contains(i[0])) {
                    minmin = Math.min(minmin, i[1]);
                    break;
                } else {
                    if (!color[i[0]]) {
                        for (int i1 = 0; i1 < mmmm[i[0]].length; i1++) {
                            if (i[0] <= i1 && mmmm[i[0]][i1]) {
                                queue.add(new int[]{i1, i[1] + 1});
                            } else if (i[0] > i1 && mmmm[i1][i[0]]) {
                                queue.add(new int[]{i1, i[1] + 1});
                            }
                        }
                        color[i[0]] = true;
                    }
                }
            }
        }

        return minmin == 10000000 ? -1 : minmin;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
