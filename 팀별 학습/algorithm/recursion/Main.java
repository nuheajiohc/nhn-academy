package algorithm.recursion;

public class Main {

    public static void main(String[] args) {
        for(int i=0; i<FACTORIAL_TEST_CASE.length;i++){
            System.out.println("test case"+ (i+1) + "=" +factorialTest(FACTORIAL_TEST_CASE[i],FACTORIAL_TEST_CASE_RESULT[i]));
        }
        System.out.println("정답률 : "+factorialCorrect/FACTORIAL_TEST_CASE.length*100+"%");

        for(int i=0; i<FIBONACCI_TEST_CASE.length;i++){
            System.out.println("test case"+ (i+1) + "=" +fibonacciTest(FIBONACCI_TEST_CASE[i],FIBONACCI_TEST_CASE_RESULT[i]));
        }
        System.out.println("정답률 : "+fibonacciCorrect/FIBONACCI_TEST_CASE.length*100+"%");
    }

    public static int[] FACTORIAL_TEST_CASE={0,5};
    public static long[] FACTORIAL_TEST_CASE_RESULT={1,120};
    public static int[] FIBONACCI_TEST_CASE={0,10};
    public static long[] FIBONACCI_TEST_CASE_RESULT={0,55};

    public static double factorialCorrect=0;
    public static double fibonacciCorrect=0;

    public static boolean factorialTest(int FACTORIAL_TEST_CASE,long FACTORIAL_TEST_CASE_RESULT){
        if(factorial(FACTORIAL_TEST_CASE)==FACTORIAL_TEST_CASE_RESULT){
            factorialCorrect++;
            return true;
        }
        return false;
    }

    public static boolean fibonacciTest(int FIBONACCI_TEST_CASE,long FIBONACCI_TEST_CASE_RESULT){
        if(fibonacci(FIBONACCI_TEST_CASE)==FIBONACCI_TEST_CASE_RESULT){
            fibonacciCorrect++;
            return true;
        }
        return false;
    }

    public static long factorial(int number) {
        switch(number){
            case 0:
            case 1:
                return 1;
            default:
                return number*factorial(number-1);
        }
    }

    public static long fibonacci(int number) {
        switch(number){
            case 0:
                return 0;
            case 1:
            case 2:
                return 1;
            default:
                return fibonacci(number-1)+fibonacci(number-2);
        }
    }
}