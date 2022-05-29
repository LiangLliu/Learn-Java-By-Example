package com.lianglliu.java8.stream.basic;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuildingStream {

    private static Stream<String> createStreamFromCollection() {
        List<String> list = Arrays.asList("hello", "world", "stream");
        return list.stream();
    }

    private static Stream<String> createStreamFromValues() {
        return Stream.of("create", "stream", "with", "values");
    }

    public static void main(String[] args) throws IOException {
        createStreamFromCollection().forEach(System.out::println);
        createStreamFromValues().forEach(System.out::println);

        // Stream empty
        Stream<String> empty = Stream.empty();

        // arrays
        int[] ints = {1, 3, 4, 6, 9};
        System.out.println(Arrays.stream(ints).sum());

        // Stream.iterate
        System.out.println("\nStream.iterate");
        Stream.iterate(0, n -> n + 1)
                .limit(10)
                .forEach(it -> System.out.print(it + " "));

        // fibonnaci with iterate
        System.out.println("\nfibonnaci with iterate");
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .forEach(t -> System.out.println("(" + t[0] + ", " + t[1] + ")"));

        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(it -> System.out.print(it + " "));

        // random stream of doubles with Stream.generate
        System.out.println("\nrandom stream of doubles with Stream.generate");
        Stream.generate(Math::random)
                .limit(10)
                .forEach(it -> System.out.print(it + " "));

        // stream of 1s with Stream.generate
        System.out.println("\nstream of 1s with Stream.generate");
        IntStream.generate(() -> 1)
                .limit(5)
                .forEach(it -> System.out.print(it + " "));

        // IntSupplier
        System.out.println("\nIntSupplier");
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            public int getAsInt() {
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return this.previous;
            }
        };
        IntStream.generate(fib).limit(10).forEach(it -> System.out.print(it + " "));

    }
}
