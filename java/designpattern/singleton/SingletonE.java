package designpattern.singleton;


//线程安全
//恶汉模式
public class SingletonE {

    private final static SingletonE INSTANCE = new SingletonE();

    private SingletonE() {
    }

    public static SingletonE getInstance() {
        return INSTANCE;
    }

}
