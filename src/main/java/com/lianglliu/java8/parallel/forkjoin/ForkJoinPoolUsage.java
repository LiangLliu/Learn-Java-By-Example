package com.lianglliu.java8.parallel.forkjoin;

import com.lianglliu.java8.parallel.forkjoin.AccumulatorRecursiveAction;
import com.lianglliu.java8.parallel.forkjoin.AccumulatorRecursiveTask;

import java.util.concurrent.ForkJoinPool;

/**
 * 分治法 —— 分而治之
 */
public class ForkJoinPoolUsage {

    private static final int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public static void main(String[] args) {
        System.out.println("-----------------------------normalCalc--------------------------");
        System.out.println(normalCalc());

        var forkJoinPool = new ForkJoinPool();

        System.out.println("-----------------------------RecursiveTask--------------------------");
        var task = new AccumulatorRecursiveTask(0, numbers.length, numbers);
        Integer result = forkJoinPool.invoke(task);
        System.out.println(result);

        System.out.println("-----------------------------RecursiveAction--------------------------");
        var action = new AccumulatorRecursiveAction(0, numbers.length, numbers);
        forkJoinPool.invoke(action);
        int result1 = AccumulatorRecursiveAction.AccumulatorHelper.getResult();
        AccumulatorRecursiveAction.AccumulatorHelper.reset();
        System.out.println(result1);
    }

    public static int normalCalc() {
        int result = 0;
        for (int number : numbers) {
            result += number;
        }
        return result;
    }
}
