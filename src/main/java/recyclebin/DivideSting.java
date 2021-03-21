package recyclebin;

import java.util.Scanner;

//给
public class DivideSting {

    public static void main(String[] args) {
        StringBuffer str = new StringBuffer();
        StringBuffer newstr = new StringBuffer();
        Scanner scanner = new Scanner(System.in);
        str.append(scanner.next());
        int flag;
        if ((str.codePointAt(0) & 0xffff00) == 0) {
            flag = 0;
        } else {
            flag = 1;
        }
        for (int i = 0; i < str.length(); i++) {
            int temp = str.codePointAt(i) ;

            if ((temp & 0xffff00) == 0) {
                if (flag == 0) {
                    //是字母
                    newstr.append((char) temp);
                } else {
                    newstr.append('/');
                    newstr.append((char) temp);
                    flag = 0;
                }
            } else {
                if (flag == 1) {
                    if ((temp & 0xff0000) != 0) {
                        String s = String.valueOf(Character.toChars(temp));
                        newstr.append(s);
                    } else {
                        newstr.append((char) temp);
                    }
                } else {
                    newstr.append('/');
                    if ((temp & 0xff0000) != 0) {
                        String s = String.valueOf(Character.toChars(temp));
                        newstr.append(s);
                    } else {
                        newstr.append((char) temp);
                    }
                    flag = 1;
                }
            }
            if(Character.isSupplementaryCodePoint(temp)) {
                i++;
            }
        }
        System.out.println(newstr.toString());
    }
}
