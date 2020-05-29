package designpattern.singleton;

public class SingletonDCL {

    private static volatile SingletonDCL instance = null;

    private SingletonDCL() {

    }

    public static SingletonDCL getInstance() {
        if (instance == null) {
            synchronized (SingletonDCL.class) {
                if (instance == null)
                    instance = new SingletonDCL();
            }
        }
        return instance;
    }
}
