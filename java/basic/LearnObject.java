package basic;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class LearnObject {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        String a = "hello";
        String b = "hello";
        System.out.println((a == b));
        String c = new String("hello");
        System.out.println((a == c));
        // It follows that for any two strings s and t, s.intern() == t.intern() is true if and only if s.equals(t) is true.
        System.out.println((a.intern() == c.intern()));
        Field field = String.class.getDeclaredField("value");
        field.setAccessible(true);
        System.out.println((field.get(a) == field.get(c)));


        Field[] m = Integer.class.getFields();
        System.out.println("Field:");
        for (Field n : m) {
            System.out.println(n);
        }
        Method[] z = Integer.class.getDeclaredMethods();
        System.out.println("Method:");
        for (Method n : z) {
            System.out.println(n);
        }
        Integer a1 = 123;
        Long a2 = 123L;
        System.out.println(a1.equals(a2));
        System.out.println(a1 == Integer.valueOf(123));
        System.out.println(Integer.valueOf(123) == Integer.valueOf(123));
        System.out.println(Integer.valueOf(30000) == Integer.valueOf(30000));
        System.out.println((new Integer(30000) == new Integer(30000)));
        System.out.println((new Integer(123) == new Integer(123)));
        //x86是小端模式，网络字节序是大端模式
        ByteOrder byteOrder = ByteOrder.nativeOrder();
        System.out.println("字节序列：" + byteOrder);
    }

}
