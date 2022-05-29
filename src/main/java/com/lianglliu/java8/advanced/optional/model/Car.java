package com.lianglliu.java8.advanced.optional.model;

import java.util.Optional;

public class Car {

    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}