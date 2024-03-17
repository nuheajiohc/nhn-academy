package course2.chapter7.ex7_3;

public class Main {

    public static void main(String[] args) {

        int[] randomIntArray = ArrayGenerator.generateIntArray();
        String[] randomStringArray = ArrayGenerator.generateStringArray();

        System.out.println("Arrays.sort로 정렬하는 데 길리는 시간");
        System.out.printf("랜덤 숫자 배열 : %.5f초%n", TimeMeasure.getSortingTimeByMethod(randomIntArray.clone()));
        System.out.printf("랜덤 문자열 배열 : %.5f초%n", TimeMeasure.getSortingTimeByMethod(randomStringArray.clone()));

        System.out.println("선택정렬로 정렬하는 데 걸리는 시간");
        System.out.printf("랜덤 숫자 배열 정렬 : %.5f초%n", TimeMeasure.getSortingTimeBySelection(randomIntArray.clone()));
        System.out.printf("랜덤 문자열 배열 정렬 : %.5f초%n", TimeMeasure.getSortingTimeBySelection(randomStringArray.clone()));

    }
}
