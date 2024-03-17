package course2.chapter9.ex9_1;

import java.math.BigInteger;

public class MathFormula {

    private MathFormula() {
    }

    public static BigInteger factorial(String number) {
        BigInteger n = new BigInteger(number);
        if (n.signum() == -1) {
            throw new ArithmeticException("입력은 0 이상으로 해주세요.");
        }
        return factorialRecursion(n);
    }

    private static BigInteger factorialRecursion(BigInteger n) {
        if (n.equals(BigInteger.ONE) || n.equals(BigInteger.ZERO)) {
            return BigInteger.ONE;
        }
        return n.multiply(factorialRecursion(n.subtract(BigInteger.ONE)));
    }

    public static BigInteger fibonacci(String number) {
        BigInteger n = new BigInteger(number);
        if (n.signum()== -1) {
            throw new ArithmeticException("입력은 0이상으로 해주세요.");
        }
        return fibonacciRecursion(n);
    }

    private static BigInteger fibonacciRecursion(BigInteger n) {
        if (n.equals(BigInteger.ONE) || n.equals((BigInteger.ZERO))) {
            return BigInteger.ONE;
        }
        return fibonacciRecursion(n.subtract(BigInteger.ONE)).add(fibonacciRecursion(n.subtract(BigInteger.TWO)));
    }
}
