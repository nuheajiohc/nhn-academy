package com.tip.functional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;


/**
 * 이 클래스는 {@link Iterator} 객체를 활용하기 위해 만든 유틸리티 클래스입니다.
 */
public class Iterators {

    /**
     * {@link Iterable<E>}의 요소들에 대해 초기값을 시작으로 이항연산하는 제네릭 메서드
     *
     * @param es         {@link Iterable<E>} 이터러블
     * @param biFunction {@link} reduce에 활용할 이항연산
     * @param init       제네릭 타입의 초기값
     * @return {@link Iterable<E>} 의 이항연산의 결과를 반환
     * @throws IllegalArgumentException 연산을 하는 메서드로 {@link Iterable}인자와 {@link BiFunction}인자에서 null이 들어온다는 것은 연산을 한다는
     *                                  의도와 어울리지 않으므로 매개변수로 들어오지 못하게 precondition으로 예외처리 하였습니다.
     * @throws NullPointerException     Iterable의 요소나 init으로 null이 들어온다면 예외가 발생할 수 있으므로 사용시 주의 바람.<br> 참조형타입의 기본값은
     *                                  null이기 때문에 Iterable의 요소나 init이 null이 오는 것은 허용하였습니다. 매개변수로 들어오는 것은 허용하였지만, null이
     *                                  들어와서 메서드를 사용할 때 nullPointerException이 던져지기 때문에 명시적으로 예외를 잡지 않았습니다.
     */
    public static <E, R> R reduce(Iterable<E> es, BiFunction<R, E, R> biFunction, R init) {
        if (es == null || biFunction == null) {
            throw new IllegalArgumentException();
        }

        R result = init;
        for (E e : es) {
            result = biFunction.apply(result, e);
        }
        return result;
    }

    /**
     * 이터레이터 요소와 초기값을 이항 연산하는 메서드<br/> 이 메서드는 iterator를 매개변수로 받아서 Iterable을 사용하는 reduce메서드를 활용하는 메서드입니다.
     *
     * @param es         {@link Iterator<E>} 이터레이터
     * @param biFunction reduce에 활용할 이항연산
     * @param init       제네릭 타입의 초기값
     * @return reduce(() -> es, biFunction, init)
     */
    public static <E, R> R reduce(Iterator<E> es, BiFunction<R, E, R> biFunction, R init) {
        return reduce(() -> es, biFunction, init);
    }

    /**
     * 두 이터레이터의 요소의 동등성을 비교하는 메서드입니다.
     *
     * @param xs {@link Iterator} 이터레이터
     * @param ys {@link Iterator} 이터레이터
     * @return 이터레이터의 동등여부를 boolean형으로 반환
     * @throws IllegalArgumentException 이터레이터가 null일 경우 예외처리 <br/> 이터레이터 여러 요소 중에서 null이 나오는 경우와 다르게 이터레이터 자체가 null인
     *                                  경우에는 예외처리를 하였습니다. 왜냐하면 null은 아직 어떤 객체도 가리키지 않은 상태인데 그 상태에서 동등성을 판단하는 것은 의미가 없기
     *                                  때문에 막아야 할 값으로 생각해서 IllegalArgumentException 예외로 처리하였습니다.
     * @apiNote 이 메서드는 주어진 이터레이터들의 요소를 비교하기 위해 {@link #reduce(Iterable, BiFunction, Object)},
     * {@link #zip(BiFunction, Iterator, Iterator)} 메서드를 사용합니다.<br/> 주소가 같은 이터레이터가 들어오면 주소로 비교하여 같은 메서드로 판단합니다. 이렇게 하지
     * 않으면 같은 iterator를 사용하기 때문에 return 코드 내부 로직에서 next()로 값을 가져오는 과정에서 연속적인 두 개의 요소를 비교하게 돼서 false를 반환하게 됩니다. 하지만 두 개의
     * 이터레이터의 요소는 같은 것이기 때문에 이렇게 로직을 작성하였습니다. <br/><br/> 두 이터레이터의 같은 위치에 null이 들어온 경우 같은 값이라고 판단하였습니다. 왜냐하면 int타입의 기본형은
     * 0이고 0을 할당받은 두 변수가 있다고 했을 때 같습니다. 이와 같은 논리를 참조형 타입에 대입해서 생각해보면 참조형 타입의 기본형은 null이기 때문에 같은 값으로 판단하였습니다. 그리고 자바는
     * null == null 을 했을 때 true가 나온다는 점도 같은 값으로 판단한 근거가 되었습니다.<br/><br/> 무한 이터레이터가 매개변수로 들어오면 요소가 무한이므로 동등성을 판단할 수 없습니다.
     * 무한 이터레이터를 사용할 때는 주의해주세요. 단, 같은 주소값을 가지는 무한 이터레이터의 경우에는 true를 반환합ㄴ다.
     */
    public static <T> boolean equals(Iterator<T> xs, Iterator<T> ys) { // TODO: reduce,zip를 써서
        if (xs == null || ys == null) {
            throw new IllegalArgumentException();
        }
        if (xs == ys) {
            return true;
        }
        return reduce(zip(Objects::equals, xs, ys), (x, y) -> x && y, true) && (xs.hasNext() == ys.hasNext());
    }

