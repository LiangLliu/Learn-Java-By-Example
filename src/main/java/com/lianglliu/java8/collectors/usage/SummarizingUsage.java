package com.lianglliu.java8.collectors.usage;

import com.lianglliu.java8.collectors.model.Dish;

import java.util.Collections;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * Stream 计算
 */
public class SummarizingUsage {

    public static void main(String[] args) {
        // counting 统计数量
        howManyDishes();

        // averagingDouble 计算平均值
        calculateAverageCalories();

        // reduce : 计算
        testReducing();

        // summingDouble 统计求和
        calculateTotalCalories();

        // summarizingDouble 汇总统计
        calculateMenuStatistics();

        // collectingAndThen: 计算结果，返回其他
        testCollectingAndThen();

        // joining: 将元素连在一块
        testJoining();
    }

    /**
     * counting 统计数量
     */
    private static void howManyDishes() {
        System.out.println("howManyDishes");
        Optional.of(Dish.menu.stream().collect(Collectors.counting()))
                .ifPresent(System.out::println);
    }

    /**
     * averagingDouble 计算平均值
     */
    private static void calculateAverageCalories() {
        System.out.println("calculateAveragingDouble");
        Optional.of(Dish.menu.stream().collect(Collectors.averagingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);

        Optional.of(Dish.menu.stream().collect(Collectors.averagingInt(Dish::getCalories)))
                .ifPresent(System.out::println);

        Double averagingLong = Dish.menu.stream().collect(Collectors.averagingLong(Dish::getCalories));
        Optional.of(averagingLong).ifPresent(System.out::println);
    }

    /**
     * reduce : 计算
     */
    private static void testReducing() {
        System.out.println("testReducing");
        Dish.menu.stream()
                .collect(Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2))
                .ifPresent(System.out::println);


        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        BinaryOperator<Dish> moreCaloricOf = BinaryOperator.maxBy(dishCaloriesComparator);

        Dish.menu.stream().collect(Collectors.reducing(moreCaloricOf))
                .ifPresent(System.out::println);
    }


    /**
     * summingDouble 统计求和
     */
    private static void calculateTotalCalories() {
        System.out.println("calculateTotalCalories");
        int summingIntCalories = Dish.menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println(summingIntCalories);
        // 等价于
        Dish.menu.stream().mapToInt(Dish::getCalories).sum();

        Optional.of(Dish.menu.stream().collect(Collectors.summingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);
        // 等价于
        Dish.menu.stream().mapToDouble(Dish::getCalories).sum();

        Optional.of(Dish.menu.stream().collect(Collectors.summingLong(Dish::getCalories)))
                .ifPresent(System.out::println);
        // 等价于
        Dish.menu.stream().mapToLong(Dish::getCalories).sum();
    }

    /**
     * summarizingDouble 汇总统计
     */
    private static void calculateMenuStatistics() {
        System.out.println("calculateAveragingDouble");
        IntSummaryStatistics summarizingIntCalories = Dish.menu.stream()
                .collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println(summarizingIntCalories);

        Optional.of(Dish.menu.stream()
                        .collect(Collectors.summarizingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);

        Optional.of(Dish.menu.stream()
                        .collect(Collectors.summarizingLong(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    /**
     * collectingAndThen: 计算结果，返回其他
     */
    private static void testCollectingAndThen() {
        System.out.println("testCollectingAndThen");
        String calcAverage = Dish.menu.stream()
                .collect(Collectors.collectingAndThen(Collectors.averagingInt(Dish::getCalories),
                                average -> "The Average Calories is -> " + average
                        )
                );
        System.out.println(calcAverage);

        // 不能被修改的list
        List<Dish> unmodifiableList = Dish.menu.stream()
                .filter(d -> d.getType().equals(Dish.Type.MEAT))
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        Collections::unmodifiableList
                ));
    }

    /**
     * joining: 将元素连在一块
     */
    private static void testJoining() {
        // porkbeefchickenfrench friesriceseason fruitpizzaprawnssalmon
        Optional.of(Dish.menu.stream().map(Dish::getName).collect(Collectors.joining()))
                .ifPresent(System.out::println);

        // pork,beef,chicken,french fries,rice,season fruit,pizza,prawns,salmon
        Optional.of(Dish.menu.stream().map(Dish::getName).collect(Collectors.joining(",")))
                .ifPresent(System.out::println);

        // Names[pork,beef,chicken,french fries,rice,season fruit,pizza,prawns,salmon]
        Optional.of(Dish.menu.stream().map(Dish::getName).collect(Collectors.joining(",", "Names[", "]")))
                .ifPresent(System.out::println);
    }
}
