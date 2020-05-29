package classloader;

/**
 * reference: https://dandanlove.com/2017/02/23/java-classloader/
 * 为什么说java spi破坏双亲委派模型？ - ZeaTalk的回答 - 知乎
 * https://www.zhihu.com/question/49667892/answer/690161827
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        MyClassLoader cl1 = new MyClassLoader();
        //磁盘中/home/im/Desktop/Hello.class文件存在
        try {
            Class c1 = cl1.loadClass("Hello");
            Object object = c1.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("main-ClassNotFoundException");
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}