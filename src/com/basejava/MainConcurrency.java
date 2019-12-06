package com.basejava;


public class MainConcurrency {

    private static int counterOne = 0;
    private static int counterTwo = 0;
    private static final Object LOCK_ONE = new Object();
    private static final Object LOCK_TWO = new Object();

    public static void main(String[] args) throws InterruptedException {

        System.out.println(Thread.currentThread().getName());

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                synchronized (LOCK_ONE) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (LOCK_TWO) {
                        System.out.println(++counterOne);
                    }
                }
            }
        };
        thread1.start();

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                synchronized (LOCK_TWO) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (LOCK_ONE) {
                        System.out.println(++counterTwo);
                    }
                }
            }
        };
        thread2.start();
    }
}
