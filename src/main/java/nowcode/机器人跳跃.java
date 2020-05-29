package nowcode;

import java.util.Scanner;

public class 机器人跳跃 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] a = new long[n];
        long e = 1;
        long re = 1;
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            e += e - a[i];
            while (e < 0) {
                e += pow(i + 1);
                re++;
            }
        }
        System.out.print(re);
    }
    static long pow(long i){
        long x=1;
        for (int j = 0; j < i; j++) {
            x=x*2;
        }
        return x;
    }
}
