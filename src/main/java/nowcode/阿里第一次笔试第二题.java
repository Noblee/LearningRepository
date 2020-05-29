package nowcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class 阿里第一次笔试第二题 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i] = sc.next();
        }
        Arrays.sort(strs, Comparator.comparingInt(a -> a.charAt(a.length() - 1)));
        int[] dp = new int[28];
        int mmmax = Integer.MIN_VALUE;
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            int max = str.length();
            for (int j = 0; j <= str.charAt(0) - 'a'; j++) {
                max = Math.max(max, dp[j] + str.length());
            }
            mmmax = Math.max(mmmax, max);
            dp[str.charAt(str.length() - 1)-'a'] = max;
        }
        System.out.println(mmmax);
    }
}
