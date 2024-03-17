package course2.chapter10.ex10_6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import javax.swing.JFileChooser;

public class FileHandler {

    private FileHandler() {}

    public static File selectFileForReading() throws FileNotFoundException {
        return selectFile(chooser->chooser.showOpenDialog(null));
    }

    public static File selectFileForWriting() throws FileNotFoundException {
        return selectFile(chooser->chooser.showSaveDialog(null));
    }

    private static File selectFile(Function<JFileChooser,Integer> FileDialog) throws FileNotFoundException {
        JFileChooser chooser = new JFileChooser();
        int returnValue = FileDialog.apply(chooser);

        if (returnValue == chooser.APPROVE_OPTION) {
            System.out.println("파일을 선택하였습니다.");
            return chooser.getSelectedFile();
        } else if (returnValue == JFileChooser.CANCEL_OPTION) {
            System.out.println("파일선택창을 닫았습니다.");
            return null;
        } else {
            throw new FileNotFoundException("파일 선택에서 예외가 발생하였습니다.");
        }
    }

    public static List<String> readFile(File file) throws IOException {
        List<String> parsedFile = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line=br.readLine())!=null){
                parsedFile.add(line);
            }
        }
        return parsedFile;
    }

    public static void writeFile(File file,String words) throws IOException {
        try(BufferedWriter writer = new BufferedWriter((new FileWriter(file,true)))){
                writer.write(words);
                writer.newLine();
        }
    }
}
