package course2.chapter7.ex7_1;

public class Main {
    public static void main(String[] args) {
        test(3, 10);
        test(10, 10);
        test(11, 10);
        test(-1, 10);
    }

    public static void test(int count, int maxValue) {
        try {
            ListGenerator generator = new ListGenerator(count, maxValue);
            System.out.println(generator.getNumbersToString());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
