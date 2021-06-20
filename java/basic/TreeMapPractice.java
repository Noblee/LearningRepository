package basic;

import io.netty.util.internal.StringUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapPractice {

    public static void main(String[] args) {
        final TreeMap<String, String> map = new TreeMap<>();
        Character[] chars = new Character[100];
        Arrays.sort(chars, Comparator.comparingInt(a -> a));

    }
}
