package course2.chapter8.ex8_1;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            excuteEquationProgram();
        } while (wantEquationInput());
        scanner.close();
    }

    public static boolean wantEquationInput() {
        System.out.println("다른 방정식을 입력하시겠습니까? (yes, no 로 입력해주세요)");
        String answer;
        while (true) {
            answer = scanner.nextLine();
            if (answer.equals("yes")) {
                return true;
            } else if (answer.equals("no")) {
                System.out.println("프로그램을 종료합니다.");
                return false;
            } else {
                System.out.println("yes나 no를 입력해주세요");
            }
        }
    }

    public static void excuteEquationProgram() {
        System.out.println("이차방정식 해를 출력하는 프로그램입니다.");
        System.out.println("ax^2 + bx + c에서 a,b,c를 공백을 기준으로 입력해주세요 (단, 입력은 실수만 가능 ex. 1 2 3) ");
        double[] doubles = inputFromShell();
        try {
            Equation equation = new Equation(doubles[0], doubles[1], doubles[2]);
            System.out.println(equation.root());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static double[] inputFromShell() {
        double[] doubles;
        while (true) {
            String input = scanner.nextLine();
            String[] splitedInput = input.split("\\s+");
            try {
                if (splitedInput.length != 3) {
                    throw new IllegalArgumentException();
                }
                doubles = Arrays.stream(splitedInput).mapToDouble(Double::parseDouble).toArray();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("공백을 기준으로 세 실수를 입력해주세요.");
            }
        }
        return doubles;
    }
}