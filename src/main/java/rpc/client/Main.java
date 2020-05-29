package rpc.client;

import java.util.Scanner;

public class Main {
    private static BankServersInterface bankServersInterface;
    private static UserData userData;

    private static void login() {
        System.out.println("----BankServiceLogin----");
        System.out.println("输入账号密码回车分隔.");
        System.out.println("----BankServiceLogin----");
        UserData userDatabuff = new UserData();
        Scanner in = new Scanner(System.in);
        userDatabuff.setUsername(in.next());
        userDatabuff.setPassword(in.next());
        if (bankServersInterface.login(userDatabuff)) {
            System.out.println("登陆成功");
            userData = userDatabuff;
            services();
        } else {
            System.out.println("账号密码不正确，请重新输入。");
        }

    }

    private static void logon() {

        System.out.println("----BankServiceLogon----");
        System.out.println("输入账号密码回车分隔");
        System.out.println("----BankServiceLogon----");
        UserData userDatabuff = new UserData();
        Scanner in = new Scanner(System.in);
        userDatabuff.setUsername(in.next());
        userDatabuff.setPassword(in.next());
        if (bankServersInterface.logon(userDatabuff)) {
            System.out.println("注册成功，请登陆。");
            userData = userDatabuff;
            login();
        } else {
            System.out.println("账号已存在，请重新输入。");
        }

    }

    public static void services() {
        while (true) {
            System.out.println("当前线程" + Thread.currentThread());
            System.out.println("----BankServices----");
            System.out.println("您是白金卡会员请操作：");

            System.out.println("1.查询余额");
            System.out.println("2.存款");
            System.out.println("3.取款");
//            System.out.println("4.转账");
            System.out.println("5.退出");
            System.out.println("----BankServices----");
            Scanner in = new Scanner(System.in);
            int num = in.nextInt();
            switch (num) {
                case 1:
                    System.out.print("用户：" + userData.getUsername()
                            + "\n余额为\n");
                    System.out.printf("%.2f\n", bankServersInterface.getBalance(userData));
                    break;
                case 2:
                    System.out.println("请输入存款金额。");
                    Double money = in.nextDouble();
                    if (bankServersInterface.deposit(userData, money)) {
                        System.out.println("存款成功");
                        System.out.print("用户：" + userData.getUsername()
                                + "\n余额为\n");
                        System.out.printf("%.2f\n", bankServersInterface.getBalance(userData));
                    } else {
                    }
                    break;
                case 3:

                    System.out.println("请输入取款金额。");
                    Double moneyy = in.nextDouble();
                    if (bankServersInterface.withdraw(userData, moneyy)) {
                        System.out.println("取款成功");
                        System.out.print("用户：" + userData.getUsername()
                                + "\n余额为\n");
                        System.out.printf("%.2f\n", bankServersInterface.getBalance(userData));
                    } else if ((bankServersInterface.getBalance(userData) - moneyy) < 0) {
                        System.out.println("你没那么多钱。");
                    } else {
                        System.out.println("账号错误？你改内存了？");
                    }
                    break;
                case 5:
                    return;
                //break;
                default:
                    break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        bankServersInterface = RpcFrame.refer(BankServersInterface.class, "localhost", 8888);
        while (true) {
            System.out.println("----BankService----");
            System.out.println("1.柜台开户");
            System.out.println("2.ATM登陆");
            System.out.println("3.退出");
            System.out.println("----BankService----");
            Scanner in = new Scanner(System.in);
            int num = in.nextInt();
            switch (num) {
                case 1:
                    logon();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    return;
                default:
                    break;
            }
        }
    }
}
