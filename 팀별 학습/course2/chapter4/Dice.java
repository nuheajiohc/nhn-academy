package course2.chapter4;

public class Dice {
    private int diceEye;
    private int maxDiceEye;

    Dice(int maxDiceEye) {
        this.maxDiceEye = maxDiceEye;
        diceEye = (int) (Math.random() * maxDiceEye) + 1;
    }

    public void roll(){
        diceEye = (int) (Math.random() * maxDiceEye) + 1;
    }

    public int getEye() {
        return diceEye;
    }
}
