package com.lianglliu.java8.lambda.usage;

import com.lianglliu.java8.lambda.parametricbehavior.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ComparatorUsage {

    private static final List<Apple> apples = Arrays.asList(
            Apple.builder().color("green").weight(150).build(),
            Apple.builder().color("green").weight(300).build(),
            Apple.builder().color("red").weight(150).build(),
            Apple.builder().color("red").weight(500).build(),
            Apple.builder().color("black").weight(300).build()
    );

    public static void main(String[] args) {

        Comparator<Apple> comparatorByColor = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getColor().compareTo(o2.getColor());
            }
        };
        apples.sort(comparatorByColor);

        Comparator<Apple> byColor = (Apple o1, Apple o2) -> o1.getColor().compareTo(o2.getColor());
        apples.sort(byColor);
        apples.sort(Comparator.comparing(Apple::getColor));

        // 比较器链
        Comparator<Apple> comparatorColor = Comparator.comparing(Apple::getColor).reversed();
        Comparator<Apple> comparatorWeight = Comparator.comparing(Apple::getWeight);
        Comparator<Apple> comparator = comparatorColor.thenComparing(comparatorWeight);
        apples.sort(comparator);

        // =>
        apples.sort(Comparator.comparing(Apple::getColor)
                .reversed()
                .thenComparing(Apple::getWeight));

    }
}
