package com.tip.functional;

public class Fibonacci implements InfiniteIterator<Integer> {

    private int prev;
    private int cur;

    public Fibonacci() {
        prev = 0;
        cur = 1;
    }

    @Override
    public Integer next() {
        int temp = prev;
        prev = cur;
        cur = temp + cur;
        return prev;
    }
    // TODO: 채우기
}
