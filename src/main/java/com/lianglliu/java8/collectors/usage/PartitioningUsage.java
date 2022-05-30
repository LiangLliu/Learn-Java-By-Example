package com.lianglliu.java8.collectors.usage;

import com.lianglliu.java8.collectors.model.Dish;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 谓词分割
 */
public class PartitioningUsage {

    public static void main(String[] args) {

        // partitioningBy : 根据谓词对输入元素进行分区
        testPartitioningBy();

        // partitioningBy、groupingBy
        partitioningByAndGroupingBy();

        // partitioningBy、collectingAndThen
        partitioningByAndCollectingAndThen();
    }

    /**
     * partitioningBy: 根据谓词对输入元素进行分区
     */
    private static void testPartitioningBy() {
        System.out.println("--------------------------testPartitioningBy------------------------");
        Map<Boolean, List<Dish>> partitionByVegetarian =
                Dish.menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));

        partitionByVegetarian.forEach((key, value) -> {
            System.out.println(key + " -> " + value);
        });
    }

    /**
     * partitioningBy、groupingBy
     */
    private static void partitioningByAndGroupingBy() {
        System.out.println("--------------------------partitioningByAndGroupingBy------------------------");
        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType =
                Dish.menu.stream()
                        .collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.groupingBy(Dish::getType)));
        vegetarianDishesByType.forEach((key, value) -> {
            System.out.println(key + " -> " + value);
        });
    }

    /**
     * partitioningBy、collectingAndThen
     */
    private static void partitioningByAndCollectingAndThen() {
        System.out.println("--------------------------partitioningByAndCollectingAndThen------------------------");
        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = Dish.menu.stream()
                .collect(
                        Collectors.partitioningBy(Dish::isVegetarian,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                        Optional::get
                                )
                        )
                );
        mostCaloricPartitionedByVegetarian.forEach((key, value) -> {
            System.out.println(key + " -> " + value);
        });
    }
}
