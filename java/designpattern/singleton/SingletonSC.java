package designpattern.singleton;


public class SingletonSC {
    private SingletonSC() {
    }

    public static SingletonSC getInstance() {
        return SingletonSCInstance.INSTANCE;
    }

    public static class SingletonSCInstance {
        private static final SingletonSC INSTANCE = new SingletonSC();
    }
}
