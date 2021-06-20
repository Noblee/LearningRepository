package interview;


import javax.annotation.Priority;
import java.util.*;

public class Vivo笔试第二题 {
    public static void main(String[] args) {
        int[] costs = new int[]{3, 1, 2, 5, 3, 1};
        Set<Integer>[] sets = new Set[costs.length + 1];
        sets[1] = new HashSet<>(Arrays.asList(2, 3, 4));
        sets[2] = new HashSet<>(Arrays.asList(5));
        sets[3] = new HashSet<>(Arrays.asList(5, 6));
        sets[4] = new HashSet<>();
        sets[5] = new HashSet<>(Arrays.asList(6));
        sets[6] = new HashSet<>();
        System.out.println(func(costs, sets));
    }

    public static int func(int[] cost, Set<Integer>[] edges) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int cur = 0;
        queue.add(new int[]{0, 0});
        while (queue.size() > 0) {
            int[] poll = queue.poll();
            cur = poll[0];
            for (int i = 1; i < edges.length; i++) {
                if(edges[i] != null) {
                    edges[i].remove(poll[1]);
                    if (edges[i].size() == 0) {
                        edges[i] = null;
                        queue.add(new int[]{cur + cost[i - 1], i});
                    }
                }
            }
        }
        return cur;
    }
}
