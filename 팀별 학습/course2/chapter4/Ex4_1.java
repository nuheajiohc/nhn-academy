package course2.chapter4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ex4_1 {
    public static void main(String[] args) {
        String sentence = null;
        try {
            sentence = inputFromShell();
        } catch (IOException e) {
            System.out.println("입력이 잘못되었습니다.");
        }

        printCaptialized(sentence);

    }

//    public static void printCaptialized(String sentence) {
//        String result = "";
//        boolean isFirstLetter = true;
//        for (int i = 0; i < sentence.length(); i++) {
//            char ch = sentence.charAt(i);
////            if(Character.isLetter(ch) && isFirstLetter){
//            if (isFirstLetter) {
//                result += Character.toUpperCase(ch);
//                isFirstLetter = false;
//                continue;
//            }
//            result += ch;
//            if (ch == ' ') {
//                isFirstLetter = true;
//            }
////            else{
////                isFirstLetter =false;
////            }
//        }
//        System.out.println(result);
//    }

    public static void printCaptialized(String sentence) {
        StringTokenizer st = new StringTokenizer(sentence);
        while (st.hasMoreTokens()) {
            String word = st.nextToken();
            char firstAlphabet = Character.toUpperCase(word.charAt(0));
            System.out.print(firstAlphabet + word.substring(1) + " ");
        }
    }


    public static String inputFromShell() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sentence = reader.readLine();
        return sentence;
    }
}
