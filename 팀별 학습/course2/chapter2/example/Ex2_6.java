package course2.chapter2.example;

import java.util.Scanner;

public class Ex2_6 {
    public static void main(String[] args) {
        System.out.print("입력값을 공백을 기준으로 입력하세요.\n? ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] words = input.split(" ");
        String initials = "";

        for (int i = 0; i < words.length; i++) {
            System.out.println((i + 1) + "번 째 단어는 " + words[i] + "이고, " + words[i].length() + " 글자입니다.");
            initials += words[i].charAt(0);
        }

        System.out.println("당신의 이니셜은 " + initials + "입니다.");

    }
}
