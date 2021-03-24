package rpc.client;

public interface BankServersInterface {
    boolean logon(UserData userdata);

    boolean login(UserData userdata);

    double getBalance(UserData userdata);

    boolean deposit(UserData userdata, double money);

    boolean withdraw(UserData userdata, double money);
}
