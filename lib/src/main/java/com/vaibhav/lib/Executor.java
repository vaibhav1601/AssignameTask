package com.vaibhav.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

public class Executor extends Thread {

    private ConcurrentLinkedQueue<Worker> mWorkers;
    private callBack mCallback;
    private CountDownLatch mLatch;



    public Executor(List<Runnable> tasks, callBack mCallback) {
        this.mCallback = mCallback;
        mWorkers = new ConcurrentLinkedQueue<>();
        mLatch = new CountDownLatch(tasks.size());

        for (Runnable task : tasks) {
            mWorkers.add(new Worker(task, mLatch));
        }

    }


    public void execute() {
        start();
    }

    @Override
    public void run() {
        while (true) {
            Worker mWorker = mWorkers.poll();

            if (mWorker != null) {
                break;
            }

            mWorker.start();
        }

        try {
            mLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if (mCallback != null) {
            mCallback.onComplete();
        }
    }

    public static class Builder {
        private List<Runnable> tasks = new ArrayList<>();
        private callBack mCallback;

        public Builder add(Runnable task) {
            tasks.add(task);
            return this;
        }

        public Builder callBack(callBack mCallback) {
            this.mCallback = mCallback;
            return this;

        }

        public Executor build() {
            return new Executor(tasks, mCallback);
        }
    }


    public interface callBack {
        void onComplete();
    }
}