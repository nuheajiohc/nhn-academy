package course2.chapter7.ex7_3;

import java.util.Arrays;

public class Sort {

    private Sort(){}

    public static void sortByMethod(int[] array) {
        Arrays.sort(array);
    }

    public static void sortByMethod(String[] array) {
        Arrays.sort(array);
    }

    public static void sortBySelection(String[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i].compareTo(array[j]) > 0) {
                    String temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public static void sortBySelection(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
}
