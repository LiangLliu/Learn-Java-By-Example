package com.lianglliu.java8.lambda.usage;

import com.lianglliu.java8.lambda.parametricbehavior.Apple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class PredicateUsage {

    private static final List<Apple> apples = List.of(
            Apple.builder().color("green").weight(150).build(),
            Apple.builder().color("green").weight(300).build(),
            Apple.builder().color("red").weight(150).build(),
            Apple.builder().color("red").weight(500).build(),
            Apple.builder().color("black").weight(300).build()
    );

    private static List<Apple> filterApple(List<Apple> apples, Predicate<Apple> applePredicate) {

        var list = new ArrayList<Apple>();

        for (Apple apple : apples) {
            if (applePredicate.test(apple)) {
                list.add(apple);
            }
        }
        return list;
    }

    private static List<Apple> filterAppleIntPredicate(List<Apple> apples, IntPredicate predicate) {

        var list = new ArrayList<Apple>();

        for (Apple apple : apples) {
            if (predicate.test(apple.getWeight())) {
                list.add(apple);
            }
        }
        return list;
    }

    private static List<Apple> filterAppleBiPredicate(List<Apple> apples, BiPredicate<String, Integer> biPredicate) {

        var list = new ArrayList<Apple>();

        for (Apple apple : apples) {
            if (biPredicate.test(apple.getColor(), apple.getWeight())) {
                list.add(apple);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(filterApple(apples, apple -> apple.getColor().equals("green")));
        System.out.println(filterAppleIntPredicate(apples, wight -> wight >= 300));
        System.out.println(filterAppleBiPredicate(apples, (color, weight) -> color.equals("green") && weight >= 300));

        // 谓词复合
        Predicate<Apple> redApple = apple -> apple.getColor().equals("red");
        Predicate<Apple> notRedApple = redApple.negate();
        Predicate<Apple> redAndHeavyApple = redApple.and(a -> a.getWeight() > 130);

        System.out.println(filterApple(apples, notRedApple));
        System.out.println(filterApple(apples, redAndHeavyApple));

    }
}
