package course2.chapter9.ex9_1;

public class Main {
    public static void main(String[] args) {
        factorialTest();
        fibonacciTest();
    }

    public static void factorialTest() {
        System.out.println("팩토리얼 테스트");

        System.out.println(MathFormula.factorial("7"));
        System.out.println(MathFormula.factorial("0"));

        try {
            System.out.println(MathFormula.factorial("-1"));
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(MathFormula.factorial("1"));
    }

    public static void fibonacciTest(){
        System.out.println("피보나치 테스트");
        System.out.println(MathFormula.fibonacci("3"));
        System.out.println(MathFormula.fibonacci("0"));

        try {
            System.out.println(MathFormula.fibonacci("-1"));
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
}
