package course2.chapter7.ex7_3;

public class ArrayGenerator {

    private ArrayGenerator() {
    }

    public static int[] generateIntArray() {
        int maxIntArrayLength = 10000;
        int maxIntValue = 100;
        int randomLength = generateRandomNumber(1, maxIntArrayLength);
        int[] array = new int[randomLength];
        for (int i = 0; i < randomLength; i++) {
            array[i] = generateRandomNumber(1, maxIntValue);
        }
        return array;
    }

    public static String[] generateStringArray() {
        int maxStringArrayLength = 10000;
        int randomLength = generateRandomNumber(1, maxStringArrayLength);
        String[] array = new String[randomLength];
        for (int i = 0; i < randomLength; i++) {
            array[i] = generateRandomString();
        }
        return array;
    }

    private static String generateRandomString() {
        StringBuilder sb = new StringBuilder();
        int maxStringLength = 30;
        int randomLength = generateRandomNumber(1, maxStringLength);
        for (int i = 0; i < randomLength; i++) {
            char randomAlphabet = (char) generateRandomNumber(97, 122);
            sb.append(randomAlphabet);
        }
        return sb.toString();
    }

    private static int generateRandomNumber(int minValue, int maxValue) {
        return (int) (Math.random() * (maxValue - minValue)) + minValue;
    }
}
