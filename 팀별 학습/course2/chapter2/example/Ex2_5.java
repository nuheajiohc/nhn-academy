package course2.chapter2.example;

import java.util.Scanner;

public class Ex2_5 {

    public static int gross = 144;
    public static int dozen = 12;

    public static void main(String[] args) {
        System.out.print("달갈 총 개수를 입력:");
        Scanner sc = new Scanner(System.in);
        int totalEggs = Integer.parseInt(sc.nextLine());
        int grossOfEggs = totalEggs / gross;
        totalEggs %= gross;
        int dozenOfEggs = totalEggs / dozen;
        totalEggs %= dozen;
        int leftEggs = totalEggs;
        System.out.println("계란 수는 총" + grossOfEggs + "개, 다스 " + dozenOfEggs + "개, " + leftEggs + "개입니다.");
    }
}
