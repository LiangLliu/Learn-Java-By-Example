package com.lianglliu.java8.parallel.spliterator;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * 底层使用 ForkJoin 去实现， 使用stream被隐藏掉了
 */
public class SpliteratorUsage {

    private static final String words = "We do not admire the man of timid peace. We admire the man who embodies " +
            "\n" +
            "victorious efforts, the man who never wrongs his neighbor, who is prompt " +
            "\n" +
            "to help a friend, but who has those virile qualities necessary to win in " +
            "\n" +
            "the stern strife of actual life. It is hard to fail, but it is worse " +
            "\n" +
            "never to have tried to succeed. In this life we get nothing save by " +
            "\n" +
            "effort. Freedom from effort in the present merely means that there has " +
            "\n" +
            "been effort stored up in the past. A man can be freed from the necessity " +
            "\n" +
            "of work only by the fact that he or his fathers before him have worked to " +
            "\n" +
            "good purpose. If the freedom thus purchased is used aright, and the man " +
            "\n" +
            "still does actual work, though of a different kind, whether as a writer " +
            "\n" +
            "or a general, whether in the field of politics or in the field of " +
            "\n" +
            "exploration and adventure, he shows he deserves his good fortune.";

    public static void main(String[] args) {

        intStreamSpliterator();

        // 自定义 spliterator
        customizeSpliterator();
    }

    public static void intStreamSpliterator() {
        System.out.println("-------------------intStreamSpliterator-------------------------");
        IntStream intStream = IntStream.rangeClosed(0, 10);
        Spliterator.OfInt spliterator = intStream.spliterator();
        Consumer<Integer> consumer = System.out::println;
        spliterator.forEachRemaining(consumer);
    }

    public static void customizeSpliterator() {
        System.out.println("-------------------customizeSpliterator-------------------------");
        MySpliterator mySpliterator = new MySpliterator(words);
        long count = mySpliterator.stream().count();
        System.out.println(count);

        mySpliterator.stream().forEach(System.out::println);
    }

}
