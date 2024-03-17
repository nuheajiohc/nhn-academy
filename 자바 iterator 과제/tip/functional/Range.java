package com.tip.functional;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 연속적인 범위 값을 나타내는 클래스입니다.
 * @apiNote {@link Range} 클래스는 정적 팩토리 메서드 패턴으로 만들었습니다.
 * 처음 구조는 생성자와 closed라는 메서드로 생성자를 호출하는 두가지의 생성방법이 있었는데
 * closed에서 long.max_value를 매개변수로 넣을 경우 문제가 생겨서 전반적인 구조를 변경하였습니다.<br/>
 * 생성자를 private로 막음에 따라 사용자가 사용할 수 있는 생성패턴들을 만들었습니다. <br/>
 * 양수 range, 음수 range, 이상 이하 초과 미만 경우의 수에 대한 패턴을 만들었습니다.<br/>
 * 그리고 초과와 이상을 사용함에 따라 end와 max의 기능이 섞여서 end메서드를 삭제하였습니다.<br/>
 * size는 long.MAX_VALUE 를 넘을 수도 있기 때문에 BigInteger로 처리하였습니다.<br/>
 * 또한, iterator에서 Math.addExact로 값을 더하고 이전값을 next로 사용하였는데 이전값을 사용한다는 점에서
 * Long.MAX_VALUE가 들어오면 경계값 문제가 생길 수 있어서 코드를 수정하였습니다.<br/><br/>
 *
 * <table>
 * <caption>Range Types</caption>
 * <tr><th>Definition               <th>Factory method
 * <tr><td>{@code {x | 0 < x < b}}  <td>{@link Range#positive positive}
 * <tr><td>{@code {x | a < x < 0}}  <td>{@link Range#negative negative}
 * <tr><td>{@code {x | a < x < b}}  <td>{@link Range#open open}
 * <tr><td>{@code {x | a <= x <= b}}<td>{@link Range#closed closed}
 * <tr><td>{@code {x | a < x <= b}} <td>{@link Range#openClosed openClosed}
 * <tr><td>{@code {x | a <= x < b}} <td>{@link Range#closedOpen closedOpen}
 * </table>
 * {@code Long.MIN_VALUE <= a , b <= Long.MAX_VALUE}
 */
public final class Range implements Iterable<Long> {
    private long startInclusive;
    private long endExclusive;

    private Range(long startInclusive, long endExclusive) {
        this.startInclusive = startInclusive;
        this.endExclusive = endExclusive;
        classInvariant();
    }

    /**
     * 1부터 특정 양의 정수까지의 범위를 생성하는 정적 팩토리 메서드
     * @param endInclusive 양수 범위의 끝 값
     * @return 1부터 {@code endInclusive}까지의 범위
     * @throws IllegalArgumentException {@code endInclusive}가 1보다 작은 경우
     */
    public static Range positive(long endInclusive) {
        return new Range(1, endInclusive);
    }

    /**
     * 특정 음의 정수부터 -1까지의 범위를 생성하는 정적 팩토리 메서드
     * @param startInclusive 음수 범위의 시작 값
     * @return {@code startInclusive}부터 -1까지의 범위
     */
    public static Range negative(long startInclusive) {
        return new Range(startInclusive, -1);
    }

    /**
     *
     * 시작 값부터 끝 값까지의 열린 범위를 생성하는 정적 팩토리 메서드<br/>
     *{@code {x | a < x < b}}<br/>
     * {@code Long.MIN_VALUE < a , b < Long.MAX_VALUE}
     * @param startInclusive 범위의 시작 값
     * @param endInclusive   범위의 끝 값
     * @return {@code startInclusive + 1}부터 {@code endInclusive - 1}까지의 범위
     * @throws ArithmeticException {@code startInclusive + 1}과 {@code endInclusive - 1}이 long 범위를 넘어갔을 경우
     */
    public static Range open(long startInclusive, long endInclusive) {
        return new Range(Math.addExact(startInclusive, 1), Math.subtractExact(endInclusive, 1));
    }

    /**
     * 시작 값부터 끝 값까지의 닫힌 범위를 생성하는 정적 팩토리 메서드<br/>
     *{@code {x | a <= x <= b}}<br/>
    * {@code Long.MIN_VALUE <= a , b <= Long.MAX_VALUE}
     * @param startInclusive 범위의 시작 값 (포함)
     * @param endInclusive   범위의 끝 값 (포함)
     * @return {@code startInclusive}부터 {@code endInclusive}까지의 범위
     */
    public static Range closed(long startInclusive, long endInclusive) {
        return new Range(startInclusive, endInclusive);
    }
    /**
     * 시작 값부터 끝 값-1 까지의 열린-닫힌 범위를 생성하는 정적 팩토리 메서드
     *
     * @param startInclusive 범위의 시작 값 (포함)
     * @param endInclusive   범위의 끝 값 (불포함)
     * @return {@code startInclusive + 1}부터 {@code endInclusive}까지의 범위
     * @throws ArithmeticException {@code startInclusive + 1}이 long 범위를 넘어갔을 경우
     */

    public static Range openClosed(long startInclusive, long endInclusive) {
        return new Range(Math.addExact(startInclusive, 1), endInclusive);
    }

    /**
     * 시작 값 +1부터 끝 값까지의 닫힌-열린 범위를 생성하는 정적 팩토리 메서드
     *
     * @param startInclusive 범위의 시작 값 (불포함)
     * @param endInclusive   범위의 끝 값 (포함)
     * @return {@code startInclusive}부터 {@code endInclusive - 1}까지의 범위
     * @throws ArithmeticException {@code endInclusive -1}이 long 범위를 넘어갔을 경우
     */
    public static Range closedOpen(long startInclusive, long endInclusive) {
        return new Range(startInclusive, Math.subtractExact(endInclusive, 1));
    }

    /**
     * 최대값이 최솟값보다 작아질 경우를 확인하는 메서드
     * @throws IllegalArgumentException 최대값이 최솟값보다 작아질 경우
     * @apiNote 최댓값이 최솟값보다 작은 경우 잘못된 인수가 들어온것이라고 판단해서 IllegalArgumentException을 던졌습니다.
     */
    private void classInvariant() {
        if (max() < min()) {
            throw new IllegalArgumentException("Range: " + this.min() + " > " + this.max());
        }
    }

    /**
     * 최대값을 리턴하는 메서드
     * @return 최대값
     */
    public long max() {
        return endExclusive;
    }

    /**
     * 최솟값을 리턴하는 메서드
     * @return 최솟값
     */
    public long min() {
        return this.startInclusive;
    }

    /**
     * range 범위의 크기를 반환하는 메서드
     *
     * @return range 범위의 크기
     * @apiNote 범위가 Long.MIN_VALUE부터 Long.MAX_VALUE까지 이므로 최대범위를 구해야하는 경우 Arithmetic exception이 발생합니다. 이 경우를 막기 위하여 size는
     * BigInteger로 계산하였습니다.<br/> 하지만 bigIntger는 연산속도가 느리므로 long 범위를 넘지 않는다면 {@link #max()}+1 에서 {@link #min()}을 빼는 방식을
     * 사용하는 것이 성능적으로 더 좋습니다.
     */
    public BigInteger size() {
        BigInteger max = BigInteger.valueOf(this.max());
        BigInteger min = BigInteger.valueOf(this.min());
        return max.subtract(min).add(BigInteger.ONE);
    }


    /**
     *
     * @return 최솟값부터 최대값까지의 연속적인 값들을 담은 이터레이터
     * @throws NoSuchElementException 최대값 다음 값을 호출하려고 하는 경우
     * @apiNote 원래 코드에서는 최대값을 호출하려고 하는 경우 Arithmetic Exception이 던져졌습니다.
     * 이 코드는 최대값일때를 boolean형 변수로 판단하는 로직을 추가하여 최대값을 호출할 수 있게 하였고, arithmetic Exception이 생길 가능성도 제거하였습니다.
     */
    public Iterator<Long> iterator() {
        return new Iterator<>() {

            private long current = min();
            private boolean isLongMaxValue;

            public boolean hasNext() {
                return current <= max() && !isLongMaxValue;
            }

            public Long next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Range.iterator()");
                }

                if (current == Long.MAX_VALUE) {
                    isLongMaxValue = true;
                    return current;
                }
                return current++;
            }
        };
    }
}