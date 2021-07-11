package juc.aqs.clh;

/**
 * ClassName:State <br/>
 * Function:request状态. <br/>
 * Reason:request状态. <br/>
 * Date:2017/9/12 8:34 <br/>
 *
 * @since JDK 1.8
 */
public enum State {
    /**
     * PENDING: 该状态的request对应的线程等待锁.
     *
     * @since JDK 1.8
     */
    PENDING,
    /**
     * GRANTED: 该状态的request对应的线程可以获取锁.
     *
     * @since JDK 1.8
     */
    GRANTED
}
