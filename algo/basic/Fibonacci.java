package basic;

import java.util.Scanner;

/**
 * tag
 */
public class Fibonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double temp = Math.sqrt(5);
        double ret = ((1 / (temp)) * (Math.pow((1 + temp) / 2, n) - Math.pow((1 - temp) / 2, n)));
        System.out.println(Math.round(ret));
    }
}
