package com.lianglliu.java8.advanced.optional.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class Person {

    private Optional<Car> car;

}
