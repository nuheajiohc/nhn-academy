package course2.chapter10.ex10_6;

import course2.chapter10.ex10_6.View.InputView;
import course2.chapter10.ex10_6.View.OutputView;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        OutputView.printProgramGuideline();
        WordSort wordSort = new WordSort();

        try {
            InputView.inputStartSign();

            File fileForReading = FileHandler.selectFileForReading();
            List<String> parsedFile = FileHandler.readFile(fileForReading);

            wordSort.recordWordOrder(parsedFile);

            File fileForWriting = FileHandler.selectFileForWriting();
            FileHandler.writeFile(fileForWriting, wordsToString(wordSort));
        } catch (IOException e) {
            System.out.println("입력과정에서 예외가 발생하였습니다.");
        }
    }

    public static String wordsToString(WordSort wordSort) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("파일 안의 단어의 수 : %d%n%n", wordSort.countWord()));
        sb.append(String.format("알파벳 순서로 정렬%n%s%n%n", wordSort.sortByAlphabet()));
        sb.append(String.format("빈도순으로 정렬%n%s%n%n", wordSort.sortByFrequency()));
        return sb.toString();
    }
}
