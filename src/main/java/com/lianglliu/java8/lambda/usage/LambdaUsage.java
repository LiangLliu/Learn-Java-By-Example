package com.lianglliu.java8.lambda.usage;

public class LambdaUsage {
    public static void main(String[] args) {
        Runnable r1 = () -> System.out.println(Thread.currentThread().getName() + " Hello World!, r1");

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " Hello World!, r2");
            }
        };

        process(r1);
        process(r2);
        process(() -> System.out.println(Thread.currentThread().getName() + " Hello World!, anonymous"));
    }

    private static void process(Runnable runnable) {
        runnable.run();
    }
}
