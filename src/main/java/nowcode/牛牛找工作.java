package nowcode;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class 牛牛找工作 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int yuangong[] = new int[m+2];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            map.put(a,b);
        }
        int max = Integer.MIN_VALUE;
        for(Map.Entry<Integer, Integer> e:map.entrySet()){
            max = Math.max(max,e.getValue());
            e.setValue(max);
        }
        for (int i = 0; i < m; i++) {
            yuangong[i] = sc.nextInt();
        }
        for (int i = 0; i < m; i++) {
            if (map.floorEntry(yuangong[i]) != null) {
                System.out.println(map.floorEntry(yuangong[i]).getValue());
            }else{
                System.out.println(0);
            }
        }
    }

}
