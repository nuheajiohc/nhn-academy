package course2.chapter3.example;


public class Ex3_2 {
    public static void main(String[] args) {
        int[] nums = new int[10001];
        int max_divisors = 0;
        int max_count = 0;
        for (int i = 1; i < 10001; i++) {
            int count = 0;
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) {
                    count++;
                }
            }
            if (max_count < count) {
                max_divisors = i;
                max_count = count;
            }
        }
        System.out.println(max_divisors);
        System.out.println(max_count);
    }
}
