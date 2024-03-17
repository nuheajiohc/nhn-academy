package course2.chapter10.ex10_5;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {

    private static ScoreInfo[] scoreData = new ScoreInfo[] {
            new ScoreInfo("Smith", "John", 70),
            new ScoreInfo("Doe", "Mary", 85),
            new ScoreInfo("Page", "Alice", 82),
            new ScoreInfo("Cooper", "Jill", 97),
            new ScoreInfo("Flintstone", "Fred", 66),
            new ScoreInfo("Rubble", "Barney", 80),
            new ScoreInfo("Smith", "Judy", 48),
            new ScoreInfo("Dean", "James", 90),
            new ScoreInfo("Russ", "Joe", 55),
            new ScoreInfo("Wolfe", "Bill", 73),
            new ScoreInfo("Dart", "Mary", 54),
            new ScoreInfo("Rogers", "Chris", 78),
            new ScoreInfo("Toole", "Pat", 51),
            new ScoreInfo("Khan", "Omar", 93),
            new ScoreInfo("Smith", "Ann", 95)
    };

    public static void main(String[] args) {
//        printScoreAverage();
//        printStudentCountByCondition();
//        System.out.println(generateListByCondition());
//        getListByCondition();
//        printStudentByLastName();
        printStudentByScore();
    }

    public static void printStudentCount() {
        System.out.println(Arrays.stream(scoreData).count());
    }

    public static void printScoreAverage() {
        //noinspection OptionalGetWithoutIsPresent
        System.out.println(Arrays.stream(scoreData).mapToInt(data -> data.score).average().getAsDouble());
    }

    public static void printStudentCountByCondition() {
        System.out.println(Arrays.stream(scoreData).filter(student -> student.score >= 90).count());
    }

    private static List<String> generateListByCondition() {
        return Arrays.stream(scoreData).filter(student -> student.score < 70)
                     .map(student -> student.firstName + student.lastName)
                     .collect(toList());
    }

    public static void getListByCondition() {
        generateListByCondition().forEach(System.out::println);
    }

    public static void printStudentByLastName() {
        Arrays.stream(scoreData).sorted(Comparator.comparing(ScoreInfo::getLastName))
              .map(student -> student.getFirstName() + " " + student.getLastName() + " 점수 : "+student.getScore())
              .forEach(System.out::println);
    }

    public static void printStudentByScore() {
        Arrays.stream(scoreData).sorted(Comparator.comparing(ScoreInfo::getScore))
              .map(student -> student.getFirstName() + " " + student.getLastName() + " 점수 : "+student.getScore())
              .forEach(System.out::println);
    }
}
