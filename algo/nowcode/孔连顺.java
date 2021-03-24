package nowcode;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class 孔连顺 {

    public static void main(String[] args) {
        long  n, d;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        d = sc.nextInt();
        TreeMap<Long,Long> map = new TreeMap<>();
        for (long i = 0; i < n; i++) {
            long temp = sc.nextInt();
            map.put(temp, i+1);
        }
        long c = 0;
        long lasti = 0;
        for (Map.Entry<Long, Long> e : map.entrySet()) {
            Map.Entry<Long, Long> entry = map.floorEntry(e.getKey() + d);
            long size = entry.getValue() - e.getValue()+1;
            if(size>2)
            c =  (c+(size * (size - 1) * (size - 2)) / 6)%99997867;
            size = lasti - e.getValue()+1;
            if(size>2)
            c =(c- (size * (size - 1) * (size - 2)) / 6)%99997867;
            lasti = entry.getValue();
        }
        System.out.println(c);
    }
}
