package nowcode;

import java.util.Scanner;
import java.util.TreeMap;

public class 特征提取 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            int m = sc.nextInt();
            TreeMap<Integer[], Integer> last = new TreeMap<>((a, b) -> !a[0].equals(b[0]) ? a[0] - b[0] : a[1] - b[1]);
            int max = Integer.MIN_VALUE;
            for (int k = 0; k < m; k++) {
                int v = sc.nextInt();
                TreeMap<Integer[], Integer> map
                        = new TreeMap<>((a, b) -> !a[0].equals(b[0]) ? a[0] - b[0] : a[1] - b[1]);
                Integer[][] temp = new Integer[v][2];
                for (int j = 0; j < v; j++) {
                    temp[j][0] = sc.nextInt();
                    temp[j][1] = sc.nextInt();
                    int tt = last.getOrDefault(temp[j], 0) + 1;
                    max = Math.max(max,tt);
                    map.put(temp[j],tt);
                }
                last = map;
            }

            System.out.println(max);
        }
    }
}
