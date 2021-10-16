package com.vaibhav.lib;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class Worker implements Runnable {
    private Thread mThread;
    private Runnable task;
    private AtomicBoolean started;
    private CountDownLatch mLatch;


    public Worker(Runnable mTask, CountDownLatch mLatch) {
        mThread = new Thread(this);
        started = new AtomicBoolean(false);
        this.task = mTask;
        this.mLatch = mLatch;

    }

    public void start() {
        if (started.getAndSet(true))
            mThread.start();
    }


    @Override
    public void run() {
        task.run();
        mLatch.countDown();

    }
}
