package interview;

public class 输出0_100_todo {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnableA = () -> {
            int i = 0;
            synchronized(输出0_100_todo.class) {
                while(i <= 100) {
                    System.out.println("threadA: " + i);
                    输出0_100_todo.class.notify();
                    try{
                        输出0_100_todo.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    i+2;
                }
            }
        };

        Runnable runnableB = () -> {
            int i = 1;
            synchronized(输出0_100_todo.class) {
                while(i <= 100) {
                    System.out.println("threadB: " + i);
                    输出0_100_todo.class.notify();
                    try {
                        输出0_100_todo.class.wait();
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
//                    i+2;
                }
            }
        };
        Thread threadA = new Thread(runnableA);
        Thread threadB = new Thread(runnableB);
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
        System.out.println("打印完成。");
    }
}