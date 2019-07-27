package com.lee.thread.learning.lesson3;

/**
 * 重排序示例
 */
public class RecordExample {
    public static void main(String[] args) {
        // a 和 b 没有任何依赖关系，处理器和编译器可能会进行重排序
        int a = 1;
        int b = 2;

        try {
            // 将a赋值为3
            a = 3;
            // b除以0，运行时异常
            b = b / 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // a 等于什么？？？
            System.out.println("a = " + a);
        }
    }
}
