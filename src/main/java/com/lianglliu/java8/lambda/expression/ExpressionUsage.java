package com.lianglliu.java8.lambda.expression;

import java.util.concurrent.Callable;

public class ExpressionUsage {

    public static void main(String[] args) {

        Runnable r1 = () -> {
        };

        Runnable r2 = () -> System.out.println(Thread.currentThread().getName() + " Hello World!");

        Callable<String> stringCallable = () -> "111";
        Callable<Long> longCallable = () -> 1L;

    }
}
