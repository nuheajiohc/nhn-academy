package course2.chapter10.ex10_6.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {
    private InputView(){}

    public static void inputStartSign() throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            br.readLine();
        }
    }
}