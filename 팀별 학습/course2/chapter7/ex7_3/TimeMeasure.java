package course2.chapter7.ex7_3;

public class TimeMeasure {
    private TimeMeasure(){}

    public static double getSortingTimeByMethod(int[] array) {
        long startTime = System.nanoTime();
        Sort.sortByMethod(array);
        long endTime = System.nanoTime();
        return (endTime - startTime)/1000000000.0;
    }

    public static double getSortingTimeByMethod(String[] array) {
        long startTime = System.nanoTime();
        Sort.sortByMethod(array);
        long endTime = System.nanoTime();
        return (endTime - startTime)/1000000000.0;
    }

    public static double getSortingTimeBySelection(int[] array) {
        long startTime = System.nanoTime();
        Sort.sortBySelection(array);
        long endTime = System.nanoTime();
        return (endTime - startTime)/1000000000.0;
    }

    public static double getSortingTimeBySelection(String[] array) {
        long startTime = System.nanoTime();
        Sort.sortBySelection(array);
        long endTime = System.nanoTime();
        return (endTime - startTime)/1000000000.0;
    }
}
