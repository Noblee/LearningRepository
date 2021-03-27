package juc;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class LearnReentrantLock {

    public static void main(String[] args) {


        ReentrantLock lock = new ReentrantLock();
        new Thread(new Worker(lock)).start();
        new Thread(new Worker(lock)).start();


    }

    static class Worker implements Runnable {

        private ReentrantLock lock;

        public Worker(ReentrantLock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                Thread.sleep(1000000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();

        }
    }
}
