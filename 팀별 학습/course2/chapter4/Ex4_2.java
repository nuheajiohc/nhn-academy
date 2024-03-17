package course2.chapter4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex4_2 {
    public static void main(String[] args) {
        String hexValue = null;
        try {
            hexValue = inputFromShell();
        } catch (IOException e) {
            System.out.println("입력값이 잘못되었습니다.");
        }

        System.out.println(convertToHexValue(hexValue));
    }

    public static int convertToHexValue(String str) {
        int value = 0;
        for (int i = 0; i < str.length(); i++) {
            int hexVal = hexValue(str.charAt(i));
            if (hexVal == -1) {
                throw new ArithmeticException("올바른 16진수 입력값이 아닙니다.");
            }
            value = value * 16 + hexVal;
        }
        return value;
    }

    public static int hexValue(char value) {
        switch (value) {
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            case 'a':
            case 'A':
                return 10;
            case 'b':
            case 'B':
                return 11;
            case 'c':
            case 'C':
                return 12;
            case 'd':
            case 'D':
                return 13;
            case 'e':
            case 'E':
                return 14;
            case 'f':
            case 'F':
                return 15;
            default:
                return -1;
        }
    }

    public static String inputFromShell() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String value = reader.readLine();
        return value;
    }
}
