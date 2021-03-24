package rpc.server;

public class UserAccData extends UserData {
    private Double deposit = new Double(0);
    UserAccData(){

    }
    UserAccData(UserData userData)
    {
        this.setPassword(userData.getPassword());
        this.setUsername(userData.getUsername());
        setDeposit((double) 0);
    }
    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }
}
