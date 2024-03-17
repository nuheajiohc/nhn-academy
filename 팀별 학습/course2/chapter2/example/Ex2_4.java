package course2.chapter2.example;


import java.util.Scanner;

public class Ex2_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("쿼터 수:");
        int quarters = Integer.parseInt(sc.nextLine());
        System.out.print("다임 수:");
        int dimes = Integer.parseInt(sc.nextLine());
        System.out.print("니켈 수:");
        int nickels = Integer.parseInt(sc.nextLine());
        System.out.print("페니 수:");
        int pennies = Integer.parseInt(sc.nextLine());

        System.out.println(quarters * 0.25 + dimes * 0.1 + nickels * 0.05 + pennies * 0.01);
    }
}
