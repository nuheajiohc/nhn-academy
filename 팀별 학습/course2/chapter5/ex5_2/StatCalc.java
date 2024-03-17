package course2.chapter5.ex5_2;

public class StatCalc {

    private int count;
    private double sum;
    private double squareSum;

    private double min = Double.MAX_VALUE;

    private double max = Double.MIN_VALUE;

    public void enter(double num) {
        countItem();
        addNum(num);
        updateSquareSum(num);
        updateMin(num);
        updateMax(num);
    }

    public void updateSquareSum(double num) {
        this.squareSum += num * num;
        boolean isOverOfRange =this.squareSum > Double.MAX_VALUE;
        if (isOverOfRange) {
            throw new ArithmeticException("제곱합이 유효범위를 넘었습니다.");
        }
    }

    public void updateMin(double num) {
        if (this.min > num) {
            this.min = num;
        }
    }

    public void updateMax(double num) {
        if (this.max < num) {
            this.max = num;
        }
    }

    public void addNum(double num) {
        this.sum += num;
        boolean isOverRange = this.sum > Double.MAX_VALUE;
        if (isOverRange) {
            throw new ArithmeticException("합이 유효범위를 넘었습니다.");
        }
    }

    public void countItem() {
        this.count += 1;
        boolean isOverRange =this.count > Integer.MAX_VALUE;
        if (isOverRange) {
            throw new ArithmeticException("항목 수가 유효범위를 초과하였습니다.");
        }
    }

    public int getCount() {
        return count;
    }

    public double getSum() {
        return sum;
    }

    public double getMean() {
        boolean isInvalidCount=this.count == 0;
        if (isInvalidCount) {
            throw new IllegalArgumentException("항목 수가 0개일 때는 getMean()을 호출할 수 없습니다");
        }
        return sum / count;
    }

    public double getStandardDeviation() {
        boolean isInvalidCount=this.count == 0;
        if (isInvalidCount) {
            throw new IllegalArgumentException("항목 수가 0개일 때는 getStandardDeviation()을 호출할 수 없습니다");
        }
        double mean = getMean();
        return Math.sqrt(squareSum / count - mean * mean);
    }

    public double getMax() {
        return this.max;
    }

    public double getMin() {
        return this.min;
    }

}