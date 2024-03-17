package course2.chapter9.ex9_6_7;

import java.util.Scanner;

public class Ex9_6_7 {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Calculator calculator;
        while(true){
            String expression = inputExpression();
            if(expression.isEmpty()){
                System.out.println("종료");
                scanner.close();
                break;
            }
            double x = inputXValue();
            try{
                calculator = new Calculator(expression);
                System.out.println(calculator.calculate(x));
            }catch(IllegalArgumentException | IndexOutOfBoundsException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static String inputExpression() {
        System.out.println("\n표현식을 입력해주세요. 프로그램을 끝내고 싶다면 enter를 눌러주세요.");
        System.out.print("\n입력 >> ");
        return scanner.nextLine();
    }

    public static double inputXValue(){
        System.out.println("\n x에 들어갈 값을 입력해주세요.");
        System.out.print("\n 입력 >>");
        double x =scanner.nextDouble();
        scanner.nextLine();
        return x;
    }
}
