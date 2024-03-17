package course2.chapter7.ex7_2;

import java.util.Arrays;

public class TwoDimensionArray {
    private int[][] array;
    private int columns;
    private int rows;

    public TwoDimensionArray(int columns, int rows) {
        if (columns <= 0 || rows <= 0) {
            throw new IllegalArgumentException("행과 열은 양의 정수로 입력해주세요.");
        }

        this.array = new int[columns][rows];
        this.columns = columns;
        this.rows = rows;

        int maxValue = 20;
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                array[i][j] = (int) (Math.random() * maxValue) + 1;
            }
        }
    }

    public void printArray() {
        for (int[] column : array) {
            System.out.println(Arrays.toString(column));
        }
    }

    public void transposeArray() {
        swapColumnRow();
        int[][] transposedArray = new int[columns][rows];
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                transposedArray[i][j] = array[j][i];
            }
        }
        array = transposedArray;
    }

    private void swapColumnRow() {
        int temp = rows;
        rows = columns;
        columns = temp;
    }

}
