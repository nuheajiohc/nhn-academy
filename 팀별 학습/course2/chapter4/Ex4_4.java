package course2.chapter4;

public class Ex4_4 {
    public static void main(String[] args) {
        printHeader();
        printHorizonLine();
        for(int i=2;i<=12;i++){
            printData(i);
        }
    }

    public static double getAvg(int callNum,int sum){
        int numberOfDice=2;
        RollingSimulator simulator = new RollingSimulator(numberOfDice);
        double count=0;
        for(int i =0 ;i<callNum;i++){
            count+=simulator.countToAnswerSum(sum);
        }
        double avg = count/callNum;
        return avg;
    }

    public static void printData(int sum){
        System.out.print("    "+sum+"        ");
        System.out.print("       "+getAvg(10000,sum)+"    \n");
    }

    public static void printHeader(){
        System.out.println("주사위의 총합      평균 굴림 횟수");
    }

    public static void printHorizonLine(){
        System.out.println("----------      -----------");
    }
}
