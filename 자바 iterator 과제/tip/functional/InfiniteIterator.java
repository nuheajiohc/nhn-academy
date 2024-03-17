package com.tip.functional;

import java.util.Iterator;

public interface InfiniteIterator<T> extends Iterator<T> {
    // TODO: 채우기

    default boolean hasNext() {
        return true;
    }
}
