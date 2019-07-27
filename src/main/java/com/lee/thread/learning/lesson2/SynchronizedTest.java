package com.lee.thread.learning.lesson2;

/**
 * 重量级锁 synchronized
 */
public class SynchronizedTest {
    public synchronized void test1(){
    }

    public void test2(){
        synchronized (this){
        }
    }
}
