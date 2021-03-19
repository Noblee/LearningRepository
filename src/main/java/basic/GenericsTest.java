package basic;
import java.util.*;

/**
 * https://www.jianshu.com/p/b5bc4b7ff236
 */
public class GenericsTest {

  public static void main(String[] args) {
    // 我们可能期望能够获得真实的泛型参数，但是仅仅获得了声明时泛型参数占位符
    List<Integer> list = new ArrayList<Integer>();
    Map<Integer, String> map = new HashMap<Integer, String>();
    System.out.println(Arrays.toString(list.getClass().getTypeParameters()));
    System.out.println(Arrays.toString(map.getClass().getTypeParameters()));
    //Java运行时不存储范型类型的。但Java的泛型擦除是有范围的，即类定义中的泛型是不会被擦除的。
    List<String> strings1 = new ArrayList<String>() {};
    List<String> strings2 = new ArrayList<String>();
    System.out.println(strings1.getClass());
    System.out.println(strings2.getClass());
    System.out.println(strings1.getClass().getGenericSuperclass());
    System.out.println(strings2.getClass().getGenericSuperclass());
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