package com.lianglliu.java8.parallel.spliterator;

import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class MySpliterator {
    private final String[] data;

    public MySpliterator(String text) {
        Objects.requireNonNull(text, "The parameter can not be null.");
        this.data = text.split("\n");
    }

    public Stream<String> stream() {
        return StreamSupport.stream(new MySpliteratorImpl(), false);
    }

    public Stream<String> parallelStream() {
        return StreamSupport.stream(new MySpliteratorImpl(), true);
    }


    private class MySpliteratorImpl implements Spliterator<String> {

        private int start;
        private int end;

        private MySpliteratorImpl() {
            this.start = 0;
            this.end = MySpliterator.this.data.length;
        }

        public MySpliteratorImpl(int start, int end) {
            this.start = start;
            this.end = end;
        }

        /**
         * 试图从 stream 中拿元素消费
         *
         * @param action The action
         */
        @Override
        public boolean tryAdvance(Consumer<? super String> action) {
            if (start < end) {
                action.accept(MySpliterator.this.data[start++]);
                return true;
            }

            return false;
        }

        @Override
        public Spliterator<String> trySplit() {
            int mid = (start - end) / 2;
            if (mid <= 1) {
                return null;
            }
            int left = start;
            int right = start + mid;

            start = start + mid + 1;

            return new MySpliteratorImpl(left, right);
        }

        @Override
        public long estimateSize() {
            return end - start;
        }

        @Override
        public long getExactSizeIfKnown() {
            return estimateSize();
        }

        @Override
        public int characteristics() {
            return IMMUTABLE | SIZED | SUBSIZED;
        }
    }

}
