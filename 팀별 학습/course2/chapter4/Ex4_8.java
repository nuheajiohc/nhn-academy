package course2.chapter4;

import java.util.ArrayList;
import java.util.Scanner;

public class Ex4_8 {

    private static int[] firstNumber = new int[10];
    private static int[] secondNumber = new int[10];
    private static int[] userAnswer = new int[10];
    private static int maxNum = 100;


    public static void main(String[] args) {
        createQuiz();
        administerQuiz();
        gradeQuiz();
    }
    public static void createQuiz() {
        for (int i = 0; i < firstNumber.length; i++) {
            firstNumber[i] = (int) (Math.random() * maxNum) + 1;
            secondNumber[i] = (int) (Math.random() * maxNum) + 1;
        }
    }

    public static void administerQuiz() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < firstNumber.length; i++) {
            System.out.print(firstNumber[i] + " + " + secondNumber[i] + " = ");
            int userInput = sc.nextInt();
            userAnswer[i]=userInput;
        }

    }

    public static void gradeQuiz() {
        int answerNumber=0;
        ArrayList<Integer> wrongNumbers = new ArrayList<>();
        for (int i = 0; i < firstNumber.length; i++) {
            if((firstNumber[i]+secondNumber[i])==userAnswer[i]){
                answerNumber++;
                continue;
            }
            wrongNumbers.add(i+1);
        }
        System.out.println(answerNumber+"개 맞혔습니다.");
        System.out.print("틀린 문제 : ");
        for(int num : wrongNumbers){
            System.out.print(num+"번 ");
        }
    }
}
