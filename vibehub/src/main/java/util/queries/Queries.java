package main.java.util.queries;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class Queries {

    public static <T> Iterable<T> filter(Iterable<T> data, Predicate<T> p) {
        return () -> new IteratorFilter(data.iterator(), p);
    }

    public static <T, R> Iterable<R> map(Iterable<T> data, Function<T, R> mapper) {
        return () -> new IteratorMap(data.iterator(), mapper);
    }

    public static <T> Iterable<T> distinct(Iterable<T> data) {
        return () -> new IteratorDistinct(data.iterator());
    }

    public static <T> Iterable<T> skip(Iterable<T> data, int nr) {
        return () -> {
            Iterator<T> iter = data.iterator();
            for (int i = 0; i < nr && iter.hasNext(); i++) iter.next();
            return iter;
        };
    }

    public static <T> int count(Iterable<T> data) {
        int size = 0;
        for (T item: data) {
            size++;
        }
        return size;
    }


    public static <T> String join(Iterable<T> src) {
        String res = "";
        for (T item: src) {
            res += item.toString();
        }
        return res;
    }
    /*
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

    public static <T> Iterable<T> iterate(T from, T to, UnaryOperator<T> inc) {
        return () -> new Iterator<T>() {
            T next = from;
            boolean finish = false;

            @Override
            public boolean hasNext() {
                return !finish;
            }

            @Override
            public T next() {
                if(finish) throw new IndexOutOfBoundsException("Iteration overlaps the value of to: " + to);
                if(next.equals(to))
                    finish = true; // The to bound is inclusive. Finishes on next step.
                T curr = next;
                next = inc.apply(curr);
                return curr;
            }
        };
    }

    public static <T> boolean containsAll(Collection<T> col, Iterable<T> data) {
        for (T item:data) {
            if(!col.contains(item))
                return false;
        }
        return true;
    }
    */
}
