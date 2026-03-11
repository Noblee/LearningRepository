package paper;

import java.util.*;

/**
 * 题目10：DAG 任务调度器
 *
 * 给定任务依赖关系（DAG）和最大并行度，输出最优执行计划
 * 1. 拓扑排序检测环
 * 2. BFS 分层 + 并行度约束
 *
 * 时间复杂度：O(V + E)
 *
 * follow-up: 任务有执行时间，节点有不同算力 → 异构调度问题（贪心/优先队列）
 */
public class DAGScheduler {

//    /**
//     * @param numTasks    任务总数 [0, numTasks)
//     * @param deps        依赖关系 deps[i] = [a, b] 表示任务 a 依赖任务 b
//     * @param parallelism 最大并行度
//     * @return 分步执行计划，每一步包含可并行执行的任务列表；若存在环返回空列表
//     */
//    public static List<List<Integer>> schedule(int numTasks, int[][] deps, int parallelism) {
//        // 构建邻接表和入度数组
//        List<List<Integer>> adj = new ArrayList<>();
//        int[] inDegree = new int[numTasks];
//        for (int i = 0; i < numTasks; i++) adj.add(new ArrayList<>());
//
//        for (int[] dep : deps) {
//            int a = dep[0], b = dep[1]; // a depends on b
//            adj.get(b).add(a);
//            inDegree[a]++;
//        }
//
//        // BFS 拓扑排序 + 分层
//        Queue<Integer> queue = new LinkedList<>();
//        for (int i = 0; i < numTasks; i++) {
//            if (inDegree[i] == 0) queue.offer(i);
//        }
//
//        List<List<Integer>> result = new ArrayList<>();
//        int processed = 0;
//
//        while (!queue.isEmpty()) {
//            // 当前层所有入度为 0 的任务
//            List<Integer> currentLevel = new ArrayList<>(queue);
//            queue.clear();
//
//            // 按并行度切分
//            for (int i = 0; i < currentLevel.size(); i += parallelism) {
//                List<Integer> batch = new ArrayList<>();
//                for (int j = i; j < Math.min(i + parallelism, currentLevel.size()); j++) {
//                    batch.add(currentLevel.get(j));
//                }
//                result.add(batch);
//            }
//
//            // 更新入度
//            for (int task : currentLevel) {
//                processed++;
//                for (int next : adj.get(task)) {
//                    inDegree[next]--;
//                    if (inDegree[next] == 0) {
//                        queue.offer(next);
//                    }
//                }
//            }
//        }
//
//        // 检测环：如果处理的任务数 < 总数，说明存在环
//        if (processed < numTasks) return Collections.emptyList();
//
//        return result;
//    }

    public static void main(String[] args) {
//        int numTasks = 5;
//        int[][] deps = {{1, 0}, {2, 0}, {3, 1}, {3, 2}, {4, 3}};
//        int parallelism = 2;
//
//        List<List<Integer>> plan = schedule(numTasks, deps, parallelism);
//        if (plan.isEmpty()) {
//            System.out.println("存在循环依赖，无法调度！");
//        } else {
//            System.out.println("执行计划：");
//            for (int step = 0; step < plan.size(); step++) {
//                System.out.println("Step " + step + ": " + plan.get(step));
//            }
//        }
//        // 预期输出:
//        // Step 0: [0]
//        // Step 1: [1, 2]
//        // Step 2: [3]
//        // Step 3: [4]
    }
}
