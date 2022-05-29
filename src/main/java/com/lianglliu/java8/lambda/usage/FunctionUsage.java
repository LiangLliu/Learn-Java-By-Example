package com.lianglliu.java8.lambda.usage;

import com.lianglliu.java8.lambda.parametricbehavior.Apple;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;

public class FunctionUsage {

    private static String getColorWithApple(Apple apple, Function<Apple, String> appleStringFunction) {
        return appleStringFunction.apply(apple);
    }

    private static Apple buildApple(String color, Integer weight, BiFunction<String, Integer, Apple> function) {
        return function.apply(color, weight);
    }

    public static void main(String[] args) {
        System.out.println("====================getColorWithApple=============================");
        System.out.println(getColorWithApple(new Apple("yellow", 1500), (apple) -> apple.getColor()));

        System.out.println("====================IntFunction<Double>=============================");
        IntFunction<Double> doubleIntFunction = (i) -> Double.valueOf(i * 10);
        System.out.println(doubleIntFunction.apply(5));

        System.out.println("====================BiFunction<String, Integer, Apple>=============================");
        System.out.println(buildApple("black", 400, (c, w) -> new Apple(c, w)));
        System.out.println(buildApple("black", 400, Apple::new));

        // 函数式复合
        System.out.println("====================函数式复合=============================");
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x + 2;
        Function<Integer, Integer> h = x -> x * 3;

        Function<Integer, Integer> l = f.andThen(g).andThen(h);
        Integer result = l.apply(1);
        System.out.println(result);
        System.out.println(f.compose(g).compose(l).apply(1));

        System.out.println("====================函数式复合应用=============================");
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline = addHeader.andThen(Letter::checkSpelling)
                .andThen(Letter::addFooter);
        System.out.println(Letter.buildLetter("This is usage of labda", transformationPipeline));

    }

    static class Letter {
        private static String addHeader(String text) {
            return "Header: header, \n" + text;
        }

        private static String addFooter(String text) {
            return text + "\nFooter: footer.";
        }

        private static String checkSpelling(String text) {
            return text.replaceAll("labda", "lambda");
        }

        public static String buildLetter(String text, Function<String, String> function) {
            return function.apply(text);
        }
    }
}
