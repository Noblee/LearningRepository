package nowcode;

import java.util.Scanner;

public class 快速幂 {

    static long mod = (int) (10e9 + 7);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(n * quickPow(2, n - 1));
    }

    static long quickPow(long a, long b) {
        if (b == 0) return 1;
        long res = quickPow(a, b >> 1);
        if (b % 2 == 1) {
            return (((res * res) % mod) * a) % mod;
        } else {
            return (res * res) % mod;
        }
    }

}
