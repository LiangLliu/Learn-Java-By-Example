package com.lianglliu.java8.stream.basic;

import com.lianglliu.java8.stream.model.Dish;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamBasicUsage {

    public static void main(String[] args) {
        var dishes = Dish.MENU;

        // collections
        System.out.println("===============================collections================================");
        var dishNameByCollections = getLowCaloricDishesNamesByCollections(dishes);
        System.out.println(dishNameByCollections);

        System.out.println("===============================stream================================");
        System.out.println(getLowCaloricDishesNamesByStream(dishes));

        dishes.forEach(System.out::println);

        dishes.stream()
                .filter(dish -> dish.getCalories() < 400)
                .sorted(Comparator.comparingInt(Dish::getCalories))
                .map(Dish::getName)
                .limit(3)
                .forEach(System.out::println);
    }

    private static List<String> getLowCaloricDishesNamesByCollections(List<Dish> dishes) {

        List<Dish> lowCaloricDishes = new ArrayList<>();
        for(Dish d: dishes){
            if(d.getCalories() < 400){
                lowCaloricDishes.add(d);
            }
        }

        lowCaloricDishes.sort(Comparator.comparingInt(Dish::getCalories));

        var dishNames = new ArrayList<String>();
        for (Dish dish : lowCaloricDishes) {
            dishNames.add(dish.getName());
        }
        return dishNames;
    }

    private static List<String> getLowCaloricDishesNamesByStream(List<Dish> dishes) {
        return dishes.stream()
                .filter(dish -> dish.getCalories() < 400)
                .sorted(Comparator.comparingInt(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }
}
