package com.course2.chapter4;

public class Ex4_7 {

    static final int ROWS = 40;
    static final int COLUMNS = 40;

    public static void main(String[] args) {
        final int SQUARE_SIZE = 10;
        Mosaic.setUse3DEffect(false);
        Mosaic.open(ROWS, COLUMNS, SQUARE_SIZE, SQUARE_SIZE);
        fillWithRandomColors();
        while (true) {
            int randomRow = (int) (ROWS * Math.random());
            int randomColumn = (int) (ROWS * Math.random());
            convertRandomNeighbor(randomRow, randomColumn);
            Mosaic.delay(1);
        }
    }

    static void fillWithRandomColors() {
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                int red = (int) (256 * Math.random());
                int green = (int) (256 * Math.random());
                int blue = (int) (256 * Math.random());
                Mosaic.setColor(row, column, red, green, blue);
            }
        }
    }

    static void convertRandomNeighbor(int row, int col) {
        int red = Mosaic.getRed(row, col);
        int green = Mosaic.getGreen(row, col);
        int blue = Mosaic.getBlue(row, col);

        int directionNum = (int) (4 * Math.random());
        int[][] directions = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        col = (col + directions[directionNum][0] + COLUMNS) % COLUMNS;
        row = (row + directions[directionNum][1] + ROWS) % ROWS;

        Mosaic.setColor(row, col, red, green, blue);
    }
}