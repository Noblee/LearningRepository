package basic;

import java.util.UUID;

public class JavaMemoryTest {
    public static void main(String[] args) throws InterruptedException {

        System.out.println((-1 % -5));

        // 字符串常量区
        String[] stringPool = new String[100000];
        for (int i = 0; i < stringPool.length; i++) {
            stringPool[i] = new String("123");
        }

        Thread.sleep(100000000);
    }

}
