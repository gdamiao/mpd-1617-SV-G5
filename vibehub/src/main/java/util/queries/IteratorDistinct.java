package main.java.util.queries;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorDistinct<T> implements Iterator<T> {
    private Iterator<T> data;
    private ArrayList<T> distinctData;
    private T nextItem;

    public IteratorDistinct(Iterator<T> data){
        this.data=data;
        distinctData = new ArrayList<>();
        nextItem = getNext();
    }

    private T getNext() {
        T item;
        while(data.hasNext()) {
            item = data.next();
            if(!distinctData.contains(item)) {
                distinctData.add(item);
                return item;
            }
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return nextItem != null;
    }

    @Override
    public T next() {
        T currentItem = nextItem;
        nextItem = getNext();
        return currentItem;
    }
}
