# java Collectors

* [Group方式和for方式分组区别](./usage/GroupingTransactions.java)
* [Stream Summarizing 数据统计、计算](./usage/SummarizingUsage.java)
    * counting 统计数量
    * averagingDouble 计算平均值
    * reduce : 计算
    * summingDouble 统计求和
    * summarizingDouble 汇总统计
    * collectingAndThen: 计算结果，返回其他
    * joining: 将元素连在一块
* [Stream Grouping 分组](./usage/GroupingUsage.java)
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
* [Stream Partitioning 谓词分割](./usage/PartitioningUsage.java)
    * partitioningBy : 根据谓词对输入元素进行分区
    * partitioningBy、groupingBy
    * partitioningBy、collectingAndThen
* [Stream reduce 计算](./usage/ReducingUsage.java)
* 自定义Collector
  * [ToListCollector](./customize/ToListCollector.java)
