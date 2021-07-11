package juc.aqs.clh;

/**
 * ClassName:CLH <br/>
 * Function:CLH 队列锁. <br/>
 * Reason:CLH 队列锁. <br/>
 * Date:2017/9/11 16:59 <br/>
 *
 * @since JDK 1.8
 */
public class CLH {

    /**
     * lock: clh队列锁的lock对象.
     * @since JDK 1.8
     */
    private Lock lock;

    /**
     * watch: 当前线程自旋监视的目标Request，为前驱process的myreq.
     *
     * @since JDK 1.8
     */
    private ThreadLocal<Request> watch;
    /**
     * myreq: 当前线程持有的Request，当且仅当当前线程释放锁后更新为GRANTED状态，否则为PENDING状态.
     *
     * @since JDK 1.8
     */
    private ThreadLocal<Request> myreq;

    private CLH() {
        this.lock = new Lock();
        //初始化myreq对象，状态为PENDING，对应的线程为当前的myProcess
        this.myreq = ThreadLocal.withInitial(() -> new Request(State.PENDING));
        //watch 初始化为null，加入到队列之后，会指向前驱process的myreq
        this.watch = new ThreadLocal<Request>();
    }

    /**
     * lock:请求锁. <br/>
     */
    public void lock(Process process) {
        myreq.get().setState(State.PENDING);
        myreq.get().setMyProcess(process);
        Request tmp = lock.getTail().getAndSet(myreq.get());
        watch.set(tmp);
        boolean flag = true;
        while (watch.get().getState() == State.PENDING) {
            try {
                if (watch.get().getMyProcess() != null) {
                    if (flag) {
                        System.out.println("   " + myreq.get().getMyProcess().getName() + "    | is waiting for " + watch.get().getMyProcess().getName()
                                + " | " + myreq.get().getState() + " | " + watch.get().getState() + " |    " +
                                "added to queue    | ");
                    } else {
                        System.out.println("   " + myreq.get().getMyProcess().getName() + "    | is waiting for " + watch.get().getMyProcess().getName()
                                + " | " + myreq.get().getState() + " | " + watch.get().getState() + " |      " +
                                "                |");
                    }
                    if (lock.getTail().get().equals(myreq.get())) {
                        System.out.println("— — — — — — — — — — — — — — — — — — — — — — — — |");
                    }
                }
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = false;
        }
        if (flag) {
            System.out.println("   " + myreq.get().getMyProcess().getName() + "    |      get lock     | " + myreq.get().getState() +
                    " | " + watch.get().getState() + " |    added to queue    | ");
        } else {
            System.out.println("   " + myreq.get().getMyProcess().getName() + "    |      get lock     | " + myreq.get().getState() +
                    " | " + watch.get().getState() + " |                      |");
        }
        if (lock.getTail().get().equals(myreq.get())) {
            System.out.println("— — — — — — — — — — — — — — — — — — — — — — — — |");
        }
    }

    /**
     * unlock:释放锁. <br/>
     */
    public void unlock() {
        myreq.get().setState(State.GRANTED);
        System.out.println("   " + myreq.get().getMyProcess().getName() + "    |   release lock    | " + myreq.get().getState() +
                " |    X    |   remove from queue  |");
        if (lock.getTail().get().equals(myreq.get())) {
            System.out.println("— — — — — — — — — — — — — — — — — — — — — — — — |");
        }
        // threadlocal 类型使用之后强制remove保证没有内存溢出
        myreq.remove();
        myreq.set(watch.get());
        //释放锁之后，watch字段不关心，置空，并且可以保证无内存溢出
        watch.remove();
    }


    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }

    public ThreadLocal<Request> getWatch() {
        return watch;
    }

    public void setWatch(ThreadLocal<Request> watch) {
        this.watch = watch;
    }

    public ThreadLocal<Request> getMyreq() {
        return myreq;
    }

    public void setMyreq(ThreadLocal<Request> myreq) {
        this.myreq = myreq;
    }

    public static void main(String[] args) throws InterruptedException {
        CLH clh = new CLH();
        Process process1 = new Process("p1",clh);
        Process process2 = new Process("p2",clh);
        Process process3 = new Process("p3",clh);
        Process process4 = new Process("p4",clh);
        System.out.println("  线程   |       action      |  myreq  |  watch  |        queue         |");
        System.out.println("— — — — — — — — — — — — — — — — — — — — — — — — |");
        new Thread(process1).start();
        Thread.sleep(100);
        new Thread(process2).start();
        Thread.sleep(100);
        new Thread(process3).start();
        Thread.sleep(100);
        new Thread(process4).start();

    }
}