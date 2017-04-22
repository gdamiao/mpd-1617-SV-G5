package test.util;

import java.util.function.Function;

public class Countify {

    static class Counter<T, R> implements ICounter<T, R> {
        private int count=0;
        private final Function<T, R> inner;

        public Counter(Function<T, R> inner) {
            this.inner = inner;
        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public R apply(T arg) {
            count++;
            return inner.apply(arg);
        }
    }

    public static <T,R> ICounter of(Function<T,R> inner) {
        return new Counter<>(inner);
    }
}
