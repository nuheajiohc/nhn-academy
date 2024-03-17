package course2.chapter3.example;


import course2.textio.TextIO;

public class Ex3_3 {

    public static void main(String[] args) {
        while (true) {
            System.out.print("첫 번째 숫자를 입력해주세요.");
            double firstNum = TextIO.getDouble();
            if (firstNum == 0) {
                break;
            }
            System.out.print("두 번째 숫자를 입력해주세요.");
            double secondNum = TextIO.getDouble();
            System.out.println("연산자를 입력해주세요.");
            char operator = TextIO.getChar();

            try {
                double result = calculator(firstNum, secondNum, operator);
                System.out.printf(firstNum + " " + operator + " " + secondNum + " = " + result);
            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static double calculator(double firstNum, double secondNum, char operator) {
        double result = 0;
        switch (operator) {
            case '+':
                result = firstNum + secondNum;
                break;
            case '-':
                result = firstNum - secondNum;
                break;
            case '*':
                result = firstNum * secondNum;
                break;
            case '/':
                if (secondNum == 0) {
                    throw new ArithmeticException("0으로 나눌 수는 없습니다.");
                }
                result = firstNum / secondNum;
                break;
            default:
                throw new ArithmeticException("잘못된 연산자를 입력하셨습니다.");
        }

        if (result > Double.MAX_VALUE || result < Double.MIN_VALUE)
            throw new ArithmeticException("double 범위안의 값이 아닙니다.");

        return result;
    }

}
