package com.lee.thread.learning.lesson3;

public class RecordExample2 {
    static int a = 0;
    static boolean flag = false;

    /**
     * A线程执行
     */
    public static void writer() {
        // ①
        a = 1;
        // ②
        flag = true;
        System.out.println("a = " + a + ", flag = " + flag);
    }

    /**
     * B线程执行
     */
    public static void read() {
        // ③
        if (flag) {
            // ④
            int i = a + a;
            System.out.println("i = " + i);
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                writer();
            }
        });
        t1.start();
        Thread.sleep(3000);

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                read();
            }
        });
        t2.start();
    }
}
