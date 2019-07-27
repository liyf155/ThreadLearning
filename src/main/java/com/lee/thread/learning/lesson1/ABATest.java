package com.lee.thread.learning.lesson1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABATest {

    public static void main(String[] args) throws InterruptedException {
        CASABATest();
        atomicStampedReferenceTest();
    }

    public static void atomicStampedReferenceTest() {
        // 第一个参数：引用对象的初始值，第二个参数：这个对象的初始标记
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(0, 0);
        new Thread(() -> {
            // compareAndSet(eR,nR,eS,nS) eR：引用对象的期望值，nR：引用对象的新值，eS：引用对象期望标记，nS：引用对象的新标记，当 eR、eS和当前线程的值相等才会修改eR、eS的值为nR、nS
            System.out.println(Thread.currentThread().getName() + ", " + atomicStampedReference.compareAndSet(0, 1, 0, 1) + ", atomicStampedReference value:" + atomicStampedReference.getReference());
        }, "Thread A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", " + atomicStampedReference.compareAndSet(1, 0, 1, 2) + ", atomicStampedReference value:" + atomicStampedReference.getReference());
            }
        }, "Thread B").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", " + atomicStampedReference.compareAndSet(0, 1, 0, 1) + ", atomicStampedReference value:" + atomicStampedReference.getReference());
            }
        }, "Thread C").start();
    }

    /**
     * 演示CAS 更新的ABA 问题
     */
    public static void CASABATest() throws InterruptedException {
        AtomicInteger integer = new AtomicInteger(0);

        new Thread(new Runnable() {
            @Override
            public void run() {
                // compareAndSet(e,u) e:期望值，u：新值，如果e的值在当前线程中和integer的值一致，就把e的值修改成u的值
                System.out.println(Thread.currentThread().getName() + ", " + integer.compareAndSet(0, 1) + ", AtomicInteger value:" + integer); //true
            }
        }, "Thread A").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", " + integer.compareAndSet(1, 0) + ", AtomicInteger value:" + integer); //true
            }
        }, "Thread B").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", " + integer.compareAndSet(0, 1) + ", AtomicInteger value:" + integer); //true
            }
        }, "Thread C").start();

    }
}
