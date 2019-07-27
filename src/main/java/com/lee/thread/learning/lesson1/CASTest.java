package com.lee.thread.learning.lesson1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子变量类：
 *      AtomicBoolean
 *      AtomicInteger
 *      AtomicLong
 *      AtomicReference
 *
 * 原子数组类：
 *      AtomicLongArray
 *      AtomicReferenceArray
 *
 * 原子方式更新对象中的字段类：
 *      AtomicIntegerFieldUpdate
 *      AtomicReferenceFieldUpdate
 *
 * 高并发汇总类：
 *      LongAdder
 *      LongAccumulator
 *      DoubleAdder
 *      DoubleAccumulator
 */
public class CASTest {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        CountDownLatch latch = new CountDownLatch(10000);

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.print(counter.incrementAndGet() + " ");
                    latch.countDown();
                }
            }).start();
        }
        // 一直等待则不会打印AtomicInteger值
//        latch.await();
        System.out.println("AtomicInteger : " + counter.get());
    }
}