    /**
     * 이터레이터 요소를 설정한 구분자로 나눠서 구분자와 함께 문자열로 반환하는 메서드입니다.<br/> 이 메서드는 주어진 이터레이터의 요소들을 구분자로 연결하기 위해
     * {@link #reduce(Iterable, BiFunction, Object)} 메서드를 사용합니다. 초기값은 첫 번째 요소의 문자열로 설정하고, 구분자를 기준으로 각 요소가 순차적으로 추가됩니다.
     *
     * @param es        {@link Iterator} 이터레이터
     * @param separator 구분자 문자열
     * @return 설정한 구분자를 기준으로 이터레이터 요소를 구분한 문자열
     * @throws IllegalArgumentException 이터레이터와 구분자 문자열에 null이 들어오면 예외처리를 합니다.
     * @apiNote 구분자를 기준으로 이터레이터 요소를 구분한다는 점에서 이터레이터 자체가 null이라면 구분할 요소가 없기 때문에 구분한다는 메서드의 기능과 맞지 않아 예외처리 하였습니다.<br/> 하지만
     * 의도적으로 null로 나누고 싶은 경우에는 null을 문자열 처리하여 사용하면 됩니다.
     */
    public static <T> String toString(Iterator<T> es, String separator) { // TODO: redude를 써서
        if (es == null || separator == null) {
            throw new IllegalArgumentException();
        }

        StringBuilder value = new StringBuilder();
        if (es.hasNext()) {
            T firstElement = es.next();
            value.append(firstElement != null ? firstElement.toString() : null);
        }
        reduce(es, (result, next) -> result.append(separator).append(next), value);
        return value.toString();
    }

    /**
     * 이터레이터 요소를 새로운 요소로 변환한 이터레이터를 반환하는 메서드<br/> 요소에 null이 들어오고 {@link Function}로 값을 변환할 때 (x)->x+" " 를 사용하면 null이 문자열로
     * 변환돼서 연결연산이 이루어집니다.<br/> 하지만 x->x.concat("str")를 이항연산 함수로 넣으면 nullPointerException이 던져집니다.<br/> null값을 다른 요소로 변환하는
     * 경우를 생각해서 exception으로 잡지 않았습니다.<br/> 그러므로 null이 요소 안에 들어 있다면 주의해서 사용해야합니다.<br/>
     *
     * @param es       {@link Iterator} 이터레이터
     * @param function 각 요소에 적용할 이항연산
     * @return 새로운 요소로 변환된 이터레이터
     * @throws IllegalArgumentException {@link Iterator} 나 {@link Function} 이 null인 경우 예외처리합니다.
     * @apiNote 이터레이터의 값을 새로운 요소로 변환을 하는 메서드인데 이터레이터 자체가 null이라면 변환할 수 없고 메서드의 기능과 맞지 않기 때문에 예외처리하였습니다.<br/> 다른 요소로
     * 변환시켜주는 {@link Function}이 null 이라면 map의 기능을 올바르게 수행할 수 없기 때문에 예외처리하였습니다.
     */
    public static <E, R> Iterator<R> map(Iterator<E> es, Function<E, R> function) {
        if (es == null || function == null) {
            throw new IllegalArgumentException();
        }

        return new Iterator<R>() {
            public boolean hasNext() {
                return es.hasNext();
            }

            public R next() {
                return function.apply(es.next());
            }
        };
    }

