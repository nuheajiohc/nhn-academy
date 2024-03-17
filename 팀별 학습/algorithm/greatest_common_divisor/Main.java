package algorithm.greatest_common_divisor;

public class Main {

    public static int[][] TEST_CASES = {
            { 10, 20, 30, 40 }, { 5, 7, 12 }
    };

    public static int[] TEST_CASES_RESULT = { 20, 1 };

    public static void main(String[] args) {
        for (int i = 0; i < TEST_CASES.length; i++) {
            System.out.println("Test Case " + (i + 1) + " = " + test(TEST_CASES[i], TEST_CASES_RESULT[i]));
        }
        System.out.printf("정답률 : %.3f", correct / TEST_CASES.length * 100);
    }

    public static double correct = 0;

    public static boolean test(int[] test_case, int test_case_result) {
        if (solution(test_case) == test_case_result) {
            correct += 1;
            return true;
        }
        return false;

    }

    public static int solution(int[] input) {
        int max = 0;
        for (int i = 0; i < input.length - 1; i++) {
            for (int j = i + 1; j < input.length; j++) {
                int value = recursion(input[i], input[j]);
                if (max < value) {
                    max = value;
                }
            }
        }
        return max;
    }

    public static int recursion(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return recursion(b, a % b);
    }

}