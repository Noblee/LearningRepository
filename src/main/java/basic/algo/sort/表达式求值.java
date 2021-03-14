package basic.algo.sort;

import java.util.HashMap;
import java.util.Map;

public class 表达式求值 {
    static private Map<Character, Integer> map = new HashMap<>();

    static {
        map.put('(', 8);
        map.put('-', 1);
        map.put('+', 1);
        map.put('*', 2);
        map.put('/', 2);
        map.put(')', 9);
        map.put('^', 4);

    }


}
