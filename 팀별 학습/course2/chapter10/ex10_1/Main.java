package course2.chapter10.ex10_1;

public class Main {
    private static PhoneDirectory phoneDirectory = new PhoneDirectory();

    public static void main(String[] args) {
        addPhoneNumber();
        getPhoneNumber();
        getPhoneNumberList();
    }

    private static void addPhoneNumber() {
        phoneDirectory.addPhoneData("재훈", "1234-1234");
        phoneDirectory.addPhoneData("민주", "4321-6736");
        phoneDirectory.addPhoneData("한상", "8764-3425");
        phoneDirectory.addPhoneData("병민", "8345-9471");
        phoneDirectory.addPhoneData("재훈", "9875-3754");
    }

    private static void getPhoneNumber() {
        phoneDirectory.getNumber("재훈");
        phoneDirectory.getNumber("꾸꾸");
    }

    private static void getPhoneNumberList() {
        phoneDirectory.getPhoneNumberList();
    }
}
