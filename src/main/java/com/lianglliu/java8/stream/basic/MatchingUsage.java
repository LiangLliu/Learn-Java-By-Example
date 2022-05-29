package com.lianglliu.java8.stream.basic;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MatchingUsage {

    public static void main(String[] args) {

        List<Integer> numbers = Stream.iterate(1, i -> i + 1)
                .limit(10)
                .collect(Collectors.toList());
        System.out.println("data resource : \n" + numbers);

        // allMatch
        System.out.println("=================allMatch==================");
        System.out.println("all > 0 : " + numbers.stream().allMatch(i -> i > 0));
        System.out.println("all > 10 : " + numbers.stream().allMatch(i -> i > 10));

        // anyMatch
        System.out.println("=================anyMatch==================");
        System.out.println("one > 6 : " + numbers.stream().anyMatch(i -> i > 6));
        System.out.println("one > 10 : " + numbers.stream().allMatch(i -> i > 10));

        // noneMatch
        System.out.println("=================noneMatch==================");
        System.out.println("no number > 6 : " + numbers.stream().noneMatch(i -> i > 6));
        System.out.println("no number > 10 : " + numbers.stream().noneMatch(i -> i > 10));
    }
}
