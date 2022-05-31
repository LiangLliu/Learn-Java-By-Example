# Java8 In Action

## [Lambda](./lambda)

* [行为参数化推到lambda](lambda/parametricbehavior/FilterApple.java)
* [常见Lambda的定义](lambda/expression/ExpressionUsage.java)
* [方法引用样例](lambda/methodreference/MethodReferenceUsage.java)
* [函数式样例](lambda/usage/FunctionUsage.java)
* [函数式接口定义](lambda/usage/DefFuncInterFaceUsage.java)
* [Lambda使用样例](lambda/usage/LambdaUsage.java)
* [Predicate 断言样例](lambda/usage/PredicateUsage.java)
* [Comparator 比较器样例](lambda/usage/ComparatorUsage.java)
* [Consumer 样例](lambda/usage/ConsumerUsage.java)
* [Supplier 样例](lambda/usage/SupplierUsage.java)

## [Stream](./stream)

### [Stream基础](./stream/basic)

* [基础样例](stream/basic/StreamBasicUsage.java)
* [创建Stream](stream/basic/BuildingStream.java)
* [Filter Stream过滤](stream/basic/FilteringUsage.java)
* [Map Stream映射](stream/basic/MappingUsage.java)
* [Match Stream匹配 ](stream/basic/MatchingUsage.java)
* [Reduce Stream数据聚合](stream/basic/MatchingUsage.java)
* [Find Stream中查找](stream/basic/FindingUsage.java)
* [基本类型Stream的使用](stream/basic/NumericStreamsUsage.java)
* [Java Stream 实战](./stream/practise)

### [collectors ：数据聚合](./collectors)

* [Group方式和for方式分组区别](./collectors/usage/GroupingTransactions.java)
* [Stream Summarizing 数据统计、计算](./collectors/usage/SummarizingUsage.java)
    * counting 统计数量
    * averagingDouble 计算平均值
    * reduce : 计算
    * summingDouble 统计求和
    * summarizingDouble 汇总统计
    * collectingAndThen: 计算结果，返回其他
    * joining: 将元素连在一块
* [Stream Grouping 分组](./collectors/usage/GroupingUsage.java)
    * groupingBy: 按照字段分组
    * groupingBy、mapping ：分组后，对分组的内容做映射
    * groupingBy、flatMapping ：分组后，对分组的内容做映射
    * groupingBy、filtering ：分组后，对分组的内容做过滤
    * groupingBy、set alias : 分组后设置组名
    * groupingBy、counting : 分组统计
    * groupingBy、reducing ：分组统计投做计算
    * groupingBy、statistics ：分组统计
    * groupingBy、mapping、set ：分组后，对分组的内容做映射为set
    * groupingByConcurrent : 线程安全的分组
* [Stream Partitioning 谓词分割](./collectors/usage/PartitioningUsage.java)
    * partitioningBy : 根据谓词对输入元素进行分区
    * partitioningBy、groupingBy
    * partitioningBy、collectingAndThen
* [Stream reduce 计算](./collectors/usage/ReducingUsage.java)
* 自定义Collector
    * [ToListCollector](./collectors/customize/ToListCollector.java)

### [并行处理](./parallel)

* [并行性能对比](./parallel/usage/ParallelProcessing.java)
* [Fork/Join 样例](./parallel/forkjoin/ForkJoinPoolUsage.java)
* [自定义 Spliterator 切片 样例](./parallel/spliterator/SpliteratorUsage.java)

## [Java8高级用法](./advanced)

### [Optional](./advanced/optional)

* [获取对象值空指针处理](./advanced/optional/usage/OptionalPersonUsage.java)
* [Optional实例](./advanced/optional/usage/OptionalUsage.java)
* [OptionalInAction](./advanced/optional/usage/OptionalInAction.java)

