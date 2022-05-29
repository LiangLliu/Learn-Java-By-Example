package com.lianglliu.java8.stream.basic;

import com.lianglliu.java8.stream.model.Dish;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class FilteringUsage {

    public static void main(String[] args) {

        // Filtering with predicate
        List<Dish> vegetarianMenu = Dish.MENU
                .stream()
                .filter(Dish::isVegetarian)
                .collect(toList());
        vegetarianMenu.forEach(System.out::println);

        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4, 5, 5, 6, 7, 8);
        System.out.println("========================Source Data=========================");
        numbers.forEach(it -> System.out.print(it + " "));
        // Filtering unique elements
        System.out.println("\n====================Filtering unique elements=====================");

        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(it -> System.out.print(it + " "));

        // Truncating a stream
        System.out.println("\n========================Truncating a stream=========================");
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .limit(3)
                .forEach(it -> System.out.print(it + " "));

        // Skipping elements
        System.out.println("\n========================Skipping elements=========================");
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .skip(3)
                .forEach(it -> System.out.print(it + " "));
    }
}
