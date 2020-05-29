package nowcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class 最长上升字符串 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String a[] = new String[n+2];
        for (int i = 0; i < n; i++) {
            a[i] = sc.next();
        }
        Arrays.sort(a, Comparator.comparingInt(x -> x.charAt(x.length() - 1)));

    }
}
