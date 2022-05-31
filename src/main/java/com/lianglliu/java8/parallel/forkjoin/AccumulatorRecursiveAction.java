package com.lianglliu.java8.parallel.forkjoin;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

public class AccumulatorRecursiveAction extends RecursiveAction {

    private final int start;
    private final int end;
    private final int[] data;
    private final int limit = 3;

    public AccumulatorRecursiveAction(int start, int end, int[] data) {
        this.start = start;
        this.end = end;
        this.data = data;
    }

    @Override
    protected void compute() {
        if ((end - start) <= limit) {
            for (int i = start; i < end; i++) {
                AccumulatorHelper.accumulator(data[i]);
            }
        } else {
            int mid = (start + end) / 2;

            var leftAccumulatorRecursiveAction = new AccumulatorRecursiveAction(start, mid, data);
            var rightAccumulatorRecursiveAction = new AccumulatorRecursiveAction(mid, end, data);

            leftAccumulatorRecursiveAction.fork();
            rightAccumulatorRecursiveAction.fork();

            leftAccumulatorRecursiveAction.join();
            rightAccumulatorRecursiveAction.join();
        }
    }

    static class AccumulatorHelper {

        private static final AtomicInteger result = new AtomicInteger(0);

        static void accumulator(int value) {
            result.addAndGet(value);
        }

        public static int getResult() {
            return result.get();
        }

        static void reset() {
            result.set(0);
        }
    }
}
