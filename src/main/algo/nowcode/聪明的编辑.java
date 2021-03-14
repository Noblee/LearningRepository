package nowcode;

import java.util.ArrayList;
import java.util.Scanner;

public class 聪明的编辑 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            String str = sc.next();
            func(str);
        }

    }

    public static void func(String arg) {
        ArrayList<Character> chars = new ArrayList<>(arg.length());
        for (int i = 0; i < arg.toCharArray().length; i++) {
            chars.add(arg.toCharArray()[i]);
        }
        for (int i = 0; i < chars.size(); i++) {
            if(i>=3&&chars.get(i-3)==chars.get(i-2)&&chars.get(i)==chars.get(i-1)){
                chars.remove(i);
                i--;
                continue;
            }
            if(i>=2&&chars.get(i-1)==chars.get(i-2)&&chars.get(i)==chars.get(i-1)){
                chars.remove(i);
                i--;
                continue;
            }
            System.out.print(chars.get(i));
        }
        System.out.println();
    }

}
