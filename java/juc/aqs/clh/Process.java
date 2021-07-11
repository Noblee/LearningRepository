package juc.aqs.clh;

/**
 * ClassName:Process <br/>
 * Function:请求锁的线程. <br/>
 * Reason:请求锁的线程. <br/>
 * Date:2017/9/11 17:01 <br/>
 *
 * @since JDK 1.8
 */
public class Process implements Runnable {
    /**
     * clh: 线程请求的clh锁.
     *
     * @since JDK 1.8
     */
    private CLH clh;

    /**
     * name: 当前线程名，方便观察，request对象与线程的对应关系.
     *
     * @since JDK 1.8
     */
    private String name;

    Process(String name, CLH clh) {
        this.clh = clh;
        this.name = name;

    }

    @Override
    public void run() {
        //1.请求锁
        clh.lock(this);
        //2.程序性等待，获取锁之后等待2秒钟，释放锁
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //释放锁
        clh.unlock();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}