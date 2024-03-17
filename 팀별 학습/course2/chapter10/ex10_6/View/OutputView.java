package course2.chapter10.ex10_6.View;

public class OutputView {

    private OutputView() {
    }

    public static void printProgramGuideline() {
        System.out.println("1. 이 프로그램은 입력 파일을 선택합니다.");
        System.out.println("2. 파일에 나타나는 모든 단어의 목록과 각 단어가 나타나는 횟수가 기록됩니다.");
        System.out.println("3. 이 목록은 먼저 알파벳 순으로, 그런 다음 빈도 순으로 출력 됩니다.");
        System.out.println("4. 빈도가 높은 단어가 맨 위에 있고 가장 적게 나타나는 단어가 맨 아래에 있습니다.");
        System.out.println("5. 입력 파일을 읽은 후, 프로그램은 출력 파일을 선택하도록 요청합니다.");
        System.out.println("6. 파일을 선택하면 단어 목록이 해당 파일에 작성되고, 취소하면 목록이 표준 출력에 작성됩니다. 모든 단어는 소문자로 변환됩니다.");
        System.out.println(">> Enter를 누르면 프로그램이 시작됩니다.");
    }
}