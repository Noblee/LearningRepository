package juc.aqs.clh;

import java.util.concurrent.atomic.AtomicReference;

/**
 * ClassName:Lock <br/>
 * Function:CLH队列锁的Lock对象. <br/>
 * Reason:CLH队列锁的Lock对象. <br/>
 * Date:2017/9/11 16:55 <br/>
 *
 * @since JDK 1.8
 */
public class Lock {
    /**
     * tail: tail指针指向最后一个加入队列的process的myreq.
     *       由于入队操作涉及的几个指针赋值逻辑上不可分割，否则会出现问题，
     *       所以对request指针都采用原子类。
     *
     * @since JDK 1.8
     */
    private AtomicReference<Request> tail;

    Lock() {
        //初始状态，tail指向一个不属于任何线程，状态为GRANTED的request
        tail = new AtomicReference<Request>(new Request(State.GRANTED, null));
    }

    AtomicReference<Request> getTail() {
        return tail;
    }

    public void setTail(AtomicReference<Request> tail) {
        this.tail = tail;
    }
}