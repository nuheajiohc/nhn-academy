package com.tip.functional.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tip.functional.Range;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.LongStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RangeTest {

    @Test
    @DisplayName("final class test")
    void accessModifierTest() {
        assertTrue(Modifier.isFinal(Range.class.getModifiers()));
    }

    @Test
    @DisplayName("positive method constructor test")
    void positiveRangeConstructorTest() {
        assertTrue(Range.positive(5) instanceof Range);
    }

    @Test
    @DisplayName("positive test - base case1")
    void positiveRangeTest() {
        Range range = Range.positive(10);
        assertEquals(1, range.min());
        assertEquals(10, range.max());
        assertEquals(BigInteger.TEN, range.size());
    }

    @Test
    @DisplayName("positive test - base case2")
    void positiveRangeTest2() {
        Range range = Range.positive(1);
        assertEquals(1, range.min());
        assertEquals(1, range.max());
        assertEquals(BigInteger.ONE, range.size());
    }

    @Test
    @DisplayName("positive test - base case3")
    void positiveRangeTest3() {
        Range range = Range.positive(Long.MAX_VALUE);
        assertEquals(1, range.min());
        assertEquals(Long.MAX_VALUE, range.max());
        assertEquals(BigInteger.valueOf(Long.MAX_VALUE), range.size());
    }

    @Test
    @DisplayName("positive exception test")
    void positiveExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> Range.positive(0));
        assertThrows(IllegalArgumentException.class, () -> Range.positive(-1));
        assertThrows(IllegalArgumentException.class, () -> Range.positive(Long.MIN_VALUE));
    }

    @Test
    @DisplayName("negative method constructor test")
    void negativeRangeConstructorTest() {
        assertTrue(Range.negative(-4) instanceof Range);
    }

    @Test
    @DisplayName("negative test - base case1")
    void negativeRangeTest1() {
        Range range = Range.negative(-10);
        assertEquals(-10, range.min());
        assertEquals(-1, range.max());
        assertEquals(BigInteger.TEN, range.size());
    }

    @Test
    @DisplayName("negative test - base case2")
    void negativeRangeTest2() {
        Range range = Range.negative(-1);
        assertEquals(-1, range.min());
        assertEquals(-1, range.max());
        assertEquals(BigInteger.ONE, range.size());
    }

    @Test
    @DisplayName("negative test - base case3")
    void negativeRangeTest3() {
        Range range = Range.negative(Long.MIN_VALUE);
        assertEquals(Long.MIN_VALUE, range.min());
        assertEquals(-1, range.max());
        assertEquals(BigInteger.valueOf(Long.MAX_VALUE).add(BigInteger.ONE), range.size());
    }

    @Test
    @DisplayName("negative exception test")
    void negativeExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> Range.negative(0));
        assertThrows(IllegalArgumentException.class, () -> Range.negative(1));
        assertThrows(IllegalArgumentException.class, () -> Range.negative(Long.MAX_VALUE));
    }

    @Test
    @DisplayName("open method constructor test")
    void openRangeConstructorTest() {
        assertTrue(Range.open(1, 3) instanceof Range);
    }

    @Test
    @DisplayName("negative test - base case1")
    void openRangeTest1() {
        Range range = Range.open(-1, 10);
        assertEquals(0, range.min());
        assertEquals(9, range.max());
        assertEquals(BigInteger.TEN, range.size());
    }

    @Test
    @DisplayName("negative test - base case2")
    void openRangeTest2() {
        Range range = Range.open(Long.MIN_VALUE, Long.MAX_VALUE);
        assertEquals(Long.MIN_VALUE + 1, range.min());
        assertEquals(Long.MAX_VALUE - 1, range.max());
        assertEquals(BigInteger.valueOf(Long.MAX_VALUE).subtract(BigInteger.valueOf(Long.MIN_VALUE + 1)), range.size());
    }

    @Test
    @DisplayName("negative test - base case3")
    void openRangeExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> Range.open(0, 0));
        assertThrows(IllegalArgumentException.class, () -> Range.open(-1, 0));
    }

    @Test
    @DisplayName("closed constructor test")
    void closedRangeConstructorTest() {
        assertTrue(Range.closed(1, 3) instanceof Range);
    }

    @Test
    @DisplayName("clsoed test - base case1")
    void closedRangeTest1() {
        Range range = Range.closed(-3, 6);
        assertEquals(-3, range.min());
        assertEquals(6, range.max());
        assertEquals(BigInteger.TEN, range.size());
    }

    @Test
    @DisplayName("negative test - base case2")
    void closedRangeTest2() {
        Range range = Range.closed(Long.MIN_VALUE, Long.MAX_VALUE);
        assertEquals(Long.MIN_VALUE, range.min());
        assertEquals(Long.MAX_VALUE, range.max());
        assertEquals(
                BigInteger.valueOf(Long.MAX_VALUE).subtract(BigInteger.valueOf(Long.MIN_VALUE)).add(BigInteger.ONE),
                range.size());
    }

    @Test
    @DisplayName("negative exception test")
    void closedExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> Range.closed(0, -1));
        assertThrows(IllegalArgumentException.class, () -> Range.closed(Long.MAX_VALUE, Long.MIN_VALUE));
    }

    @Test
    @DisplayName("openClosed constuructor test")
    void openClosedRangeConstructorTest() {
        assertTrue(Range.openClosed(1, 3) instanceof Range);
    }

    @Test
    @DisplayName("openClsoed test - base case1")
    void openClosedRangeTest1() {
        Range range = Range.openClosed(-4, 6);
        assertEquals(-3, range.min());
        assertEquals(6, range.max());
        assertEquals(BigInteger.TEN, range.size());
    }

    @Test
    @DisplayName("oepnclosed test - base case2")
    void openClosedRangeTest2() {
        Range range = Range.openClosed(Long.MIN_VALUE, Long.MAX_VALUE);
        assertEquals(Long.MIN_VALUE + 1, range.min());
        assertEquals(Long.MAX_VALUE, range.max());
        assertEquals(BigInteger.valueOf(Long.MAX_VALUE).subtract(BigInteger.valueOf(Long.MIN_VALUE)), range.size());
    }

    @Test
    @DisplayName("openClosed exception test - IllegalArgumentException")
    void openClosedExceptionTest1() {
        assertThrows(IllegalArgumentException.class, () -> Range.openClosed(0, 0));
    }

    @Test
    @DisplayName("openClosed exception test - ArithmeticException")
    void openClosedExceptionTest2() {
        assertThrows(ArithmeticException.class, () -> Range.openClosed(Long.MAX_VALUE, Long.MIN_VALUE));
    }

    @Test
    @DisplayName("closedOpen costructor test")
    void closedOpenRangeConstructorTest() {
        assertTrue(Range.closedOpen(1, 3) instanceof Range);
    }


    @Test
    @DisplayName("closedOpen test - base case1")
    void closedOpenRangeTest1() {
        Range range = Range.closedOpen(-4, 6);
        assertEquals(-4, range.min());
        assertEquals(5, range.max());
        assertEquals(BigInteger.TEN, range.size());
    }

    @Test
    @DisplayName("closedOpen test - base case2")
    void closedOpenRangeTest2() {
        Range range = Range.closedOpen(Long.MIN_VALUE, Long.MAX_VALUE);
        assertEquals(Long.MIN_VALUE, range.min());
        assertEquals(Long.MAX_VALUE - 1, range.max());
        assertEquals(BigInteger.valueOf(Long.MAX_VALUE).subtract(BigInteger.valueOf(Long.MIN_VALUE)), range.size());
    }

    @Test
    @DisplayName("closedOpen exception test - IllegalArgumentException")
    void closedOpenExceptionTest1() {
        assertThrows(IllegalArgumentException.class, () -> Range.openClosed(0, 0));
    }

    @Test
    @DisplayName("clsoedOpen exception test - ArithmeticException")
    void closedOpenExceptionTest2() {
        assertThrows(ArithmeticException.class, () -> Range.openClosed(Long.MAX_VALUE, Long.MIN_VALUE));
    }

    @Test
    @DisplayName("range iterator test- element count valid1 ")
    void rangeElementCountIteratorTest1() {
        Range range = Range.closed(3, 15);
        Iterator<Long> iterator = range.iterator();
        long count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        assertEquals(13, count);
    }

    @Test
    @DisplayName("range iterator test - element valid test1")
    void rangeElementValidIterator1() {
        Range range = Range.closed(3, 15);
        Iterator<Long> iterator = range.iterator();
        Iterator<Long> expectedIterator = LongStream.iterate(3, x -> x + 1).limit(15).iterator();
        while (iterator.hasNext() && expectedIterator.hasNext()) {
            assertEquals(expectedIterator.next(), iterator.next());
        }
    }

    @Test
    @DisplayName("range iterator test- element count valid2")
    void rangeElementCountIteratorTest2() {
        Range range = Range.closed(Long.MAX_VALUE - 5, Long.MAX_VALUE);
        Iterator<Long> iterator = range.iterator();
        long count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        assertEquals(6, count);
    }

    @Test
    @DisplayName("range iterator test - element valid test2")
    void rangeElementValidIterator2() {
        Range range = Range.closed(Long.MAX_VALUE - 5, Long.MAX_VALUE - 1);
        Iterator<Long> iterator = range.iterator();
        Iterator<Long> expectedIterator = LongStream.iterate(Long.MAX_VALUE - 5, x -> x + 1).limit(5).iterator();
        while (iterator.hasNext() && expectedIterator.hasNext()) {
            assertEquals(expectedIterator.next(), iterator.next());
        }
    }

    @Test
    @DisplayName("range iterator exception test")
    void rangeIteratorExceptionTest() {
        Range range = Range.closed(Long.MAX_VALUE - 5, Long.MAX_VALUE);
        Iterator<Long> iterator = range.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}