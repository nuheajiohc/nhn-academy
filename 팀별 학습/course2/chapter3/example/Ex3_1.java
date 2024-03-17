package course2.chapter3.example;

public class Ex3_1 {
    public static void main(String[] args) {
        int count = 0;
        while (true) {
            int firstDice = (int) (Math.random() * 6) + 1;
            int secondDice = (int) (Math.random() * 6) + 1;
            if (firstDice == 1 && secondDice == 1) {
                break;
            }
            count++;
        }
        System.out.println("snake eyes가 나올때까지 걸린 횟수 : " + count);
    }
}