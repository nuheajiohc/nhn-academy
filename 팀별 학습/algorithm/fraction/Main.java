package algorithm.fraction;

import java.math.BigDecimal;

public class Main {

    public static class Fraction {
        private int numerator;  // 분자
        private int denominator;    // 분모

        public Fraction(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;

            normalize();
        }

        public int getNumerator() {
            return numerator;
        }

        public int getDenominator() {

            return denominator;
        }

        public String toString(){
            return numerator+"/"+denominator;
        }
        public void normalize() {
            int gcd=getGcd(getNumerator(),getDenominator());
            this.numerator= getNumerator()/gcd;
            this.denominator = getDenominator()/gcd;
        }

        public int getGcd(int numerator, int denominator){
            if(denominator==0){
                return numerator;
            }
            return getGcd(denominator,numerator%denominator);
        }


    }

    /**
     * 소수점 값에 대해 자리 수 계산 하는 메서드.    <br/>
     * ex)  <br/>
     * 0.01 -> 100을 곱하기 위해 2를 return    <br/>
     * 0.1 -> 10을 곱하기 위해 1을 return
     *
     *
     * @param num   소수 값
     * @return      소수점 이하 자리 수 값
     */
    public static int getDecimalNumber(BigDecimal num) {
        String str = num.toPlainString();
        String[] strList =str.split("[.]");
        if(strList.length==2){
            return strList[1].length();
        }
        return 0;
    }

    public static String solution(String input) {
        BigDecimal number = new BigDecimal(input);

        int decimalNumber = getDecimalNumber(number);

        double denominator = Math.pow(10,decimalNumber);
        int numerator = (int)(number.doubleValue()*Math.pow(10,decimalNumber));

        return new Fraction(numerator, (int) denominator).toString();
    }

    public static void main(String[] args) {
        for(int i=0; i<TEST_CASES.length; i++){
            System.out.println("test case"+ (i+1) + "=" +test(TEST_CASES[i], TEST_CASES_RESULT[i]));
        }

        System.out.printf("정답률: %.3f%%", correct/TEST_CASES.length*100);
    }

    public static String[] TEST_CASES = {"0.1","0.3","0.25"};
    public static String[] TEST_CASES_RESULT = {"1/10","3/10","1/4"};

    public static double correct=0;

    public static boolean test(String TEST_CASE, String TEST_CASES_RESULT){
        if(solution(TEST_CASE).equals(TEST_CASES_RESULT)){
            correct++;
            return true;
        }
        return false;
    }


}