package course2.chapter8.ex8_1;

public class Equation {

    private double firstNumber;
    private double secondNumber;
    private double thirdNumber;

    public Equation(double firstNumber, double secondNumber, double thirdNumber) {
        if (firstNumber == 0) {
            throw new IllegalArgumentException("첫 번째 계수는 0이 될 수 없습니다.");
        }
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.thirdNumber = thirdNumber;
    }

    public double root() {
        double temp = Math.pow(secondNumber, 2) - 4 * firstNumber * thirdNumber;
        boolean hasRoot = temp >= 0;
        if (!hasRoot) {
            throw new IllegalArgumentException("방정식이 근을 가지지 않습니다.");
        }
        return (-secondNumber + Math.sqrt(temp)) / (2 * firstNumber);
    }
}