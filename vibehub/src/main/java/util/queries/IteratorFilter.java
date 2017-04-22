package main.java.util.queries;

import java.util.Iterator;
import java.util.function.Predicate;

public class IteratorFilter<T> implements Iterator<T> {
    final Iterator<T> src;
    final Predicate<T> p;
    private T nextItem;

    public IteratorFilter(Iterator<T> src, Predicate<T> p) {
        this.src = src;
        this.p = p;
        this.nextItem = moveNext();
    }

    private T moveNext() {
        T item = null;
        while(src.hasNext()) {
            item = src.next();
            if(p.test(item))
                return item;
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return nextItem != null;
    }

    @Override
    public T next() {
        T curr = nextItem;
        nextItem = moveNext();
        return curr;
    }
}
