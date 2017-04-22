package test.util;

import java.util.function.Function;

public interface ICounter<T,R> extends Function<T,R> {
    int getCount();
}
