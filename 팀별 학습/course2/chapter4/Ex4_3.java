package course2.chapter4;

public class Ex4_3 {
    public static void main(String[] args) {
        int numberOfDice = 2;
        int answerSum=2;
        RollingSimulator simulator = new RollingSimulator(numberOfDice);
        System.out.println("뱀눈(주사위 합 2)이 나오기까지 걸린 횟수 : " + simulator.countToAnswerSum(answerSum));
    }
}