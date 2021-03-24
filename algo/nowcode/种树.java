package nowcode;

import java.util.ArrayList;
import java.util.Scanner;

public class 种树 {
    static ArrayList<Integer> res;
    static int found = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        int c = 0;
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            c += a[i];
        }
        res = new ArrayList<>();
        func(-1, a, c);
    }

    public static void func(int last, int[] a, int r) {
        if (r == 0) {
            for (int i = 0; i < res.size(); i++) {
                System.out.print(res.get(i));
                if (i != res.size() - 1) System.out.print(" ");
            }
            found = 1;
            return;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] > 0 && i != last) {
                a[i]--;
                res.add(i + 1);
                func(i, a, r - 1);
                if (found == 1) return;
                a[i]++;
                res.remove(res.size() - 1);
            }
        }
    }
}
