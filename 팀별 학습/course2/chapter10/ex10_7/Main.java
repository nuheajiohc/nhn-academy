package course2.chapter10.ex10_7;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        while (true) {
            try {
                String input =View.inputGuideline();
                if (input.isEmpty()) {
                    System.out.println("종료");
                    View.close();
                    return;
                }
                calculator.selectCalculationType(input);

            } catch (NumberFormatException e) {
                System.out.println("실수를 입력해주세요.");
            } catch (IOException e) {
                System.out.println("입력에 예외가 생겼습니다.");
            } catch (IndexOutOfBoundsException e){
                System.out.println(e.getMessage());
            }
            catch (ParserError e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
