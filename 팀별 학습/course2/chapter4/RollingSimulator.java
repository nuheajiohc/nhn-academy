package course2.chapter4;

import java.util.ArrayList;
import java.util.List;

public class RollingSimulator {
    private List<Dice> dices;

    RollingSimulator(int numberOfDice) {
        int maxDiceEye = 6;
        dices = new ArrayList<>();
        for (int i = 0; i < numberOfDice; i++) {
            addDice(maxDiceEye);
        }
    }

    private void addDice(int maxDiceEye) {
        dices.add(new Dice(maxDiceEye));
    }

    public int getEyeSum() {
        int sum = 0;
        for (Dice dice : dices) {
            dice.roll();
            sum += dice.getEye();
        }
        return sum;
    }

    public int countToAnswerSum(int answerSum){
        int count=1;
        while(answerSum != getEyeSum()){
            count++;
        }
        return count;
    }
}
