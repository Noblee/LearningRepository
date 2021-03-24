package basic;

import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor;

public class LCM {
    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        if (x < y) {
            int temp = x;
            x = y;
            y = temp;
        }
        int z = gcd(x, y);
        System.out.println(z*(x/z)*(y/z));

    }
}
