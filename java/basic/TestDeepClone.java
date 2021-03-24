package basic;

import java.io.*;

public class TestDeepClone {

    public static void main(String[] args) {
        System.out.println("----------------深拷贝--------------");
        //重写Object的clone方法,实现深拷贝
        //还是用==查看两个对象的内存地址是否相等来确定是否为两个对象,如果是两个内存地址,那么就是深拷贝
        Animal animal1 = new Animal(new Person("cxh", 26), 3);
        Animal animal2 = CloneUtil.clone(animal1);
        System.out.println("animal1和animal2的host内存地址是否相同:" + (animal1.host == animal2.host));
    }

    static class Person implements Serializable {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

    }

    static class Animal implements Serializable {
        Person host;//主人
        int age;//年纪

        Animal(Person person, int age) {
            this.host = person;
            this.age = age;
        }
    }
}
class CloneUtil {
    public static <T extends Serializable> T clone(T obj) {
        T cloneObj = null;
        try {
            //写入字节流
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.close();

            //分配内存,写入原始对象,生成新对象
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());//获取上面的输出字节流
            ObjectInputStream ois = new ObjectInputStream(bais);

            //返回生成的新对象
            cloneObj = (T) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}
