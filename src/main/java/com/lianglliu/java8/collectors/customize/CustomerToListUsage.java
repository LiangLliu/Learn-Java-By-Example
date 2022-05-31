package com.lianglliu.java8.collectors.customize;

import java.util.List;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomerToListUsage {

    private static final Collector<String, List<String>, List<String>> CUSTOMER_TO_LIST_COLLECTOR = new ToListCollector<>();

    private static final Random random = new Random();

    public static void main(String[] args) {

        var list = generateData(100);

        List<String> result = list.stream()
                .filter(it -> it.length() > 5)
                .collect(CUSTOMER_TO_LIST_COLLECTOR);
        System.out.println(result);
    }

    private static List<String> generateData(int number) {
        if (number < 1) {
            return List.of();
        }

        return Stream.iterate(0, i -> i + 1)
                .limit(number)
                .map(it -> generateString())
                .collect(Collectors.toList());
    }

    private static String generateString() {
        return createRandomStr(random.nextInt(10));
    }

    private static String createRandomStr(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            stringBuffer.append(str.charAt(number));
        }
        return stringBuffer.toString();
    }

}
