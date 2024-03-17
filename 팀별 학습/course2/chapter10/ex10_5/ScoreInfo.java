package course2.chapter10.ex10_5;

public class ScoreInfo{
    String lastName;
    String firstName;
    int score;

    public ScoreInfo(String lastName, String firstName, int score) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.score = score;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getScore() {
        return score;
    }
}
