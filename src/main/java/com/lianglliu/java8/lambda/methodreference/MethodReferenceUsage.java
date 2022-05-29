package com.lianglliu.java8.lambda.methodreference;

import com.lianglliu.java8.lambda.parametricbehavior.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class MethodReferenceUsage {

    private static final List<Apple> apples = Arrays.asList(
            Apple.builder().color("green").weight(150).build(),
            Apple.builder().color("green").weight(300).build(),
            Apple.builder().color("red").weight(150).build(),
            Apple.builder().color("red").weight(500).build(),
            Apple.builder().color("black").weight(300).build()
    );

    private static <T> void useConsumer(T t, Consumer<T> consumer) {
        consumer.accept(t);
        consumer.accept(t);
    }

    public static void main(String[] args) {
        Consumer<String> consumer = (s) -> System.out.println(s);
        useConsumer("aaa", consumer);

        useConsumer("bbb", System.out::println);

        System.out.println("排序前");
        System.out.println(apples);

        apples.sort(Comparator.comparing(Apple::getColor));
        System.out.println("排序后");
        System.out.println(apples);
    }
}
