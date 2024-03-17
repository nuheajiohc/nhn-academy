package course2.chapter5.ex5_1;

public class Dice {
    private int[] dices;
    private int MAX_DICE_EYE = 6;

    public Dice(int number) {
        this.dices = new int[number];
    }

    public void roll() {
        for(int i=0; i<this.dices.length; i++){
            dices[i]=(int) (Math.random() * MAX_DICE_EYE) + 1;
        }
    }

    public int getDice(int number) {
        return this.dices[number];
    }

    public String toString() {
        String diceString = "";
        for (int i = 1; i <= this.dices.length; i++) {
            diceString += i + "번째 주사위 번호 : " + this.dices[i - 1] + "\n";
        }
        return diceString;
    }
}
