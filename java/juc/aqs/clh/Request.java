package juc.aqs.clh;

/**
 * ClassName:Request <br/>
 * Function:对锁的请求. <br/>
 * Reason:对锁的请求. <br/>
 * Date:2017/9/11 16:55 <br/>
 *
 * @since JDK 1.8
 */
public class Request {

    /**
     * myProcess: 发起该请求的线程，myreq对应的myProcess.
     *
     * @since JDK 1.8
     */
    private Process myProcess;
    /**
     * state: 请求状态，PENDING 表示对应线程等待，GRANTED 表示对应线程可以获取锁.
     *
     * @since JDK 1.8
     */
    private State state;

    Request(State state, Process myProcess) {
        this.myProcess = myProcess;
        this.state = state;
    }

    public Request(State state) {
        this.state = state;
    }

    State getState() {
        return state;
    }

    void setState(State state) {
        this.state = state;
    }

    Process getMyProcess() {
        return myProcess;
    }

    public void setMyProcess(Process myProcess) {
        this.myProcess = myProcess;
    }
}