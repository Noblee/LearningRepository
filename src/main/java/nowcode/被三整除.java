package nowcode;

import java.util.Scanner;

public class 被三整除 {

    public static void main(String[] args) {
        int a,b ;
        Scanner sc = new Scanner(System.in);
        a = sc.nextInt();
        b = sc.nextInt();
        System.out.println((func(b) - func(a-1)));
    }

    public static int func(int arg) {
        int ress = (arg/3)*2;
        if((arg%3)==2) ress++;
        return ress;
    }
}
