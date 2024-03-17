package course2.chapter3.example;


public class Ex3_7 {
    public static void main(String[] args) {
        findSameBirthday();
        findBirthdayNumber();
        findUnique365Birthday();
    }

    public static void findSameBirthday() {
        int[] birthdays = new int[365];
        int count = 0;
        while (true) {
            int randomBirthday = (int) (Math.random() * 365);
            birthdays[randomBirthday]++;
            count++;
            if (birthdays[randomBirthday] == 3) {
                System.out.println("생일이 같은 사람을 찾기까지 걸린 횟수 : " + count);
                break;
            }
        }
    }

    public static void findBirthdayNumber() {
        boolean[] birthdays = new boolean[365];
        for (int i = 0; i < 365; i++) {
            int randomBirthday = (int) (Math.random() * 365);
            birthdays[randomBirthday] = true;
        }

        int count = 0;

        for (boolean hasBirthday : birthdays) {
            if (hasBirthday) {
                count++;
            }
        }
        System.out.println("무작위로 365명을 선택했을 때 생일의 개수 : " + count);
    }

    public static void findUnique365Birthday() {
        boolean[] birthdays = new boolean[365];
        int count = 0;
        int noBirthdaysNum = 365;
        while (true) {
            count++;
            int randomBirthday = (int) (Math.random() * 365);
            boolean isNotBirhdays = !birthdays[randomBirthday];
            if (isNotBirhdays) {
                noBirthdaysNum--;
                birthdays[randomBirthday] = true;
            }
            if (noBirthdaysNum == 0) {
                System.out.println("365일 모두 생일이 있도록 만들기까지 찾아야 할 사람 수: " + count);
                break;
            }

        }
    }
}
