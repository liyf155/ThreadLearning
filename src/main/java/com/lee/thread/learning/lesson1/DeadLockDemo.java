package com.lee.thread.learning.lesson1;

public class DeadLockDemo {
    private static String a = "A";
    private static String b = "B";

    public static void main(String[] args) throws Exception {
        deadLock();
    }

    public static void deadLock() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (a) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (b) {
                        System.out.println("B--" + Thread.currentThread().getName());
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (b) {
                    synchronized (a) {
                        System.out.println("A--" + Thread.currentThread().getName());
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }
}
