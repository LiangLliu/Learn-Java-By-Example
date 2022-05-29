package com.lianglliu.java8.lambda.usage;

import com.lianglliu.java8.lambda.parametricbehavior.Apple;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ConsumerUsage {

    private static final List<Apple> apples = List.of(
            Apple.builder().color("green").weight(150).build(),
            Apple.builder().color("green").weight(300).build(),
            Apple.builder().color("red").weight(150).build(),
            Apple.builder().color("red").weight(500).build(),
            Apple.builder().color("black").weight(300).build()
    );

    private static void consumerApples(List<Apple> apples, Consumer<Apple> appleConsumer) {
        for (Apple apple : apples) {
            appleConsumer.accept(apple);
        }
    }

    private static void consumerApples(List<Apple> apples, String s, BiConsumer<Apple, String> biConsumer) {
        for (Apple apple : apples) {
            biConsumer.accept(apple, s);
        }
    }

    public static void main(String[] args) {
        System.out.println("==================consumerApples=====================");
        consumerApples(apples, System.out::println);

        System.out.println("==================BiConsumerApples=====================");
        consumerApples(apples, "xxx", (apple, s) -> System.out.println(apple.getColor() + "---" + s));
    }
}
