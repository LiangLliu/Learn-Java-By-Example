package com.lianglliu.java8.advanced.optional.usage;

import com.lianglliu.java8.advanced.optional.model.Car;
import com.lianglliu.java8.advanced.optional.model.Insurance;
import com.lianglliu.java8.advanced.optional.model.Person;

import java.util.Optional;

public class OptionalInAction {
    public static void main(String[] args) {
        Optional.of(getInsuranceNameByOptional(null))
                .ifPresent(System.out::println);
    }

    private static String getInsuranceNameByOptional(Person person) {
        return Optional.ofNullable(person)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }
}
