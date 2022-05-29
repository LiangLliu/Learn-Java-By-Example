package com.lianglliu.java8.stream.basic;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumericStreamsUsage {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 3);
        System.out.println("data source : \n" + numbers);

        numbers.stream()
                .filter(it -> it > 3)
                .reduce(Integer::sum)
                .ifPresent(it -> System.out.println("number > 3 sum : " + it));

        IntStream intStream = numbers.stream().mapToInt(it -> it);
        intStream.filter(it -> it > 3)
                .reduce(Integer::sum)
                .ifPresent(it -> System.out.println("number > 3 sum : " + it));

        // 勾股数
        System.out.println("勾股数字\n");
        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 100)
                        .boxed()
                        .flatMap(a -> IntStream.rangeClosed(a, 100)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .boxed()
                                .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                        );

        pythagoreanTriples.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
    }
}
