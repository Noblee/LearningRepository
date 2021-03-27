package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class 欧拉晒 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        boolean temp[] = new boolean[n];
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.fill(temp, true);
        for (int i = 2; i < n; i++) {
            if (temp[i]) {
                list.add(i);
            }
            for (int j = 0; j < list.size() && i * list.get(j) < n; j++) {
                temp[i * list.get(j)] = false;
                if (i % list.get(j) == 0) {
                    break;
                }
            }
        }
        System.out.println(list);
    }
}
