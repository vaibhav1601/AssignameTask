package com.vaibhav.lib;

import java.util.concurrent.atomic.AtomicBoolean;

public class main {


    public static void main(String[] args) {
        AtomicBoolean processing = new AtomicBoolean(true);

        new Executor.Builder()

                .add(() -> {
                    System.out.println("Task 1 Start");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Task 1 complete");

                }).add(() -> {
            System.out.println("Task 2 Start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task 2 complete");


        }).add(main::run).callBack(new Executor.callBack() {
            @Override
            public void onComplete() {
                processing.getAndSet(false);

            }
        }).build().execute();

        while (processing.get()) {
//
        }

        System.out.println("program terminate");
    }


    private static void run() {
        System.out.println("Task 3 Start");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task 3 complete");


    }
}
