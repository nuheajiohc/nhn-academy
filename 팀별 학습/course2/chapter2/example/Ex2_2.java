package course2.chapter2.example;

import java.util.ArrayList;

// 주사위 개수와 주사위 최대 숫자에서 둘의 의미는 다르지만 영어로 번역하면 number로 쓰게 돼서 의미가 겹칩니다. 어떻게 해결해야할까요?
// 주사위라는 클래스를 만들었는데 여기서 주사위들을 모두 관리하는 것이 맞는가요?

public class Ex2_2 {
    public static void main(String[] args) {
        Dice dice = new Dice(5, 12);
        dice.decideDiceNumber();
        ArrayList<Integer> dices = dice.getDicesNumber();

        for (int i = 1; i <= dices.size(); i++) {
            System.out.printf("%d번 주사위 숫자 : %d\n", i, dices.get(i - 1));
        }

        System.out.println(dice.getDiceNumberSum());
    }
}

class Dice {
    private int numberOfDice; // 주사위 개수
    private int maxDiceNumber; // 주사위 최대 숫자

    private ArrayList<Integer> dices = new ArrayList<>();

    public Dice(int numberOfDice, int maxDiceNumber) {
        this.numberOfDice = numberOfDice;
        this.maxDiceNumber = maxDiceNumber;
    }

    public void decideDiceNumber() {
        for (int count = 0; count < this.numberOfDice; count++) {
            int diceNumber = (int) (Math.random() * this.maxDiceNumber) + 1;
            this.dices.add(diceNumber);
        }
    }

    public ArrayList<Integer> getDicesNumber() {
        return this.dices;
    }

    public int getDiceNumberSum() {
        int sum = 0;
        for (int diceNumber : dices) {
            sum += diceNumber;
        }
        return sum;
    }

}