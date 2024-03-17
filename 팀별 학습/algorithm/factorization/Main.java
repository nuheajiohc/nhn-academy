package algorithm.factorization;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int[] TEST_CASES = {
            1,
            60,
            256,
            325,
            20_160,
            3_456_789,
            10_000_001,
            1_234_567_890
    };

    private static final String[] TEST_CASES_RESULT = {
            "1 = 1",
            "2 * 2 * 3 * 5 = 60",
            "2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 = 256",
            "5 * 5 * 13 = 325",
            "2 * 2 * 2 * 2 * 2 * 2 * 3 * 3 * 5 * 7 = 20160",
            "3 * 7 * 97 * 1697 = 3456789",
            "11 * 909091 = 10000001",
            "2 * 3 * 3 * 5 * 3607 * 3803 = 1234567890"
    };

    public static void main(String[] args) {
        for (int i = 0; i < TEST_CASES.length; i++) {
            System.out.println("Test Case " + (i + 1) + " = " + test(TEST_CASES[i], TEST_CASES_RESULT[i]));
        }

        System.out.printf("정답률 = %.3f%%", (correct / TEST_CASES.length * 100));
    }

    private static double correct = 0;

    private static boolean test(int input, String result) {
        if (solution(input).equals(result)) {
            correct++;
            return true;
        }

        return false;
    }

    // public static String solution(int num) {
    // int origin = num;
    // List<Integer> list = new ArrayList<>();
    // StringBuilder sb = new StringBuilder();
    // int i = 2;
    // if (origin == 1) {
    // list.add(1);
    // } else {
    // while (origin >= 2) {
    // if (origin % i == 0) {
    // origin /= i;
    // list.add(i);
    // } else {
    // i += 1;
    // }

    // }
    // }

    // for (int n : list) {
    // sb.append(n);
    // sb.append(" * ");
    // }
    // sb.delete(sb.length() - 2, sb.length());
    // sb.append("= ").append(num);

    // return sb.toString();
    // }
    public static String solution(int num) {
        int origin = num;
        List<Integer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if (num == 1) {
            list.add(1);
        } else {
            recursion(list, 2, num);
        }

        for (int n : list) {
            sb.append(n);
            sb.append(" * ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("= ").append(origin);
        return sb.toString();
    }

    public static List<Integer> recursion(List<Integer> list, int i, int num) {
        if (num < 2) {
            return list;
        }

        if (num % i == 0) {
            list.add(i);
            return recursion(list, i, num / i);
        } else {
            return recursion(list, i + 1, num);
        }
    }
}