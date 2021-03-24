package designpattern.singleton;

//懒汉模式，非现场安全
public class SingletonL {

    private static SingletonL instance = null;

    private SingletonL() {
    }

    public static SingletonL getInstance() {
        if (instance == null)
            instance = new SingletonL();
        return instance;
    }

}
