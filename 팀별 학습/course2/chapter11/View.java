package course2.chapter11;

import java.util.Scanner;

public class View {
    public static String Input(){
        System.out.print("폴더 명을 입력해주세요 : ");

        try(Scanner scanner = new Scanner(System.in)){
            return scanner.nextLine();
        }
    }
}
