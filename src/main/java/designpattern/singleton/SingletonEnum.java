package designpattern.singleton;

//枚举模式
public enum SingletonEnum {
    INSTANCE;

    public static SingletonEnum getInstance() {
        return SingletonEnum.INSTANCE;
    }

}