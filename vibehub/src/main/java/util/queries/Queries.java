package main.java.util.queries;

import java.io.UncheckedIOException;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;

public class Queries {

    public static <T, R> Iterable<R> map(Iterable<T> data, Function<T, R> mapper) {
        return () -> new IteratorMap(data.iterator(), mapper);
    }

    public static <T> String join(Iterable<T> src) throws UncheckedIOException {
        String res = "";
        for (T item: src) {
            res += item.toString();
        }
        return res;
    }

    public static <T> Iterable<T> peek(Iterable<T> src, Consumer<T> action) {
        return () -> new Iterator<T>() {
            final Iterator<T> iter = src.iterator();
            @Override
            public boolean hasNext() {
                return iter.hasNext();
            }

            @Override
            public T next() {
                T curr = iter.next();
                action.accept(curr);
                return curr;
            }
        };
    }
}
