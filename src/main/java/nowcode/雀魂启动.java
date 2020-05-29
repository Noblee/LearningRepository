package nowcode;

import java.util.Scanner;

public class 雀魂启动 {

    static int fff = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] a = new int[10];
        for (int i = 0; i < 13; i++) {
            a[scanner.nextInt()]++;
        }
        int ffff = 0;
        for (int i = 1; i <= 9; i++) {
            if (a[i] != 4) {
                a[i]++;
                fff = 0;
                func(a, 0, i);
                if (fff == 1)
                    ffff = 1;
                a[i]--;
            }
        }
        if (ffff == 0)
            System.out.print(0);
    }

    private static void func(int[] a, int flag, int res) {
        int f = 0;
        for (int i = 1; i < 10; i++) {
            if (a[i] != 0) {
                f = 1;
                break;
            }
        }
        if (f == 0) {
            if (fff == 0) {
                fff = 1;
                System.out.print(res+" ");
            }
            return;
        }

        if (flag == 0) {
            for (int i = 1; i < 10; i++) {
                if (a[i] >= 2) {
                    a[i] -= 2;
                    func(a, 1, res);
                    a[i] += 2;
                }
            }
        }
        if (flag == 0)
            return;
        for (int i = 1; i < 10; i++) {
            if (a[i] >= 3) {
                a[i] -= 3;
                func(a, 1, res);
                a[i] += 3;
            }
            if (i >= 3 && a[i - 2] >= 1 && a[i - 1] >= 1 && a[i] >= 1) {
                a[i] -= 1;
                a[i - 1] -= 1;
                a[i - 2] -= 1;
                func(a, 1, res);
                a[i] += 1;
                a[i - 1] += 1;
                a[i - 2] += 1;
            }
        }
    }
}
