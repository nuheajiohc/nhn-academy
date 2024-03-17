import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    private Scanner fileReader;

    public FileReader(String file) throws FileNotFoundException {
        fileReader = new Scanner(new File(file));
    }

    public List<String> getFile() {
        List<String> lines = new ArrayList<>();
        while (fileReader.hasNext()) {
            lines.add(fileReader.nextLine());
        }
        fileReader.close();
        return lines;
    }
}
