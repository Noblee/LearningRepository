package rpc.server;

public class BankServersImpl implements BankServersInterface {
    private static Repository usercache = new Repository();

    @Override
    public boolean logon(UserData userdata) {
        if (usercache.get(userdata.getUsername()) == null) {
            UserAccData userAccData = new UserAccData(userdata);
            usercache.put(userdata.getUsername(), userAccData);
            return true;
        }
        return false;
    }

    @Override
    public boolean login(UserData userdata) {
        if (usercache.get(userdata.getUsername()) == null) {
            return false;
        } else {
            return usercache.get(userdata.getUsername()).getPassword().equals(userdata.getPassword());
        }
    }

    @Override
    public double getBalance(UserData userdata) {
        if (login(userdata)) {
            return usercache.get(userdata.getUsername()).getDeposit();
        }
        return 0;
    }

    @Override
    public boolean deposit(UserData userdata, double money) {
        if (login(userdata)) {
            UserAccData userAccData = usercache.get(userdata.getUsername());
            userAccData.setDeposit(userAccData.getDeposit() + money);
            return true;
        }
        return false;
    }

    @Override
    public boolean withdraw(UserData userdata, double money) {
        if (login(userdata)) {
            UserAccData userAccData = usercache.get(userdata.getUsername());
            if (userAccData.getDeposit() - money < 0) {
                return false;
            } else {
                userAccData.setDeposit(userAccData.getDeposit() - money);
                return true;
            }
        }
        return false;
    }
//
//    @Override
//    public boolean transfer(UserData userdata, String username, double money) {
//        if (login(userdata)) {
//            UserAccData userAccData = usercache.get(userdata.getUsername());
//            if (userAccData.getDeposit() - money < 0) {
//                return false;
//            } else {
//                userAccData.setDeposit(userAccData.getDeposit() - money);
//                UserAccData AccuserAccData = usercache.get(username);
//                if(AccuserAccData==null) {
//                    return false;
//                }
//                AccuserAccData.setDeposit(AccuserAccData.getDeposit() + money);
//                return true;
//            }
//        }
//        return false;
//    }
}
