package nowcode;

import java.util.Scanner;
import java.util.TreeMap;

public class 字节跳动附加题 {
    static class Pair {
        int a, b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static int e = 1000000007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m[] = new int[n + 2];
        int m1[] = new int[n + 2];
        TreeMap<Pair, Integer> map = new TreeMap<>(
                (a, b) -> (a.a != b.a ? a.a - b.a : a.b - b.b)
        );
        for (int i = 1; i <= n; i++) {
            m[i] = sc.nextInt();
        }
        int step = 0;
        int i = 1;
        while (i != n + 1) {
            if (map.containsKey(new Pair(i, m1[i]))) {
                step = (step + map.get(new Pair(i, m1[i]))) % e;
                continue;
            }
            step = (step + 1) % e;
            int j = i;
            if (m1[i] == 0) {
                m1[i] = 1;
                i = m[i];
            } else {
                m1[i] = 0;
                i++;
            }
            map.put(new Pair(i, m1[i]), step);
        }
        System.out.println(step);
    }
}
