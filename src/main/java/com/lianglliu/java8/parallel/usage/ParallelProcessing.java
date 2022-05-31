package com.lianglliu.java8.parallel.usage;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelProcessing {
    public static void main(String[] args) {

        // 查看 Java 虚拟机可用的处理器数量
        viewAvailableProcessors();

        // 计算性能对比
        comparePerformance();
    }

    private static void comparePerformance() {
        System.out.println("-----------------------------measurePerformance-normalAdd-------------------------");
        long normalTime = measurePerformance(ParallelProcessing::normalAdd, 1_000_000_000);
        System.out.println("normalAdd: The best process time = " + normalTime);

//        System.out.println("-----------------------------measurePerformance-iterateStream-------------------------");
//        long iterateStreamTime = measurePerformance(ParallelProcessing::iterateStream, 10_000_000);
//        System.out.println("iterateStream: The best process time = " + iterateStreamTime);
//
//        System.out.println("-----------------------------measurePerformance-iterateParallelStream-------------------------");
//        long iterateParallelStreamTime = measurePerformance(ParallelProcessing::iterateParallelStream, 10_000_000);
//        System.out.println("iterateParallelStream: The best process time = " + iterateParallelStreamTime);

        System.out.println("-----------------------------measurePerformance-longStreamParallelStream-------------------------");
        long longStreamParallelStreamTime = measurePerformance(ParallelProcessing::longStreamParallelStream, 1_000_000_000);
        System.out.println("longStreamParallelStream: The best process time = " + longStreamParallelStreamTime);
    }

    // 查看 Java 虚拟机可用的处理器数量
    private static void viewAvailableProcessors() {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    private static long measurePerformance(Function<Long, Long> adder, long limit) {
        long faster = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long startTimestamp = System.currentTimeMillis();
            long result = adder.apply(limit);
            long duration = System.currentTimeMillis() - startTimestamp;
            if (duration < faster) {
                faster = duration;
            }
        }
        return faster;
    }

    private static long iterateStream(long limit) {
        return Stream.iterate(1L, i -> i + 1)
                .mapToLong(Long::longValue)
                .limit(limit)
                .reduce(0L, Long::sum);
    }

    private static long iterateParallelStream(long limit) {
        return Stream.iterate(1L, i -> i + 1)
                .mapToLong(Long::longValue)
                .parallel()
                .limit(limit)
                .reduce(0L, Long::sum);
    }

    private static long longStreamParallelStream(long limit) {
        return LongStream.rangeClosed(0, limit)
                .parallel()
                .limit(limit)
                .reduce(0L, Long::sum);
    }

    private static long normalAdd(long limit) {
        long result = 0L;
        for (int i = 0; i < limit; i++) {
            result += 1;
        }
        return result;
    }

}
