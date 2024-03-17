package course2.chapter7.ex7_1;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ListGenerator {
    private List<Integer> numberList;

    public ListGenerator(int count, int maxValue) {
        if (count > maxValue) {
            throw new IllegalArgumentException("최대값보다 많은 개수를 요소로 추가할 수는 없습니다.");
        }
        if (count < 0) {
            throw new IllegalArgumentException("개수가 0보다 작을수는 없습니다.");
        }

        numberList = new ArrayList<>();
        addUniqueRandomNums(count, maxValue);
    }

    private void addUniqueRandomNums(int count, int maxValue) {
        Set<Integer> uniqueNums = new LinkedHashSet<>();
        while (uniqueNums.size() < count) {
            int value = (int) (Math.random() * maxValue) + 1;
            uniqueNums.add(value);
        }
        numberList.addAll(uniqueNums);
    }

    public String getNumbersToString() {
        return numberList.toString();
    }
}
