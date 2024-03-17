package com.tip.functional.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tip.functional.InfiniteIterator;
import com.tip.functional.Iterators;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IteratorsTest {

    @Test
    @DisplayName("InfiniteIterator 부모 인터페이스 확인 테스트")
    void InfiniteIteratorTest() {
        assertTrue(Iterator.class.isAssignableFrom(InfiniteIterator.class));
    }

    @Test
    @DisplayName("reduce test based on Iterable - number type test")
    void reduceBaseTestByNumber() {
        Iterable<Integer> intList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertEquals(45, Iterators.reduce(intList, Integer::sum, 0));
    }

    @Test
    @DisplayName("reduce test based on Iterable - String type test")
    void reduceBaseTestByString() {
        Iterable<String> stringIterable = List.of("One", "Two", "Three");
        assertEquals("OneTwoThree", Iterators.reduce(stringIterable, (x, y) -> x + y, ""));
    }

    @Test
    @DisplayName("reduce test - special case")
    void reduceTest() {
        List<String> stringIterable = Arrays.asList("Nhn", "Academy", null);
        assertEquals("nullNhnAcademynull", Iterators.reduce(stringIterable, (x, y) -> x + y, null));
    }

    @Test
    @DisplayName("reduce test - all iterator value is consumed ")
    void reduceIteratesOverAllElementsTest() {
        Iterable<Integer> intIterable = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Iterator<Integer> intIterator = intIterable.iterator();
        Iterators.reduce(intIterator, Integer::sum, 0);
        assertFalse(intIterator.hasNext());
    }

    @Test
    @DisplayName("reduce Exception test - IterableParameter")
    void reduceExceptionTestByIterableParameter() {
        Iterable<Integer> intIterable = null;
        assertThrows(IllegalArgumentException.class, () -> Iterators.reduce(intIterable, Integer::sum, 1));
    }

    @Test
    @DisplayName("reduce Exception test - BiFunction Parameter")
    void reduceExceptionTestByBiFunctionParameter() {
        Iterable<String> stringIterable = List.of("One", "Two", "Three");
        assertThrows(IllegalArgumentException.class, () -> Iterators.reduce(stringIterable, null, ""));
    }

    @Test
    @DisplayName("reduce Exception test - nullPointerException")
    void reduceNullPointerExceptionTest() {
        Iterable<Integer> intIterable = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertThrows(NullPointerException.class, () -> Iterators.reduce(intIterable, Integer::sum, null));
    }


    @Test
    @DisplayName("reduce test based on Iterator - overloding test")
    void reduceOverloadingTest() {
        Iterable<Integer> intIterable = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertEquals(Iterators.reduce(intIterable, Integer::sum, 0),
                Iterators.reduce(intIterable.iterator(), Integer::sum, 0));
    }

    @Test
    @DisplayName("equals test - same length, same element")
    void equalsTest1() {
        Iterable<Integer> intIterable = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Iterator<Integer> iterator1 = intIterable.iterator();
        Iterator<Integer> iterator2 = intIterable.iterator();
        assertTrue(Iterators.equals(iterator1, iterator2));
        assertFalse(iterator1.hasNext());
    }

    @Test
    @DisplayName("equals test - same length, different element")
    void equalsTest2() {
        Iterator<Integer> iterator1 = IntStream.iterate(1, x -> x + 1).limit(5).iterator();
        Iterator<Integer> iterator2 = IntStream.iterate(3, x -> x + 1).limit(5).iterator();
        assertFalse(Iterators.equals(iterator1, iterator2));
        assertFalse(iterator1.hasNext());
    }

    @Test
    @DisplayName("equals test - same reference Iterator")
    void equalsTest3() {
        Iterable<String> stringIterable = List.of("One", "Two", "Three");
        Iterator<String> iterator = stringIterable.iterator();
        assertTrue(Iterators.equals(iterator, iterator));
        assertEquals("One", iterator.next());
    }

    @Test
    @DisplayName("equals test - different length1")
    void equalsTest4() {
        Iterator<Integer> iterator1 = IntStream.iterate(1, x -> x + 1).limit(5).iterator();
        Iterator<Integer> iterator2 = IntStream.iterate(1, x -> x + 1).limit(7).iterator();
        assertFalse(Iterators.equals(iterator1, iterator2));
        assertFalse(iterator1.hasNext());
        assertTrue(iterator2.hasNext());
    }

    @Test
    @DisplayName("equals test - different length2")
    void equalsTest5() {
        Iterator<Integer> iterator1 = IntStream.iterate(5, x -> x + 1).limit(5).iterator();
        Iterator<Integer> iterator2 = IntStream.iterate(1, x -> x + 1).limit(7).iterator();
        assertFalse(Iterators.equals(iterator1, iterator2));
        assertFalse(iterator1.hasNext());
        assertTrue(iterator2.hasNext());
    }

    @Test
    @DisplayName("equals test - element include null")
    void equalsTest6() {
        List<String> list = Arrays.asList("Nhn", "Academy", null);
        Iterator<String> iterator1 = list.iterator();
        Iterator<String> iterator2 = list.iterator();
        assertTrue(Iterators.equals(iterator1, iterator2));
        assertFalse(iterator1.hasNext());
        assertFalse(iterator2.hasNext());
    }

    @Test
    @DisplayName("equals Exception Test - Iterator parameter1")
    void equalsExceptionTest1() {
        Iterable<String> stringIterable = List.of("One", "Two", "Three");
        Iterator<String> iterator1 = stringIterable.iterator();
        assertThrows(IllegalArgumentException.class, () -> Iterators.equals(iterator1, null));
    }

    @Test
    @DisplayName("equals Exception Test - Iterator parameter2")
    void equalsExceptionTest2() {
        Iterable<String> stringIterable = List.of("One", "Two", "Three");
        Iterator<String> iterator1 = stringIterable.iterator();
        assertThrows(IllegalArgumentException.class, () -> Iterators.equals(null, iterator1));
    }

    @Test
    @DisplayName("toString Test - String type")
    void toStringTestByString() {
        Iterable<String> stringIterable = List.of("Nhn", "Academy", "Winner");
        Iterator<String> iterator = stringIterable.iterator();
        assertEquals("Nhn Academy Winner", Iterators.toString(iterator, " "));
    }

    @Test
    @DisplayName("toString Test - Number type")
    void toStringTestByNumber() {
        Iterable<Integer> list = List.of(1, 2, 3, 4, 5);
        Iterator<Integer> iterator = list.iterator();
        assertEquals("1, 2, 3, 4, 5", Iterators.toString(iterator, ", "));
    }

    @Test
    @DisplayName("toString Test")
    void toStringNullElementTest1() {
        List<Integer> list = Arrays.asList(1, 2, 3, null, 5);
        Iterator<Integer> iterator = list.iterator();
        assertEquals("1 2 3 null 5", Iterators.toString(iterator, " "));
    }

    @Test
    @DisplayName("toString test - first element is null")
    void toStringNullElementTest2() {
        List<Integer> list = Arrays.asList(null, 2, 3, 4, 5);
        Iterator<Integer> iterator = list.iterator();
        assertEquals("null 2 3 4 5", Iterators.toString(iterator, " "));
    }

    @Test
    @DisplayName("toStringException test - Iterator parameter")
    void toStringExceptionTest1() {
        assertThrows(IllegalArgumentException.class, () -> Iterators.toString(null, " "));
    }

    @Test
    @DisplayName("toStringException test - separator parameter")
    void toStringExceptionTest2() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Iterator<Integer> iterator = list.iterator();
        assertThrows(IllegalArgumentException.class, () -> Iterators.toString(iterator, null));
    }

    @Test
    @DisplayName("map return type test")
    void mapReturnTypeTest() {
        Iterable<String> list = List.of("nhn", "academy", "winner");
        assertTrue(Iterators.map(list.iterator(), x -> x) instanceof Iterator);
    }

    @Test
    @DisplayName("map test - number type")
    void mapTestByNumber() {
        Iterable<Integer> list1 = List.of(1, 2, 3, 4, 5);
        Iterable<Integer> list2 = List.of(2, 4, 6, 8, 10);
        Iterator<Integer> iterator1 = Iterators.map(list1.iterator(), x -> x * 2);
        Iterator<Integer> iterator2 = list2.iterator();
        while (iterator1.hasNext() && iterator2.hasNext()) {
            assertEquals(iterator2.next(), iterator1.next());
        }
    }

    @Test
    @DisplayName("map test - string type")
    void mapTestByString() {
        Iterable<String> list1 = List.of("nhn", "academy", "winner");
        Iterable<String> list2 = List.of("nhn화이팅", "academy화이팅", "winner화이팅");
        Iterator<String> iterator1 = Iterators.map(list1.iterator(), x -> x.concat("화이팅"));
        Iterator<String> iterator2 = list2.iterator();
        while (iterator1.hasNext() && iterator2.hasNext()) {
            assertEquals(iterator2.next(), iterator1.next());
        }
    }

    @Test
    @DisplayName("map test - empty iterator")
    void mapTestByEmptyElement() {
        List<Integer> list = List.of();
        Iterator<String> iterator = Iterators.map(list.iterator(), Object::toString);
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    @DisplayName("map test - null element, String operator")
    void mapTestByNullElement() {
        Iterable<String> list1 = Arrays.asList(null, "nhn", "academy", "winner");
        Iterable<String> list2 = List.of("nullHaha", "nhnHaha", "academyHaha", "winnerHaha");
        Iterator<String> iterator1 = Iterators.map(list1.iterator(), x -> x + "Haha");
        Iterator<String> iterator2 = list2.iterator();
        while (iterator1.hasNext() && iterator2.hasNext()) {
            assertEquals(iterator2.next(), iterator1.next());
        }
    }

    @Test
    @DisplayName("map exception test - Iterator parameter")
    void mapExceptionTest1() {
        assertThrows(IllegalArgumentException.class, () -> Iterators.map(null, x -> x));
    }

    @Test
    @DisplayName("map exception test - Function parameter")
    void mapExceptionTest2() {
        Iterable<String> list = List.of("nhn", "academy", "winner");
        Iterator<String> iterator = list.iterator();
        assertThrows(IllegalArgumentException.class, () -> Iterators.map(iterator, null));
    }

    @Test
    @DisplayName("map exception test - null element, String operator")
    void mapExceptionTestByNullElement() {
        Iterable<String> list1 = Arrays.asList(null, "nhn", "academy", "winner");
        Iterator<String> iterator = Iterators.map(list1.iterator(), x -> x.concat("Haha"));
        if (iterator.hasNext()) {
            assertThrows(NullPointerException.class, iterator::next);
        }
    }

    @Test
    @DisplayName("filter return type test")
    void filterReturnTypeTest() {
        Iterable<String> list = List.of("nhn", "academy", "winner");
        assertTrue(Iterators.filter(list.iterator(), x -> true) instanceof Iterator);
    }

    @Test
    @DisplayName("filter test - empty Iterator")
    void filterTestByEmptyElement() {
        Iterable<Integer> intIterable = Arrays.asList();
        Iterator<Integer> iterator = Iterators.filter(intIterable.iterator(), x -> x % 2 == 0);
        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("filter test - number type")
    void filterTestByNumber() {
        Iterable<Integer> intIterable = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Iterable<Integer> intIterable2 = List.of(2, 4, 6, 8, 10);
        Iterator<Integer> expectedIterator = intIterable2.iterator();
        Iterator<Integer> filterdIterator = Iterators.filter(intIterable.iterator(), x -> x % 2 == 0);
        while (expectedIterator.hasNext() && filterdIterator.hasNext()) {
            assertEquals(expectedIterator.next(), filterdIterator.next());
        }
    }

    @Test
    @DisplayName("filter test - string type")
    void filterTestByString() {
        Iterable<String> stringIterable = List.of("nhn", "academy", "nhn", "화이팅", "winner");
        Iterable<String> stringIterable2 = List.of("nhn", "nhn");
        Iterator<String> expectedIterator = stringIterable2.iterator();
        Iterator<String> filterdIterator = Iterators.filter(stringIterable.iterator(), x -> Objects.equals(x, "nhn"));
        while (expectedIterator.hasNext() && filterdIterator.hasNext()) {
            assertEquals(expectedIterator.next(), filterdIterator.next());
        }
    }

    @Test
    @DisplayName("filter test - null element")
    void filterTestByNullElement() {
        Iterable<String> stringIterable = Arrays.asList("nhn", null, "nhn", "화이팅", "winner");
        List<String> stringIterable2 = Arrays.asList(new String[1]);
        Iterator<String> expectedIterator = stringIterable2.iterator();
        Iterator<String> filterdIterator = Iterators.filter(stringIterable.iterator(), x -> Objects.equals(x, null));
        while (expectedIterator.hasNext() && filterdIterator.hasNext()) {
            assertEquals(expectedIterator.next(), filterdIterator.next());
        }
    }


    @Test
    @DisplayName("filter exception test - iterator null")
    void filterExceptionTestByIterator() {
        assertThrows(IllegalArgumentException.class, () -> Iterators.filter(null, x -> true));
    }

    @Test
    @DisplayName("filter exception test - predicate null")
    void filterExceptionTestByPredicateParameter() {
        Iterable<String> stringIterable = List.of("nhn", "academy", "nhn", "화이팅", "winner");
        Iterator<String> iterator = stringIterable.iterator();
        assertThrows(IllegalArgumentException.class, () -> Iterators.filter(iterator, null));
    }

    @Test
    @DisplayName("filter exception test - next")
    void filterExceptionTestByNext() {
        Iterator<Integer> iterator = IntStream.iterate(1, x -> x + 1).limit(10).iterator();
        Iterator<Integer> filteredIterator = Iterators.filter(iterator, x -> x % 11 == 0);
        assertThrows(NoSuchElementException.class, () -> filteredIterator.next());
    }

    @Test
    @DisplayName("findFirst test")
    void findFirstTest() {
        Iterable<String> stringIterable = List.of("nhn", "academy", "화이팅", "winner", "nhn");
        Iterator<String> iterator = stringIterable.iterator();
        assertEquals("nhn", Iterators.findFirst(iterator, x -> Objects.equals("nhn", x)));
        assertEquals("academy", iterator.next());
    }

    @Test
    @DisplayName("iterate return type test - instanceof InfiniteIterator")
    void iterateReturnTypeTest2() {
        assertTrue(Iterators.iterate(1, x -> x + 1) instanceof InfiniteIterator);
    }

    @Test
    @DisplayName("iterate hasNext test - number type")
    void iterateHasNextTestByNumber() {
        Iterator<Integer> iterator = Iterators.iterate(1, x -> x + 1);
        assertTrue(iterator.hasNext());
    }

    @Test
    @DisplayName("iterate next test - number type")
    void iterateNextTest() {
        Iterator<Integer> iterator = Iterators.iterate(1, x -> x + 1);
        assertEquals(1, iterator.next());
        assertEquals(2, iterator.next());
    }

    @Test
    @DisplayName("iterate hasNext test - string type")
    void iterateHasNextTestByString() {
        Iterator<String> iterator = Iterators.iterate("a", x -> x + "a");
        assertTrue(iterator.hasNext());
    }

    @Test
    @DisplayName("iterate next test - string type")
    void iterateNextTestByString() {
        Iterator<String> iterator = Iterators.iterate("a", x -> x + "a");
        assertEquals("a", iterator.next());
        assertEquals("aa", iterator.next());
    }

    @Test
    @DisplayName("iterate exception test - UnaryOperator is null")
    void iterateExceptionTest2() {
        assertThrows(IllegalArgumentException.class, () -> Iterators.iterate(1, null));
    }

    @Test
    @DisplayName("limit return type test")
    void limitReturnTypeTest() {
        Iterator<Integer> iterator = IntStream.iterate(1, x -> x + 1).iterator();
        assertTrue(Iterators.limit(iterator, 5) instanceof Iterator);
    }

    @Test
    @DisplayName("limit test - maxSize")
    void limitMaxSizeTest1() {
        Iterator<Integer> iterator = IntStream.iterate(1, x -> x + 1).iterator();
        Iterator<Integer> limitedIterator = Iterators.limit(iterator, 5);
        int count = 0;
        while (limitedIterator.hasNext()) {
            count++;
            limitedIterator.next();
        }
        assertEquals(5, count);
    }

    @Test
    @DisplayName("limit test - over maxSize == maxSize")
    void limitMaxSizeTest2() {
        Iterator<Integer> iterator = IntStream.iterate(1, x -> x + 1).limit(3).iterator();
        Iterator<Integer> limitedIterator = Iterators.limit(iterator, 5);
        int count = 0;
        while (limitedIterator.hasNext()) {
            count++;
            limitedIterator.next();
        }
        assertEquals(3, count);
    }

    @Test
    @DisplayName("limit test - number")
    void limitTest() {
        Iterator<Integer> iterator = IntStream.iterate(1, x -> x + 1).iterator();
        Iterator<Integer> limitedIterator = Iterators.limit(iterator, 5);
        Iterator<Integer> expectedIterator = IntStream.iterate(1, x -> x + 1).limit(5).iterator();
        while (limitedIterator.hasNext() && expectedIterator.hasNext()) {
            assertEquals(expectedIterator.next(), limitedIterator.next());
        }
    }

    @Test
    @DisplayName("limit test - string")
    void limitTestByString() {
        List<String> stringList = List.of("nhn", "academy", "화이팅", "winner");
        Iterator<String> iterator = stringList.iterator();
        Iterator<String> limitedIterator = Iterators.limit(iterator, 3);
        Iterator<String> expectedIterator = stringList.stream().limit(3).iterator();
        while (limitedIterator.hasNext() && expectedIterator.hasNext()) {
            assertEquals(expectedIterator.next(), limitedIterator.next());
        }
    }

    @Test
    @DisplayName("limit exception test - iterator is null")
    void limitExceptionTestByIterator() {
        assertThrows(IllegalArgumentException.class, () -> Iterators.limit(null, 5));
    }

    @Test
    @DisplayName("limit exception tset - maxSize < 0")
    void limitExceptionTestByMaxSize() {
        Iterator<Integer> iterator = IntStream.iterate(1, x -> x + 1).iterator();
        assertThrows(IllegalArgumentException.class, () -> Iterators.limit(iterator, -1));
    }

    @Test
    @DisplayName("limit exception tset - next")
    void limitExceptionTestByNext() {
        Iterator<Integer> iterator = IntStream.iterate(1, x -> x + 1).iterator();
        Iterator<Integer> limitedIterator = Iterators.limit(iterator, 0);
        assertThrows(NoSuchElementException.class, () -> limitedIterator.next());
    }

    @Test
    @DisplayName("generate test - return type test")
    void generateReturnTypeTest() {
        assertTrue(Iterators.generate(() -> 1) instanceof InfiniteIterator);
    }

    @Test
    @DisplayName("generate test - string type")
    void generateTestByString() {
        InfiniteIterator<String> iterator = Iterators.generate(() -> "jaehun");
        int randomCount = 10;
        for (int i = 0; i < randomCount; i++) {
            assertEquals("jaehun", iterator.next());
        }
    }

    @Test
    @DisplayName("generate test - number type")
    void generateTestByNumber() {
        InfiniteIterator<Integer> iterator = Iterators.generate(() -> 20);
        int randomCount = 15;
        for (int i = 0; i < randomCount; i++) {
            assertEquals(20, iterator.next());
        }
    }

    @Test
    @DisplayName("generateExceptionTest")
    void generateExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> Iterators.generate(null));
    }

    @Test
    @DisplayName("zipTest - same size")
    void zipSameSizeTest() {
        Iterator<Integer> iterator1 = IntStream.iterate(1, x -> x + 1).limit(5).iterator();
        Iterator<Integer> iterator2 = IntStream.iterate(10, x -> x + 1).limit(5).iterator();
        Iterator<Integer> iteratorByZip = Iterators.zip(Integer::sum, iterator1, iterator2);
        Iterator<Integer> expectedIterator = IntStream.iterate(11, x -> x + 2).limit(5).iterator();

        while (iteratorByZip.hasNext() && expectedIterator.hasNext()) {
            assertEquals(expectedIterator.next(), iteratorByZip.next());
        }
    }

    @Test
    @DisplayName("zip test - different size")
    void zipDifferentSizeTest() {
        Iterator<Integer> iterator1 = IntStream.iterate(1, x -> x + 1).limit(5).iterator();
        Iterator<Integer> iterator2 = IntStream.iterate(10, x -> x + 1).limit(7).iterator();
        Iterator<Integer> iteratorByZip = Iterators.zip(Integer::sum, iterator1, iterator2);
        Iterator<Integer> expectedIterator = IntStream.iterate(11, x -> x + 2).limit(5).iterator();

        while (iteratorByZip.hasNext() && expectedIterator.hasNext()) {
            assertEquals(expectedIterator.next(), iteratorByZip.next());
        }
    }

    @Test
    @DisplayName("zipExceptionTest - biFunction")
    void zipExceptionTestByBiFunction() {
        Iterator<Integer> iterator1 = IntStream.iterate(1, x -> x + 1).iterator();
        Iterator<Integer> iterator2 = IntStream.iterate(1, x -> x + 1).iterator();
        assertThrows(IllegalArgumentException.class, () -> Iterators.zip(null, iterator1, iterator2));
    }

    @Test
    @DisplayName("zipExceptionTest - Iterator")
    void zipExceptionTestByIterator() {
        Iterator<String> iterator = Arrays.asList(new String[1]).iterator();
        assertThrows(IllegalArgumentException.class, () -> Iterators.zip((x, y) -> x, null, iterator));
    }

    @Test
    @DisplayName("zipExceptionTest - next")
    void zipExceptionTestByNext() {
        Iterator<Integer> iterator1 = IntStream.iterate(1, x -> x + 1).limit(0).iterator();
        Iterator<Integer> iterator2 = IntStream.iterate(1, x -> x + 1).limit(0).iterator();
        Iterator<Integer> iteratorByZip = Iterators.zip(Integer::sum, iterator1, iterator2);
        assertThrows(NoSuchElementException.class, iteratorByZip::next);
    }

    @Test
    @DisplayName("count test - number")
    void countTestByNumber() {
        Iterator<Integer> iterator = IntStream.iterate(1, x -> x + 1).limit(10).iterator();
        assertEquals(10, Iterators.count(iterator));
    }

    @Test
    @DisplayName("count test - string")
    void countTestByString() {
        List<String> stringList = List.of("nhn", "academy", "화이팅", "winner");
        Iterator<String> iterator = stringList.iterator();
        assertEquals(4, Iterators.count(iterator));
    }

    /**
     * long범위의 오버플로우를 테스트해야 하는데 현실적으로 long 최대 범위의 값을 1부터 차례대로 카운팅하면서 연산하기 힘들기 때문에 {@link #customCount(Iterator)} 라는
     * long.MaxValue 를 초기값으로 가지는 메서를 만들어서 테스트하였습니다.<br/> 원래대로라면 count를 테스트하기 위한 iterator를 임의로 만드는 것이 올바르지만 아직 부족하여
     * count 메서드를 건들어서 만들게 되었습니다.
     */
    @Test
    @DisplayName("count exception test - longRange")
    void countExceptionTestByIntRange() {
        Iterator<Integer> dummyIterator = IntStream.iterate(1, x -> x).iterator();
        assertThrows(ArithmeticException.class, () -> customCount(dummyIterator));
    }

    private <E> long customCount(Iterator<E> iterator) {
        return Iterators.reduce(iterator, (acc, currentCount) -> Math.addExact(acc, 1), Long.MAX_VALUE);
    }

    @Test
    @DisplayName("count exception test - iterator")
    void countExceptionTestByIterator() {
        assertThrows(IllegalArgumentException.class, () -> Iterators.count(null));
    }

    /**
     * position이 long 범위까지임을 증명하기 위해 int범위보다 큰 값을 넣어 테스트했습니다.
     */
    @Test
    @DisplayName("get test - Valid even if it exceeds the Int range")
    void getTestByOverIntRange() {
        Iterator<Long> iterator = LongStream.iterate(1, x -> x + 1).iterator();
        assertEquals((long) Integer.MAX_VALUE + 1, Iterators.get(iterator, (long) Integer.MAX_VALUE + 1));
    }

    @Test
    @DisplayName("get test - base case")
    void getTest() {
        Iterator<Long> iterator = LongStream.iterate(1, x -> x + 1).limit(10).iterator();
        assertEquals(10, Iterators.get(iterator, 10));
    }

    @Test
    @DisplayName("get exception test - iterator")
    void getExceptionTestByIterator() {
        assertThrows(IllegalArgumentException.class, () -> Iterators.get(null, 3));
    }

    @Test
    @DisplayName("get exception test - position")
    void getExceptionTestByPosition() {
        Iterator<Integer> iterator = IntStream.iterate(1, x -> x + 1).iterator();
        assertThrows(IllegalArgumentException.class, () -> Iterators.get(iterator, -1));
        assertThrows(IllegalArgumentException.class, () -> Iterators.get(iterator, 0));
    }

    @Test
    @DisplayName("toList test - base case")
    void toListTest() {
        List<Integer> list = Arrays.asList(1, 2, 3, null, 4);
        Iterator<Integer> iterator = list.iterator();
        List<Integer> listByToListMethod = Iterators.toList(iterator);
        for (int i = 0; i < 5; i++) {
            assertEquals(list.get(i), listByToListMethod.get(i));
        }
    }

    @Test
    @DisplayName("toList exception test")
    void toListExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> Iterators.toList(null));
    }

    @Test
    @DisplayName("printTest")
    void printTest1() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);

        Iterator<Integer> iterator = Arrays.asList(1, 2, 3).iterator();
        Iterators.print(iterator, ", ", printStream);

        String result = byteArrayOutputStream.toString();
        assertEquals("1, 2, 3", result);
    }

    @Test
    @DisplayName("printTest")
    void printTest2(){
        Iterator<Integer> iterator = IntStream.iterate(1,x->x+1).limit(10).iterator();
        Iterators.print(iterator,", ");
    }

}
