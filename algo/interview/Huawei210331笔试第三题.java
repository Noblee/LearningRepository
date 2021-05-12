package interview;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Huawei210331笔试第三题 {
    static int[][] cache;
    public static void main(String[] args) {
        String a = "aemeeeeoyoooon";
        String b = "amyone";
        int aLength = a.length();
        cache = new int[aLength + 1][b.length() + 1];
        for (int i = 0; i < cache.length; i++) {
            for (int j = 0; j < cache[0].length; j++) {
                cache[i][j] = -1;
            }
        }
        int num = 0;
        Map<Character, HashSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < a.length(); i++) {
            if (!map.containsKey(a.charAt(i))) {
                HashSet<Integer> set = new HashSet<>();
                set.add(i);
                map.put(a.charAt(i), set);
            } else {
                map.get(a.charAt(i)).add(i);
            }
        }
        System.out.println(dfs(map, aLength, b.toCharArray(), num, 0));
    }

    public static int dfs(Map<Character, HashSet<Integer>> map, int aLength, char[] b, int cur, int count) {
        if (b.length == count) {
            return 0;
        }
        if (cache[cur][count] != -1) {
            return cache[cur][count];
        }
        HashSet<Integer> set = map.get(b[count]);
        int min = Integer.MAX_VALUE;
        for (Integer i : set) {
            int temp ;
            if (i > cur) {
                temp = Math.min(i - cur, cur - i + aLength);
            } else {
                temp = Math.min(cur - i, i - cur + aLength);
            }
            min = Math.min(min, dfs(map, aLength, b, i, count + 1) + temp);
        }
        cache[cur][count] = min;
        return min;
    }
}
