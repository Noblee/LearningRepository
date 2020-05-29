package interview;

import java.util.ArrayList;
import java.util.List;

public class PDD字符串编码 {
    public static void main(String[] args) {
        String[] strings = {null, "", "Te,s%%t", "Te,,s%%t", "Te,%,s%%t"};
        String encode = encode(strings);
        List<String> decode = decode(encode);
        System.out.println(decode);
    }

    public static String encode(String[] strs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            sb.append(strs[i].replace("%", "%%").replace(",", "%,"));
            sb.append(",");
        }
        return sb.toString();
    }

    public static List<String> decode(String str) {
        List<String> strs = new ArrayList<>();
        boolean flag = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!flag && c == '%') {
                flag = true;
            } else if (flag) {
                sb.append(c);
                flag = false;
            } else if (c == ',') {
                strs.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        return strs;
    }
}
