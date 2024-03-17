package course2.chapter3.example;


import java.util.Scanner;

public class Ex3_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String temp = "";
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
                temp += ch;
            } else {
                if (temp != "")
                    System.out.println(temp);
                temp = "";
            }
        }
    }
}
