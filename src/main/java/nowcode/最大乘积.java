package nowcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 最大乘积 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextInt();
        long[] array = new long[(int)n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        PriorityQueue<Long> qmax = new PriorityQueue<>(3 );
        PriorityQueue<Long> qmin = new PriorityQueue<>(3,(a, b) -> (int)(b - a));
        for (long num : array) {
            if (qmax.size() < 3) {
                qmax.add(num);
            } else if (qmax.peek() <= num) {
                qmax.poll();
                qmax.add(num);
            }
            if (qmin.size() < 3) {
                qmin.add(num);
            } else if (qmin.peek() >= num) {
                qmin.poll();
                qmin.add(num);
            }
        }
        ArrayList<Long> min = new ArrayList<>();
        ArrayList<Long> max = new ArrayList<>();

        for (long i = 0; !qmin.isEmpty(); i++) {
            min.add(qmin.poll());
        }
        Collections.reverse(min);

        for (long i = 0; !qmax.isEmpty(); i++) {
            max.add( qmax.poll());
        }
        Collections.reverse(max);
        if(max.get(0)<=0){
            System.out.print(max.get(0)*max.get(1)*max.get(2));
        }else{
            long r1 = max.get(0) * max.get(1) * max.get(2);
            long r2 = max.get(0) * min.get(0) * min.get(1);
            System.out.print(Math.max(r1, r2));
        }
    }
}
