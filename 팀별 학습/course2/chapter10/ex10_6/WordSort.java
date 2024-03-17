package course2.chapter10.ex10_6;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class WordSort {

    private Map<String, WordData> wordTree;

    private static class WordData implements Comparable<WordData> {
        String word;
        int frequency;
        Set<Integer> linePositions;

        public WordData(String word, int linePosition) {
            this.word = word;
            this.frequency = 1;
            this.linePositions = new HashSet<>();
            this.linePositions.add(linePosition);
        }

        @Override
        public int compareTo(WordData anatoerWordData) {
            return anatoerWordData.frequency - this.frequency;
        }
    }

    public WordSort() {
        this.wordTree = new TreeMap<>();
    }

    public void recordWordOrder(List<String> parsedFile) {
        for (int i = 1; i <= parsedFile.size(); i++) {
            parseLine(i, parsedFile.get(i - 1));
        }
    }

    private void parseLine(int index, String line) {
        String[] splitedLine = line.split(" ");
        for (String word : splitedLine) {
            word = word.toLowerCase();
            boolean meetsCondition = word.length() >= 3 && !word.contains("the");
            if (meetsCondition) {
                putWord(word, index);
            }
        }
    }

    private void putWord(String word, int index) {
        WordData wordData = wordTree.get(word);
        if (wordData == null) {
            wordTree.put(word, new WordData(word, index));
        } else {
            wordData.frequency++;
            wordData.linePositions.add(index);
        }
    }

    public int countWord() {
        return wordTree.size();
    }

    public String sortByAlphabet() {
        StringBuilder sb = new StringBuilder();
        for (WordData wordData : wordTree.values()) {
            sb.append(String.format("%s (%d) %s%n", wordData.word, wordData.frequency, wordData.linePositions));
        }
        return sb.toString();
    }

    public String sortByFrequency() {
        StringBuilder sb = new StringBuilder();
        wordTree.values().stream().sorted(WordData::compareTo)
                .forEach(wordData -> sb.append(
                        String.format("%s (%d) %s%n", wordData.word, wordData.frequency, wordData.linePositions)));
        return sb.toString();
    }
}
