package algorithm.least_common_multiple;

public class Main {

    public static void main(String[] args) {
        for (int i = 0; i < TEST_CASES.length; i++) {
            System.out.println("test case" + (i + 1) + "=" + test(TEST_CASES[i],
                    TEST_CASES_RESULT[i]));
        }
        System.out.printf("정답률 : %.3f", correct / TEST_CASES.length * 100);
    }

    public static int[][] TEST_CASES = { { 0, 3 }, { 1, 1 }, { 98, 49 }, { 2, 81 }, { 3, 7 }, { 19, 87 }, { 1, 40 } };

    public static int[] TEST_CASES_RESULT = { 0, 1, 98, 162, 21, 1653, 40 };

    public static double correct = 0;

    public static boolean test(int[] TEST_CASE, int TEST_CASES_RESULT) {
        if (solution(TEST_CASE[1], TEST_CASE[0]) == TEST_CASES_RESULT) {
            correct++;
            return true;
        }
        return false;
    }

    // solution
    private static int solution(int first, int second) {
        if (first == 0 || second == 0) {
            return 0;
        }
        int gcd = recursion(first, second);
        int gab = first * second / gcd;
        return gab;
    }

    private static int recursion(int first, int second) {
        if (second == 0) {
            return first;
        }
        return recursion(second, first % second);
    }
}