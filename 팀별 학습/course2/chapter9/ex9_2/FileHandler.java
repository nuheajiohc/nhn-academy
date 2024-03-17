package course2.chapter9.ex9_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static List<String> loadFile(String inputFilePath) throws IOException {
        List<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String str;
            while ((str = br.readLine()) != null) {
                words.add(str);
            }
        }
        return words;
    }

    public static void writeToFile(String sortedWords, String outputFilePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            writer.write(sortedWords);
            writer.newLine();
        }
    }
}
