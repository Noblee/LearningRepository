package rpc.server;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws Exception {
        RpcFrame.expose(new BankServersImpl(), 8888);
    }
}

