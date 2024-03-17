package com.course2.chapter4;

public class Ex4_6 {

    static final int ROWS = 80;
    static final int COLUMNS = 80;

    static int currentRow;
    static int currentColumn;

    public static void main(String[] args) {
        final int SQUARE_SIZE = 5;
        Mosaic.open(ROWS, COLUMNS, SQUARE_SIZE, SQUARE_SIZE);
        Mosaic.setUse3DEffect(false);
        currentRow = ROWS / 2;
        currentColumn = COLUMNS / 2;
        while (true) {
            changeToGreen(currentRow, currentColumn);
            randomMove();
            Mosaic.delay(1);
        }
    }

    static void changeToGreen(int rowNum, int colNum) {
        int green = Mosaic.getGreen(rowNum, colNum) + 25;
        green = Math.min(green,255);
        Mosaic.setColor(rowNum, colNum, 0, green, 0);
    }

    static void randomMove() {
        int[][] directions = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        int directionNum = (int) (4 * Math.random());
        currentRow = (currentRow + directions[directionNum][0] + ROWS) % ROWS;
        currentColumn = (currentColumn + directions[directionNum][1] + ROWS) % ROWS;
    }
}