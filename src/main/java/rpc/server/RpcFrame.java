package rpc.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Noble
 */
public class RpcFrame {
    private static ExecutorService executorService = Executors.newFixedThreadPool(100);

    public static void expose(final Object service, int port) throws IOException {

        if (service == null) throw new IllegalArgumentException("service instance == null");
        if (port <= 0 || port > 65535) throw new IllegalArgumentException("Invalid port " + port);

        System.out.println("Export service " + service.getClass().getName() + " on port " + port);
        ServerSocket server = new ServerSocket(port);
        while (true) {
            final Socket socket = server.accept();
            executorService.submit(() -> {
                try{
                    try {
                        //服务端接受客户端传过来的方法名、参数类型、方法所需参数，然后执行方法
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        try {
                            String methodName = input.readUTF();
                            Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                            Object[] arguments = (Object[]) input.readObject();
                            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                            try {
                                Method method = service.getClass().getMethod(methodName, parameterTypes);
                                Object result = method.invoke(service, arguments);
                                output.writeObject(result);
                                if (arguments[0] instanceof UserData) {
                                    System.out.println(new Date() + ":用户 " + ((UserData) arguments[0]).getUsername() + " 调用了 " + methodName + " 方法.");
                                } else {
                                    System.out.println(new Date() + ":" + methodName + "方法被调用.");
                                }

                            } catch (Throwable t) {
                                output.writeObject(t);
                            } finally {
                                output.close();
                            }
                        } finally {
                            input.close();
                        }
                    } finally {
                        socket.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T refer(final Class<T> interfaceClass, final String host, final int port) throws Exception {
        if (interfaceClass == null) {
            throw new IllegalArgumentException("Interface class == null");
        }
        if (!interfaceClass.isInterface()) {
            throw new IllegalArgumentException("The " + interfaceClass.getName() + " must be interface class!");
        }
        if (host == null || host.length() == 0) {
            throw new IllegalArgumentException("Host == null!");
        }
        if (port <= 0 || port > 65535) {
            throw new IllegalArgumentException("Invalid port " + port);
        }
        System.out.println("Get remote service " + interfaceClass.getName() + " from server " + host + ":" + port);

        //利用动态代理，对每个接口类的方法调用进行的隐藏
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
                Socket socket = new Socket(host, port);
                try {
                    //客户端将方法名、参数类型、方法所需参数传给服务端
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    try {
                        output.writeUTF(method.getName());
                        output.writeObject(method.getParameterTypes());
                        output.writeObject(arguments);
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        try {
                            Object result = input.readObject();
                            if (result instanceof Throwable) {
                                throw (Throwable) result;
                            }
                            return result;
                        } finally {
                            input.close();
                        }
                    } finally {
                        output.close();
                    }
                } finally {
                    socket.close();
                }
            }
        });
    }
}



