package com.example.demo.synchronizedOne.anli;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Sync extends AbstractQueuedSynchronizer {

    @Override
    protected boolean tryAcquire(int arg) {
        if (compareAndSetState(0, 1)) {
            setExclusiveOwnerThread(Thread.currentThread());
            setState(1);
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
        setState(0);
        setExclusiveOwnerThread(null);
        return true;
    }

    @Override
    protected boolean isHeldExclusively() {
        return getState()==1;
    }
}
