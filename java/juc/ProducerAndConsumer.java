package juc;




import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ProducerAndConsumer {
    static private final ArrayBlockingQueue<String[]> queue = new ArrayBlockingQueue<>(100000);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(() -> {
            while (true) {
                queue.add(new String[]{String.valueOf(UUID.randomUUID()), String.valueOf(Thread.currentThread().getId())});
            }
        });
        executorService.submit(() -> {
            while (true) {
                queue.offer(new String[]{String.valueOf(UUID.randomUUID()), String.valueOf(Thread.currentThread().getId())});
            }
        });
        executorService.submit(() -> {
            while (true) {
                String[] poll = queue.poll(1, TimeUnit.DAYS);
                System.out.println(Arrays.toString(poll));
            }
        });
        Thread.sleep(10000000);

    }

}

class Producer {

}
