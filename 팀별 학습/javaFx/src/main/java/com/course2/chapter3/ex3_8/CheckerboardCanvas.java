package com.course2.chapter3.ex3_8;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CheckerboardCanvas extends Canvas {

    public CheckerboardCanvas(double width, double height) {
        super(width, height);
    }

    public void drawCheckerBoard() {
        GraphicsContext g = getGraphicsContext2D();
        g.setFill(Color.RED);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setFill(Color.BLACK);
        int widthNum = 8;
        int heightNum = 8;
        for (int i = 0; i < heightNum; i++) {
            for (int j = 0; j < widthNum; j++) {
                if ((i + j) % 2 == 1) {
                    double tileWidthSize = getWidth() / widthNum;
                    double tileHeightSize = getHeight() / heightNum;
                    g.fillRect(j * tileWidthSize, i * tileHeightSize, tileWidthSize, tileHeightSize);
                }
            }
        }
    }
}
