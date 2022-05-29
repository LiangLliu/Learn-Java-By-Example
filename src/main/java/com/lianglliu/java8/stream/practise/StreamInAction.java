package com.lianglliu.java8.stream.practise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamInAction {

    public static void main(String[] args) {
        var raoul = new Trader("Raoul", "Cambridge");
        var mario = new Trader("Mario", "Milan");
        var alan = new Trader("Alan", "Cambridge");
        var brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2020, 300),
                new Transaction(brian, 2021, 700),
                new Transaction(raoul, 2022, 1000),
                new Transaction(raoul, 2021, 400),
                new Transaction(mario, 2021, 710),
                new Transaction(mario, 2022, 700),
                new Transaction(alan, 2022, 950),
                new Transaction(alan, 2021, 700)
        );

        // 找出2021年发生的所有交易，并按照交易额排序
        List<Transaction> transactions2021 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2021)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(transactions2021);

        // 交易员都在那些不同的城市工作过？
        transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);

        // 查找所有来自于剑桥的交易员， 并按照姓名排序
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(it -> it.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);

        // 返回所有交易员的姓名字符串，并按照姓名排序
        String traderStr = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);
        System.out.println(traderStr);

        // 有没有交易员在米兰工作的？
        boolean milanBased = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(milanBased);

        // 打印生活在剑桥的交易员的所有交易额
        System.out.println("打印生活在剑桥的交易员的所有交易额");
        transactions.stream()
                .filter(it -> "Cambridge".equals(it.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        // 所有交易员中，最高的交易额是多少？
        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .ifPresent(System.out::println);

        // 找出交易额最小的交易
        int minValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(0, Integer::min);
        System.out.println(minValue);
    }
}
