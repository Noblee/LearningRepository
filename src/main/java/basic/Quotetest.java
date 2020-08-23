package basic;
public class Quotetest{
    private static void swap(X a,X b){
        X c = a ;
        a = b;
        b = c;
    }
    public static void main(String[] args){
        X x = new X();
        X y = new X();
        x.a =1;
        y.a =2;
        swap(x,y);
        System.out.println(x.a+" "+y.a);
    }
}
class X{
    int a;
}