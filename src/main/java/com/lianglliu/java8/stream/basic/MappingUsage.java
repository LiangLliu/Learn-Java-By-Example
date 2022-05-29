package com.lianglliu.java8.stream.basic;

import com.lianglliu.java8.stream.model.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MappingUsage {

    public static void main(String[] args) {

        // map with name
        System.out.println("\n====================map with name=====================");
        Dish.MENU.stream()
                .map(Dish::getName)
                .forEach(it -> System.out.print(it + " "));

        List<String> words = Arrays.asList("Hello", "World", "Stream");
        System.out.println("\n====================Get String length=====================");
        words.stream()
                .map(String::length)
                .forEach(it -> System.out.print(it + " "));

        // flatmap
        System.out.println("\n==================== flatmap =====================");
        words.stream()
                .flatMap(word -> Arrays.stream(word.split("")))
                .distinct()
                .forEach(it -> System.out.print(it + " "));

        // flatmap
        System.out.println("\n==================== flatmap =====================");
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> numbers2 = Arrays.asList(6, 7, 8);
        List<int[]> pairs = numbers1.stream()
                .flatMap(integer -> numbers2.stream()
                        .map(j -> new int[]{integer, j})
                )
                .filter(pair -> (pair[0] + pair[1]) % 3 == 0)
                .collect(Collectors.toList());
        pairs.forEach(pair -> System.out.println("(" + pair[0] + ", " + pair[1] + ")"));

    }
}
