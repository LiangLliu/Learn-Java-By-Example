package com.lianglliu.java8.collectors.customize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 自定义聚合器
 *
 * @param <T> Stream元素的类型
 *            List<T> -> <A> : supplier的类型
 *            List<T> -> <R> : 返回的类型
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    private void log(String message) {
        System.out.println(Thread.currentThread().getName() + " " + message);
    }

    /**
     * 收集的容器
     */
    @Override
    public Supplier<List<T>> supplier() {
        log("ToListCollector.supplier");
        return ArrayList::new;
    }

    /**
     * 消费
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        log("ToListCollector.accumulator");
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        log("oListCollector.combiner");
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        log("ToListCollector.finisher");
        // list -> list
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        log("ToListCollector.characteristics");
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
    }
}
