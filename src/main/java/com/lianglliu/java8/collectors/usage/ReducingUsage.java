package com.lianglliu.java8.collectors.usage;

import com.lianglliu.java8.collectors.model.Dish;

import java.util.stream.Collectors;

public class ReducingUsage {

    public static void main(String[] args) {
        var result = Dish.menu.stream()
                .collect(Collectors.reducing(0,
                                Dish::getCalories,
                                (a, b) -> a + b
                        )
                );
        System.out.println(result);

        // 等价于
        Dish.menu.stream().collect(Collectors.reducing(0,
                        Dish::getCalories,
                        Integer::sum
                )
        );

        // 等价于
        Integer reduce = Dish.menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);

        // 等价于
        int sum = Dish.menu.stream().mapToInt(Dish::getCalories).sum();
    }
}
