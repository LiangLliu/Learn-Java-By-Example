package com.lianglliu.java8.lambda.usage;

public class DefFuncInterFaceUsage {
    @FunctionalInterface
    interface Adder {
        int add(int a, int b);
    }

    // error
//    @FunctionalInterface
    public interface SmartAdder extends Adder {
        long add(long a, long b);
    }

    // true
    @FunctionalInterface
    public interface Empty extends Adder {
    }
}
