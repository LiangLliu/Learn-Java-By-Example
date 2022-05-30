package com.lianglliu.java8.collectors.usage;

import com.lianglliu.java8.collectors.model.CaloricLevel;
import com.lianglliu.java8.collectors.model.Dish;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分组
 */
public class GroupingUsage {

    public static void main(String[] args) {

        // groupingBy: 按照字段分组
        testGroupingBy();

        // groupingBy、mapping ：分组后，对分组的内容做映射
        groupingByAndMapping();

        // groupingBy、flatMapping ：分组后，对分组的内容做映射
        groupingByAndFlatMapping();

        // groupingBy、filtering ：分组后，对分组的内容做过滤
        groupingByAndFiltering();

        // groupingBy、set alias : 分组后设置组名
        groupingAndSetAlias();

        // groupingBy、counting : 分组统计
        groupingAndCount();

        // grouping、Reducing ：分组统计投做计算
        groupingAndReducing();

        // grouping、Statistics ：分组统计
        groupingAndStatistics();

        // groupingBy、mapping、set ：分组后，对分组的内容做映射为set
        groupingAndCollectorsSet();

        // groupingByConcurrent : 线程安全的分组
        testGroupingByConcurrent();
    }

    /**
     * 按照字段分组
     */
    private static void testGroupingBy() {
        System.out.println("-----------testGroupingBy----------");

        Map<Dish.Type, List<Dish>> groupByDishType = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println(groupByDishType);

        Map<String, List<Dish>> groupByName = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getName));
        System.out.println(groupByName);
    }

    /**
     * groupingBy、mapping ：分组后，对分组的内容做映射
     */
    private static void groupingByAndMapping() {
        System.out.println("-----------groupingByAndMapping----------");

        // 按照 type 分组，并将每个元素的name拿出来
        Map<Dish.Type, List<String>> groupDishNames = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                                Collectors.mapping(Dish::getName, Collectors.toList())
                        )
                );
        System.out.println(groupDishNames);
    }

    /**
     * groupingBy、flatMapping ：分组后，对分组的内容做映射
     */
    private static void groupingByAndFlatMapping() {
        System.out.println("-----------groupingByAndFlatMapping----------");
        Map<Dish.Type, Set<String>> groupDishTagsByType = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                                Collectors.flatMapping(dish -> Dish.dishTags.get(dish.getName()).stream(),
                                        Collectors.toSet())
                        )
                );
        System.out.println(groupDishTagsByType);
    }

    /**
     * groupingBy、filtering ：分组后，对分组的内容做过滤
     */
    private static void groupingByAndFiltering() {
        System.out.println("-----------groupingByAndFiltering----------");

        Map<Dish.Type, List<Dish>> groupCaloricDishesByType = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                                Collectors.filtering(dish -> dish.getCalories() > 500,
                                        Collectors.toList())
                        )
                );
        System.out.println(groupCaloricDishesByType);
    }

    /**
     * grouping、set alias : 分组后设置组名
     */
    private static void groupingAndSetAlias() {
        System.out.println("-----------groupingAndSetAlias----------");
        Map<CaloricLevel, List<Dish>> groupDishesByCaloricLevel = Dish.menu.stream()
                .collect(Collectors.groupingBy(
                                dish -> {
                                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                    else return CaloricLevel.FAT;
                                }
                        )
                );
        System.out.println(groupDishesByCaloricLevel);

        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishedByTypeAndCaloricLevel = Dish.menu.stream()
                .collect(
                        Collectors.groupingBy(Dish::getType,
                                Collectors.groupingBy((Dish dish) -> {
                                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                            else return CaloricLevel.FAT;
                                        }
                                )
                        )
                );
        System.out.println(groupDishedByTypeAndCaloricLevel);
    }

    /**
     * groupingBy、counting : 分组统计
     */
    private static void groupingAndCount() {
        System.out.println("-----------groupingAndCount----------");
        Map<Dish.Type, Long> countDishesInGroups = Dish.menu
                .stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        System.out.println(countDishesInGroups);
    }

    /**
     * grouping、Reducing ：分组统计投做计算
     */
    private static void groupingAndReducing() {
        System.out.println("-----------groupingAndReducing----------");
        Map<Dish.Type, Optional<Dish>> mostCaloricDishesByType = Dish.menu
                .stream()
                .collect(Collectors.groupingBy(Dish::getType,
                                Collectors.reducing((Dish d1, Dish d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)
                        )
                );
        System.out.println(mostCaloricDishesByType);

        Map<Dish.Type, Dish> mostCaloricDishesByTypeWithoutOprionals = Dish.menu
                .stream()
                .collect(Collectors.groupingBy(Dish::getType,
                                Collectors.collectingAndThen(
                                        Collectors.reducing((Dish d1, Dish d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2),
                                        Optional::get)
                        )
                );
        // 等价于以下代码
        Dish.menu.stream()
                .collect(Collectors.toMap(Dish::getType,
                        Function.identity(), (Dish d1, Dish d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)
                );
        System.out.println(mostCaloricDishesByTypeWithoutOprionals);
    }

    /**
     * grouping、Statistics ：分组统计
     */
    private static void groupingAndStatistics() {
        System.out.println("-----------groupingAndStatistics----------");
        Map<Dish.Type, Integer> sumCaloriesByType = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                                Collectors.summingInt(Dish::getCalories)
                        )
                );
        System.out.println(sumCaloriesByType);

        Map<Dish.Type, IntSummaryStatistics> summaryCaloriesByType = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                                Collectors.summarizingInt(Dish::getCalories)
                        )
                );
        System.out.println(summaryCaloriesByType);
    }

    /**
     * grouping、mapping ：
     */
    private static void groupingAndCollectorsSet() {
        System.out.println("-----------groupingAndCollectorsSet----------");
        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                                Collectors.mapping(dish -> {
                                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                            else return CaloricLevel.FAT;
                                        },
                                        Collectors.toSet()
                                )
                        )
                );
        System.out.println(caloricLevelsByType);
    }

    /**
     * groupingByConcurrent : 线程安全的分组
     */
    private static void testGroupingByConcurrent() {
        ConcurrentSkipListMap<Dish.Type, Double> skipListMap = Dish.menu.stream()
                .collect(Collectors.groupingByConcurrent(Dish::getType,
                                ConcurrentSkipListMap::new,
                                Collectors.averagingDouble(Dish::getCalories)
                        )
                );
        System.out.println(skipListMap);
    }
}
