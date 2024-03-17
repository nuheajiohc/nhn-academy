package course2.chapter3.example;


import course2.textio.TextIO;

public class Ex3_5 {
    public static void main(String[] args) {

        try {
            TextIO.readFile("course2/chapter3/example/sales.dat");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        double sum = 0;
        double data;
        int index = 1;
        while (!TextIO.eof()) {
            String line = TextIO.getln();
            String[] lineList = line.split(":");
            String num = lineList[1];
            try {
                data = Double.parseDouble(num);
                sum += data;
                index++;
            } catch (NumberFormatException e) {
                System.out.println(index + "번 도시 " + lineList[0] + "의 데이터는 숫자가 아닙니다.");
            }
        }
        System.out.println(sum);

    }
}
