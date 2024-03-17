package course2.chapter2.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
// try catch를 사용할 때 try 안에 코드가 아래처럼 길어져도 괜찮은가요?
public class Ex2_7 {
    public static void main(String[] args) {

        File file = new File("course2/chapter2/example/testdata.txt");
        try{
            Scanner sc = new Scanner(file);
            String name=sc.nextLine();
            int scoreSum =0 ;
            double subjectNumber =0;
            while(sc.hasNext()){
                int score = sc.nextInt();
                scoreSum+=score;
                subjectNumber++;
            }
            double average = scoreSum/subjectNumber;
            System.out.println(name + "의 평균점수 : " + average);
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("파일을 읽는 도중 오류가 발생했습니다.");
        }
    }
}
