package com.lianglliu.java8.lambda.parametricbehavior;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过参数行为化例子，来演进 lambda
 */
public class FilterApple {

    private static final List<Apple> apples = List.of(
            Apple.builder().color("green").weight(150).build(),
            Apple.builder().color("green").weight(300).build(),
            Apple.builder().color("red").weight(150).build(),
            Apple.builder().color("red").weight(500).build(),
            Apple.builder().color("black").weight(300).build()
    );

    public static List<Apple> filterGreenApple(List<Apple> apples) {

        ArrayList<Apple> list = new ArrayList<>();

        for (Apple apple : apples) {
            if ("green".equals(apple.getColor())) {
                list.add(apple);
            }
        }
        return list;
    }

    // 根据颜色筛选
    public static List<Apple> filterApplesByColor(List<Apple> apples, String color) {

        ArrayList<Apple> list = new ArrayList<>();

        for (Apple apple : apples) {
            if (color.equals(apple.getColor())) {
                list.add(apple);
            }
        }
        return list;
    }

    // 根据体重
    public static List<Apple> filterApplesByColor(List<Apple> apples, Integer weight) {

        ArrayList<Apple> list = new ArrayList<>();

        for (Apple apple : apples) {
            if (apple.getWeight() > weight) {
                list.add(apple);
            }
        }
        return list;
    }

    /*

     java 8 中，如果一个接口只有一个方法，那么默认就是 Function的

     */
    // 策略模式
    @FunctionalInterface
    interface AppleFilter {
        boolean filter(Apple apple);
    }

    private static List<Apple> findApple(List<Apple> apples, AppleFilter appleFilter) {
        ArrayList<Apple> list = new ArrayList<>();

        for (Apple apple : apples) {
            if (appleFilter.filter(apple)) {
                list.add(apple);
            }
        }
        return list;
    }

    static class AppleFilterByWeightIs150AndColorIsRed implements AppleFilter {

        @Override
        public boolean filter(Apple apple) {
            return apple.getWeight().equals(150) && apple.getColor().equals("red");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        assert apples.size() == 2 : "error";

        System.out.println(filterGreenApple(apples));
        System.out.println(filterApplesByColor(apples, "red"));

        System.out.println(findApple(apples, new AppleFilterByWeightIs150AndColorIsRed()));

        // 匿名内部类
        System.out.println(findApple(apples, new AppleFilter() {

            @Override
            public boolean filter(Apple apple) {
                return apple.getWeight().equals(300) && apple.getColor().equals("green");
            }
        }));

        // lambda
        System.out.println("===================lambda===================");
        System.out.println(findApple(apples, (Apple apple) -> apple.getWeight().equals(300) && apple.getColor().equals("green")));


        System.out.println("===================Runnable===================");
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();

        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();

        Thread.currentThread().join();
    }
}
