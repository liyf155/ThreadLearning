package com.lee.thread.learning.lesson1;

/**
 * 串行和并行执行的速度对比
 */
public class ConcurrencyTest {
    private static final long count = 10001;

    public static void main(String[] args) throws Exception {
        concurrency();
        serial();
    }

    /**
     * 并发执行结果
     * @throws InterruptedException
     */
    public static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });
        thread.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        thread.join();
        System.out.println("concurrency:" + time + "ms, b=" + b);
    }

    /**
     * 串行执行结果
     */
    public static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial:" + time + "ms, b=" + b);
    }
}
