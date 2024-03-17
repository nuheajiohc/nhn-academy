package course2.chapter5.ex5_1;

public class DiceRolling {

    public static int numberOfDice = 2;

    public static int answerSum = 2;

    public static void main(String[] args) {
        System.out.println("두 주사위의 합이 "+ answerSum +"가 나올 때까지 걸린 횟수 : "+ countRollingDice());
    }

    public static int countRollingDice() {
        int count = 0;
        while (true) {
            count++;
            Dice dice = new Dice(numberOfDice);
            dice.roll();
            if(isAnswerSum(dice)){
                break;
            }
        }
        return count;
    }

    public static boolean isAnswerSum(Dice dice){
        int sum=0;
        for (int i = 0; i < numberOfDice; i++) {
            sum+=dice.getDice(i);
        }
        return sum==answerSum;
    }
}