    /**
     * 이터레이터 요소 중에서 작성한 조건에 맞는 요소만 걸러서 Iterator로 반환하는 메서드
     *
     * @param iterator  {@link Iterator} 이터레이터
     * @param predicate {@link Predicate} 각 요소에 적용할 이항연산
     * @return 조건에 맞는 요소로 이루어진 이터레이터
     * @throws IllegalArgumentException {@link Iterator} 나 {@link Function} 이 null인 경우 예외처리합니다.<br/> 조건에 맞는 요소를 고르는 기능의
     *                                  메서드인데 null값에서는 고를 수 있는 요소가 없으므로 잘못된 인수로 판단하고 예외처리합니다. <br/> 조건에 맞는 요소를 고르는 기능의
     *                                  메서드라는 점에서 조건을 걸어주는 연산이 null이라면 올바르게 값을 거를 수 없으므로 잘못된 인수로 판단하고 예외처리합니다.
     * @throws NullPointerException     next() 값이 존재하지 않으면 얘외처리합니다.
     * @apiNote 같은 이터레이터를 filter의 파라미터로 여러 번 사용할 경우 초기값에 바로 값이 할당되기 때문에 예상하는 결과를 얻지 못할 수 있습니다.<br/> 그러므로 한 이터레이터에는 한번의
     * filter를 사용해주세요.
     */
    public static <E> Iterator<E> filter(Iterator<E> iterator, Predicate<E> predicate) {
        // TODO: Bug를 찾을 수 있는 test code를 IteratorTest.filterTest에 쓰고, Bug 고치기
        if (iterator == null || predicate == null) {
            throw new IllegalArgumentException();
        }

        return new Iterator<E>() {
            private E current = findFirst(iterator, predicate);

            public boolean hasNext() {
                return current != null;
            }

            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("filter");
                }
                E old = current;
                current = findFirst(iterator, predicate);
                return old;
            }
        };
    }

    public static <E> E findFirst(Iterator<E> iterator, Predicate<E> predicate) {
        while (iterator.hasNext()) {
            E first = iterator.next();
            if (predicate.test(first)) {
                return first;
            }
        }
        return null;
    }

    /**
     * 초기값과 변환 연산을 통해 무한한 요소를 가지는 이터레이터를 반환하는 메서드
     *
     * @param seed 제네릭 타입의 초기값
     * @param f    다음값에 적용할 변환 연산
     * @return 무한한 요소가 생성된 이터레이터
     * @throws IllegalArgumentException 초기값 seed나 {@link UnaryOperator} 이 null인 경우 예외처리합니다.
     * @apiNote 변환 연산을 적용하여 무한한 요소를 만드는 기능을 한다는 점에서 변환연산이 null이면 메서드의 기능을 올바르게 수해할 수 없으므로 null값을 예외처리하였습니다.<br/> 무한한 요소를
     * 만들면서 null이 필요한 경우가 있을수도 있으므로 초기값으로는 null을 허용하였습니다.<br/> 그러므로 null이 들어가는 상황을 고려해 주의하여 사용해주세요.
     */
    public static <T> InfiniteIterator<T> iterate(T seed, UnaryOperator<T> f) {
        if (f == null) {
            throw new IllegalArgumentException();
        }
        return new InfiniteIterator<T>() {
            public T current = seed;

            @Override
            public T next() {
                T old = current;
                current = f.apply(current);
                return old;
            }
        };
    }

    /**
     * 주어진 이터레이터 요소 중 일정 개수만 포함하는 이터레이터를 반환하는 메서드
     *
     * @param iterator {@link Iterator} 이터레이터
     * @param maxSize  가져올 데이터 개수
     * @return 주어진 이터레이터 요소 중 일정 개수만 포함하는 이터레이터를 반환
     * @throws IllegalArgumentException {@link Iterator}와 maxsize이 null인 경우 예외처리합니다.
     * @throws NoSuchElementException   next()를 호출할 때 다음 값이 없으면 예외처리합니다.
     * @apiNote 제한된 개수의 요소를 가지는 이터레이터를 반환한다는 점에서 iterator가 null이면 제한할 요소를 반환한다는 의미와 맞지 않으므로 잘못된 매개변수로 생각해서 예외처리하였습니다.
     * <br/> 제한된 개수를 반환한다는 점에서 음수는 잘못된 매개변수로 생각하였습니다. 0을 허용한 이유는 빈 이터레이터를 가져오고 싶은 사용자가 있을 수도 있고,개수가 0개가 될 수 있기 때문에 잘못된
     * 매개변수라고는 생각하지 않아서 허용하였습니다.<br/><br/> maxsize 이상의 값이 들어와도 maxSize의 개수만큼 값이 나옵니다.
     */
    public static <T> Iterator<T> limit(Iterator<T> iterator, long maxSize) { // TODO
        if (iterator == null) {
            throw new IllegalArgumentException(String.valueOf((Object) null));
        }

        if (maxSize < 0) {
            throw new IllegalArgumentException("maxSize < 0");
        }

        return new Iterator<>() {
            private long count = 0;

            @Override
            public boolean hasNext() {
                return iterator.hasNext() && (count < maxSize);
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("limit");
                }
                count = Math.addExact(count, 1);
                return iterator.next();
            }
        };
    }

    /**
     * {@link Supplier}를 이용하여 {@link InfiniteIterator}의 next의 return 값을 정해주는 메서드
     *
     * @param supplier {@link InfiniteIterator}의 next의 값이 될 연산
     * @return {@link Supplier} 로 결정된 next값을 가지는 {@link InfiniteIterator} 를 반환
     * @throws IllegalArgumentException {@link Supplier}가 null인 경우 예외처리를 합니다.
     * @apiNote next값이 null이 되는 것은 허용하지만, {@link Supplier} 자체가 null인 경우는 허용하지 않습니다. next값을 만드는 연산 자체가 null이라는 것은 next값을
     * 만들지 않겠다는 의미로 생각해서 잘못된 인자로 생각하여 예외처리하였습니다.<br/> 이 메서드의 목적은 next값을 사용자화하여 {@link InfiniteIterator}를 반환하여 연산이 필요한
     * 시점까지 연산을 넘기기 위함입니다.
     */
    public static <T> InfiniteIterator<T> generate(Supplier<T> supplier) { // TODO:
        if (supplier == null) {
            throw new IllegalArgumentException("supplier is null");
        }
        return supplier::get;
    }


    /**
     * 두 개의 이터레이터를 {@link BiFunction}을 통해 하나의 이터레이터로 만드는 메서드
     *
     * @param biFunction 두 이터레이터를 결합하는 연산
     * @param xIterator  첫번재 이터레이터
     * @param yIterator  두번째 이터레이터
     * @return 두 이터레이터를 조합한 이터레이터
     * @throws IllegalArgumentException {@link BiFunction}와 {@link Iterator} 중 null 값이 있다면 예외처리
     * @throws NoSuchElementException   다음값이 존재하지 않을 때 next를 호춣하면 예외처리
     * @apiNote 두 개의 이터레이터를 하나의 이터레이터로 만드는 기능인데 결합연산이 null이라는 건 결합한다는 말과 알맞지 않고, 결합할 이터레이터가 없다는 말도 알맞지 않기 때문에 이 경우들에서
     * 예외처리하였습니다.
     */
    public static <X, Y, Z> Iterator<Z> zip(BiFunction<X, Y, Z> biFunction, Iterator<X> xIterator,
                                            Iterator<Y> yIterator) {

        if (biFunction == null || xIterator == null || yIterator == null) {
            throw new IllegalArgumentException();
        }

        return new Iterator<Z>() {
            public boolean hasNext() {
                return xIterator.hasNext() && yIterator.hasNext();
            }

            public Z next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("zip");
                }
                return biFunction.apply(xIterator.next(), yIterator.next());
            }
        };
    }

    /**
     * 이터레이터 요소의 갯수를 세주는 메서드
     *
     * @param iterator {@link Iterator} 순회할 이터레이터
     * @return 요소를 셀 수 있는 {@link #reduce(Iterator, BiFunction, Object)} 반환
     * @throws IllegalArgumentException {@link Iterator}가 null이라면 예외처리
     * @throws ArithmeticException      요소의 개수가 long 범위를 넘어가면 예외처리
     * @apiNote 요소의 개수를 세야되는데 {@link Iterator}가 null이라는 것은 개수를 센다는 의도와 맞지 않기 때문에 예외처리하였습니다.<br/> {@link Iterator}의 요소의
     * 개수는 제한이 없기 때문에 long 범위 안의 개수를 센다면 long overflow가 생길 수 있습니다. 그래서 Math.addExact로 예외처리를 하였습니다.
     */
    public static <E> long count(Iterator<E> iterator) {
        // TODO: reduce를 써서
        if (iterator == null) {
            throw new IllegalArgumentException("count");
        }
        return reduce(iterator, (acc, element) -> Math.addExact(acc, 1), 0L);
    }

    /**
     * 이터레이터의 요소의 위치를 통해 값을 얻는 메서드
     *
     * @param iterator {@link Iterator} 이터레이터
     * @param position 요소의 위치, ex. 요소 2,4,6  > 2는 1번째 위치
     * @return 이터레이터 요소의 위치에 해당하는 값
     * @throws IllegalArgumentException {@link Iterator}가 null 인 경우, position이 0보다 작거나 같은 경우
     * @apiNote 이터레이터의 어떤 위치에 있는 값을 얻는 메서드의 기능으로 보아 {@link Iterator} 자체가 null이 된다는 것은 옳지 않다고 생각하여 null일 경우 예외처리를 하였습니다.
     * <br/> position은 인덱스가 아니라 번째를 뜻하는 변수로 의미를 바꿔서 양의 정수만 들어올 수 있게 하였습니다. index의 의미에서 번째의 의미로 바꿈에 따라 예외도
     * IllegalArgumentException으로 변경하였습니다.<br/> 바꾼 이유는 원래 코드에서는 return할 때 index+1을 하여 get메서드의 매개변수로 올바른 long 범위가 들어와도
     * Long.MAX_VALUE가 들어오면 return의 limit 메서드에서 예외가 발생하기 때문입니다.<br/> 그래서 예외 상황을 줄이기 위해 위치를 의미하는 변수로 변경하였습니다.
     */
    public static <T> T get(Iterator<T> iterator, long position) {
        if (iterator == null) {
            throw new IllegalArgumentException("get");
        }

        if (position <= 0) {
            throw new IllegalArgumentException("index < " + position);
        }
        return getLast(limit(iterator, position));
    }

    /**
     * get메서드의 helper method
     *
     * @param iterator 제네릭 타입의 이터레이터
     * @return 이터레이터의 마지막 값
     * @apiNote 원래 코드는 iterator.next()를 먼저 받고 시작합니다.<br/> 물론 get메서드에서 예외처리가 돼서 NullPointerException에 걸리지는 않지만 이 메서드만 봤을
     * 때도 문제가 없어보이는 메서드를 만들기 위해 hasNext로 검사를 한뒤 next를 받는 방식을 주석으로 작성해뒀습니다.
     */
    private static <T> T getLast(Iterator<T> iterator) {
        while (true) {
            T current = iterator.next();
            if (!iterator.hasNext()) {
                return current;
            }
        }
//        T current = null;
//        while (iterator.hasNext()) {
//            current = iterator.next();
//        }
//        return current;
    }

    /**
     * 이터레이터를 리스트로 변환하는 메서드
     * @param iterator {@link Iterator} 이터레이터
     * @return 변환된 리스트를 반환
     * @throws IllegalArgumentException {@link Iterator}가 null일 경우
     */

    public static <T> List<T> toList(Iterator<T> iterator) {
        if (iterator == null) {
            throw new IllegalArgumentException("toList");
        }
        List<T> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    public static <E> void print(Iterator<E> iterator, String separator,
                                 java.io.PrintStream printStream) {
        printStream.print(toString(iterator, separator));
    }

    public static <E> void print(Iterator<E> iterator, String separator) {
        print(iterator, separator, System.out);
    }

    public static <E> void println(Iterator<E> iterator, String separator,
                                   java.io.PrintStream printStream) {
        print(iterator, separator, printStream);
        printStream.println();
    }

    public static <E> void println(Iterator<E> iterator, String separator) {
        println(iterator, separator, System.out);
    }

    public static <E> void println(Iterator<E> iterator) {
        println(iterator, ", ");
    }

    private Iterators() {
    }

}


