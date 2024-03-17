package com.tip.functional;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Experiments<T extends Number> implements Iterator<T> {


    Iterator<T> integerInfiniteIterator;
    String herbAvailabilities;
    String binomialDistribution;

    long count;
    double sum;


    public Experiments(Iterator<T> integerInfiniteIterator, String herbAvailabilities, String binomialDistribution) {
        this.integerInfiniteIterator = integerInfiniteIterator;
        this.herbAvailabilities = herbAvailabilities;
        this.binomialDistribution = binomialDistribution;
    }

    @Override
    public boolean hasNext() {
        return integerInfiniteIterator.hasNext();
    }

    @Override
    public T next() {
        count = Math.addExact(count, 1);
        T temp = integerInfiniteIterator.next();
        sum += temp.doubleValue();
        return temp;
    }

    public void report() {
        System.out.println(sum / count);
        System.out.println(herbAvailabilities);
        System.out.println(binomialDistribution);
    }
}
