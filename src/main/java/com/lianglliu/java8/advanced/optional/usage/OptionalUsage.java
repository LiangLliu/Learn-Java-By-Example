package com.lianglliu.java8.advanced.optional.usage;

import com.lianglliu.java8.advanced.optional.model.Insurance;

import java.util.Optional;

public class OptionalUsage {

    public static void main(String[] args) {
        // empty
        Optional<Insurance> empty = Optional.empty();

        // of
        Optional<Insurance> insurance = Optional.of(new Insurance());

        // ofNullable
        Optional<Insurance> insurance1 = Optional.ofNullable(null);

        boolean empty1 = insurance1.isEmpty();

        // Optional.get
        Insurance insurance2 = insurance.get();

        // Optional.orElse
        Insurance insurance3 = insurance1.orElse(new Insurance());

        System.out.println(Optional.of(5).or(() -> Optional.of(4)));

        Optional<Integer> opt1 = Optional.of(5);
        Optional<Integer> opt2 = opt1.or(() -> Optional.of(4));


        Optional<Integer> integerOptional = Optional.of(5);

        Optional<Integer> integer = integerOptional.filter(it -> it < 5);
        System.out.println(integer);

        Optional<Double> doubleOptional = integerOptional.map(Double::valueOf);
        System.out.println(doubleOptional);

    }



}
