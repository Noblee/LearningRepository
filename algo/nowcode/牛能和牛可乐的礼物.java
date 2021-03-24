package nowcode;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class 牛能和牛可乐的礼物 {
    static double min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String[] split = s.substring(1, s.length() - 1).split(",");
        List<Integer> list = Arrays.stream(split).map(Integer::parseInt).collect(Collectors.toList());
        int sum = list.stream().mapToInt(a -> a).sum();
        double focus = (double) sum / 2.0;
        func(list, 0, 0, focus, sum);
        System.out.println((int) Math.ceil(min * 2));
    }

    public static void func(List<Integer> list, int cur, int sum, double focus, int last) {
        if (focus - sum > 0) {
            min = Math.min(focus - sum, min);
        } else if (focus - sum == 0) {
            min = 0;
            return;
        } else {
            return;
        }
        if (sum + last < focus - min)
            return;
        if (cur != list.size()) {
            func(list, cur + 1, sum + list.get(cur), focus, last - list.get(cur));
            func(list, cur + 1, sum, focus, last - list.get(cur));
        }
    }
}
