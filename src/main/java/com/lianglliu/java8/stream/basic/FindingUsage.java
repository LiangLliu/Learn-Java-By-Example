package com.lianglliu.java8.stream.basic;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindingUsage {

    public static void main(String[] args) {
        List<Integer> numbers = Stream.iterate(1, i -> i + 1)
                .limit(10)
                .collect(Collectors.toList());
        System.out.println("data resource : \n" + numbers);

        // findAny
        Optional<Integer> findAnyIsEven = numbers.stream()
                .filter(it -> it % 2 == 0)
                .findAny();

        assert findAnyIsEven.isPresent() : "No Number is Even";
        System.out.println(findAnyIsEven.get());

        // findFirst
        Optional<Integer> findFirstEven = numbers.stream()
                .filter(it -> it % 2 == 0)
                .findFirst();

        assert findFirstEven.isPresent() : "No Number is Even";
        System.out.println(findFirstEven.get());
    }
}
