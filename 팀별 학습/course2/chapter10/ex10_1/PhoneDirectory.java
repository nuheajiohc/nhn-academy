package course2.chapter10.ex10_1;

import java.util.Map;
import java.util.TreeMap;

public class PhoneDirectory {
    private Map<String, String> phoneData;

    public PhoneDirectory() {
        this.phoneData = new TreeMap<>();
    }

    public void addPhoneData(String name, String number) {
        if (this.phoneData.containsKey(name)) {
            System.out.println("중복된 이름(\"" + name + "\")이 있어서 추가할 수 없습니다.");
            return;
        }
        this.phoneData.put(name, number);
    }

    public String getNumber(String name) {
        if (!this.phoneData.containsKey(name)) {
            System.out.println("전화번호부에 \"" + name + "\" 없음.");
            return null;
        }
        return this.phoneData.get(name);
    }

    public void getPhoneNumberList() {
        phoneData.forEach((name, number) -> {
            System.out.print("name : " + name);
            System.out.println("  number : " + number);
        });
    }

}
