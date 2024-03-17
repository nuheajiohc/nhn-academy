package course2.chapter4;

import java.util.Arrays;

public class Ex4_5 {
    public static ArrayProcessor max = (double[] array) -> {
        Arrays.sort(array);
        return array[array.length - 1];
    };
    public static ArrayProcessor min = (double[] array) -> {
        Arrays.sort(array);
        return array[0];
    };
    public static ArrayProcessor sum = (double[] array) -> {
        double sum = 0;
        for (double num : array) {
            sum += num;
        }
        return sum;
    };
    public static ArrayProcessor avg = (double[] array) -> {
        double avg = sum.apply(array) / array.length;
        return avg;
    };

    public static void main(String[] args) {
        System.out.println("최대값 : "+max.apply(new double[] {1,3,5,6,34,2}));
        System.out.println("최솟값 : "+min.apply(new double[] {1,3,5,6,34,2}));
        System.out.println("합 : "+sum.apply(new double[] {1,3,5,6,34,2}));
        System.out.println("평균 : "+avg.apply(new double[] {1,3,5,6,34,2}));
    }

}
