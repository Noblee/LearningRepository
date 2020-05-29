package other.deepclone;

public class TestDeepClone {

    public static void main(String[] args) {
        System.out.println("----------------深拷贝--------------");
        //重写Object的clone方法,实现深拷贝
        //还是用==查看两个对象的内存地址是否相等来确定是否为两个对象,如果是两个内存地址,那么就是深拷贝
        Animal animal1 = new Animal(new Person("cxh", 26), 3);
        Animal animal2 = CloneUtil.clone(animal1);
        System.out.println("animal1和animal2的host内存地址是否相同:" + (animal1.host == animal2.host));
    }
}
