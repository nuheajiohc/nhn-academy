package course2.chapter10.ex10_2;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    private static char operation;

    private static Set<Integer> leftSet = new TreeSet<>();
    private static Set<Integer> rightSet = new TreeSet<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        parse(input);
        if(canCalculateSet()){
            System.out.println(leftSet);
        }else{
            System.out.println("유효하지 않은 연산자입니다.");
        }
    }

    public static void parse(String input) {
        input = input.replaceAll("[\\[\\]\\s]", "");
        findOperation(input);
        String[] sets = input.split("[*+-]");
        parseSet(sets[0], leftSet);
        parseSet(sets[1], rightSet);
    }

    public static void findOperation(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '+' || input.charAt(i) == '*' || input.charAt(i) == '-') {
                operation = input.charAt(i);
                break;
            }
        }
    }

    private static void parseSet(String str, Set<Integer> set) {
        for (String num : str.split(",")) {
            set.add(Integer.valueOf(num));
        }
    }

    private static boolean canCalculateSet() {
        switch (operation) {
            case '+':
                return leftSet.addAll(rightSet);
            case '-':
                return leftSet.removeAll(rightSet);
            case '*':
                return leftSet.retainAll(rightSet);
            default:
                return false;
        }
    }
}
