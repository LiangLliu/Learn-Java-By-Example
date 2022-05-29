package com.lianglliu.java8.stream.basic;

import com.lianglliu.java8.stream.model.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 数据聚合
 */
public class ReducingUsage {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
        System.out.println("dats source : \n" + numbers);

        // sum
        System.out.println("sum: " + numbers.stream().reduce(0, (a, b) -> a + b));
        System.out.println("sum: " + numbers.stream().reduce(0, Integer::sum));

        // max
        System.out.println("max: " + numbers.stream().reduce(0, Integer::max));

        // min
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(it -> System.out.println("min: " + it));

        System.out.println("===================Dish menu  sum: ===================");
        Dish.MENU.stream()
                .map(Dish::getCalories)
                .reduce(Integer::sum)
                .ifPresent(System.out::println);

    }
}
