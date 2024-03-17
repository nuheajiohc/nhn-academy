package course2.chapter11.ex11_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileHandler {

    private BufferedReader br;

    public void readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        br = new BufferedReader(new FileReader(file));
        System.out.print(file.getName());
    }

    public int countLine() throws IOException {
        int count = 0;
        while (br.readLine() != null) {
            count++;
        }
        return count;
    }
}
