package com.lianglliu.java8.advanced.optional;

import com.lianglliu.java8.advanced.optional.model.Car;
import com.lianglliu.java8.advanced.optional.model.Insurance;
import com.lianglliu.java8.advanced.optional.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class OptionalUsage {
    public static void main(String[] args) {

    }

    // 空指针异常
    public static String getCarInsuranceName(Person person) {
        return person.getCar().get().getInsurance().get().getName();
    }

    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    public Set<String> getCarInsuranceNames(List<Person> persons) {
        return persons.stream()
                .map(Person::getCar)
                .map(optCar -> optCar.flatMap(Car::getInsurance))
                .map(optInsurance -> optInsurance.map(Insurance::getName))
                .flatMap(Optional::stream)
                .collect(Collectors.toSet());
    }
}
