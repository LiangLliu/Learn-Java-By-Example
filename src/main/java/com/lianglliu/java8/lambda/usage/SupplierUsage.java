package com.lianglliu.java8.lambda.usage;

import com.lianglliu.java8.lambda.parametricbehavior.Apple;

import java.util.function.Supplier;

public class SupplierUsage {

    private static Apple createApple(Supplier<Apple> appleSupplier) {
       return appleSupplier.get();
    }

    public static void main(String[] args) {

        Supplier<String> stringSupplier = String::new;
        System.out.println(stringSupplier.getClass());

        System.out.println("==================================================");
        System.out.println(createApple(() -> new Apple("green", 100)));
    }
}
