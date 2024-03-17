package course2.chapter7.ex7_5;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        test();
        scanner.close();
    }

    public static void test() {
        double[] testArray;
        String[] splitedInput = inputFromShell().split("\\s+");

        testArray = Arrays.stream(splitedInput).mapToDouble(Double::parseDouble).toArray();
        System.out.println("original Array : " + Arrays.toString(testArray));
        testArray = Sort.sortByBubble(testArray);
        System.out.println("sorted Array : " + Arrays.toString(testArray));
    }

    public static String inputFromShell() {
        StringBuilder sb = new StringBuilder();
        double num;
        while (true) {
            try {
                num = scanner.nextDouble();
                if (num < 0) {
                    throw new InputMismatchException("양의 실수만 입력해주세요(0 입력시 종료)");
                }
                if (num == 0) {
                    break;
                }
                sb.append(num).append(" ");
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
        return sb.toString();
    }
}
