import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordsBook {
    private Map<String, String> wordsBook = new HashMap<>();

    public void generate(FileReader fileReader) {
        List<String> lines = fileReader.getFile();
        for (String line : lines) {
            try {
                addWord(line);
            } catch (IllegalArgumentException e) {
                System.out.println("[ " + line + " ] 해당라인에 " + e.getMessage());
            }
        }
    }

    private void addWord(String line) {
        String[] splitedLine = line.split("\\s+");
        int splitedIndex = findIndexBetweenWordAndMeaning(splitedLine);

        String word = convertArrayToString(splitedLine, 1, splitedIndex);
        if (word.isEmpty()) {
            throw new IllegalArgumentException("영단어가 존재하지 않습니다.");
        }
        String meaning = convertArrayToString(splitedLine, splitedIndex, splitedLine.length);

        wordsBook.put(word, meaning);
    }

    private int findIndexBetweenWordAndMeaning(String[] splitedLine) {
        for (int i = 1; i < splitedLine.length; i++) {
            char c = splitedLine[i].charAt(0);
            if ((c >= '가' && c <= '힣') || c == '-' || c == '~' || c == '(' || Character.isDigit(c)) {
                return i;
            }
        }

        throw new IllegalArgumentException("한글 뜻이 존재하지 없습니다.");
    }

    private String convertArrayToString(String[] array, int startIndex, int endIndex) {
        StringBuilder result = new StringBuilder();
        for (int i = startIndex; i < endIndex; i++) {
            result.append(array[i]).append(" ");
        }
        return result.toString().trim();
    }

    public String getMeaning(String word) {
        if (!wordsBook.containsKey(word)) {
            throw new IllegalArgumentException("단어가 단어장에 등록되지 않았습니다.");
        }
        return wordsBook.get(word);
    }
}
