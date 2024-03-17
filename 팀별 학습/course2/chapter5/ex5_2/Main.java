package course2.chapter5.ex5_2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StatCalc statCalc = new StatCalc();
        while (true){
            System.out.print("숫자를 입력해주세요.(0 입력 시 종료) : ");
            double number;

            try{
                number = inputFromShell();
            }catch(InputMismatchException e){
                System.out.println("실수로 입력해주세요.");
                continue;
            }

            if(number==0){
                System.out.println("0을 입력했으므로 입력을 종료합니다.");
                break;
            }
            statCalc.enter(number);
        }

        System.out.println("-------------  통계 결과  ----------------");
        System.out.println("숫자 집합의 개수 : "+statCalc.getCount());
        System.out.println("숫자 집합의 합 : "+statCalc.getSum());
        System.out.println("숫자 집합 중 최대값 : "+statCalc.getMax());
        System.out.println("숫자 집합 중 최솟값 : "+statCalc.getMin());
        System.out.println("숫자 집합의 평균 : "+statCalc.getMean());
        System.out.println("숫자 집합의 표준편차 : "+statCalc.getStandardDeviation());
    }

    public static double inputFromShell() {
        Scanner scanner = new Scanner(System.in);
        double userInput = scanner.nextDouble();
        return userInput;
    }
}
