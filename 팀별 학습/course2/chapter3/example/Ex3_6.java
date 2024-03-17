package course2.chapter3.example;


import java.util.ArrayList;

public class Ex3_6 {
    public static void main(String[] args) {
        int maxNum = 0;
        ArrayList<Integer> divisors = new ArrayList<>();
        for (int i = 1; i < 10001; i++) {
            int tempNum = 0;
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) {
                    tempNum++;
                }
            }
            if (maxNum <= tempNum) {
                if (maxNum < tempNum)
                    divisors.clear();
                divisors.add(i);
                maxNum = tempNum;
            }
        }

        System.out.println("1부터 10000 사이의 정수 중에서,");
        System.out.println("약수의 최대 개수는 " + maxNum + "입니다.");
        System.out.println("약수의 개수가 가장 많은 숫자는 다음과 같습니다.");
        for (int divisor : divisors) {
            System.out.println(divisor);
        }
    }
}
