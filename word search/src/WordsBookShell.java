import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordsBookShell {
    private static WordsBook wordsBook = new WordsBook();

    public static void main(String[] args) {
        generateWordsBook();
        Scanner scanner = new Scanner(System.in);
        searchWord(scanner);
    }

    public static void generateWordsBook() {
        FileReader fileReader;
        final String FILE_PATH = "./src/words.txt";

        try {
            fileReader = new FileReader(FILE_PATH);
            wordsBook.generate(fileReader);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void searchWord(Scanner scanner) {
        String wordToSearch;
        while (true) {
            System.out.print("검색할 단어를 입력하세요: ('exit()'를 입력하면 프로그램이 종료됩니다.)");
            wordToSearch = scanner.nextLine();

            if (wordToSearch.equals("exit()")) {
                System.out.println("프로그램이 종료됩니다.");
                break;
            }

            try {
                System.out.println(wordsBook.getMeaning(wordToSearch));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
