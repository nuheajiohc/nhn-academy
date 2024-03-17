package course2.chapter10.ex10_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class View {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static String inputGuideline() throws IOException {
        System.out.println("- 표현식이나 변수가 포함된 식을 입력해주세요.");
        System.out.println("- 표현식 입력 방식 : print <expression>  ex. print 3+4");
        System.out.println("- 변수 입력 방식 : let <variable> = <expression> ex. let x = 4");
        System.out.println("- 변수를 입력하면 표현식을 입력하게 됩니다.");
        System.out.println("- 프로그램을 끝내고 싶다면 enter를 눌러주세요.");
        System.out.print("입력 >> ");
        return br.readLine();
    }

    public static void close() throws IOException{
        br.close();
    }
}
