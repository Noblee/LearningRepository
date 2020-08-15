package basic;
import java.util.ArrayList;
import java.util.List;

/**
 * https://www.jianshu.com/p/b5bc4b7ff236
 */
public class ClassCastTest {

  public static void main(String[] args) {
    //Java运行时不存储范型类型的。但Java的泛型擦除是有范围的，即类定义中的泛型是不会被擦除的。
    List<String> strings1 = new ArrayList<String>() {};
    List<String> strings2 = new ArrayList<String>() {};
    System.out.println(strings1.getClass());
    System.out.println(strings2.getClass());

    System.out.println(strings1.getClass().getGenericSuperclass());
    strings1 = new ArrayList<String>();
    System.out.println(strings1.getClass().getGenericSuperclass());

    A a = new A();
    System.out.println(a.getClass().getSuperclass());
    System.out.println(a.getClass().getGenericSuperclass());
  }

}
class B<X,Y>{
  X a;
  Y b;
}
class A extends B<Integer,String>{

}