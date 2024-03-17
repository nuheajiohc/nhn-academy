package com.course2.chapter3.ex3_9;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MotionFrameCanvas extends Canvas {
    private final int squareLength;

    private final GraphicsContext g;

    public MotionFrameCanvas(double width, double height) {
        super(width, height);
        int squareNum = 6;
        squareLength = (int) height / squareNum;
        g = getGraphicsContext2D();
    }

    public void drawFrame(int frameNumber) {
        g.setFill(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        drawCyclicFrame(Color.RED, 0, 1, frameNumber);
        drawCyclicFrame(Color.GREEN, 20, 2, frameNumber);
        drawCyclicFrame(Color.BLUE, 40, 3, frameNumber);
        drawOscillatingFrame(Color.CYAN, 60, 1, frameNumber);
        drawOscillatingFrame(Color.MAGENTA, 80, 2, frameNumber);
        drawOscillatingFrame(Color.YELLOW, 100, 3, frameNumber);

        drawSplitLine();
    }

    private void drawSplitLine() {
        g.setStroke(Color.BLACK);
        for (int i = squareLength; i < getWidth(); i += squareLength) {
            g.strokeLine(0, i, getWidth(), i);
        }
    }

    private void drawCyclicFrame(Color color, double coordinateY, int speed, int frameNumber) {
        double coordinateX = (speed * frameNumber) % getWidth();
        g.setFill(color);
        g.fillRect(coordinateX, coordinateY, squareLength, squareLength);
    }

    private void drawOscillatingFrame(Color color, double coordinateY, int speed, int frameNumber) {
        g.setFill(color);
        double coordinateX = (speed * frameNumber) % (2 * (getWidth() - squareLength));
        if (coordinateX + squareLength < getWidth()) {
            g.fillRect(coordinateX, coordinateY, squareLength, squareLength);
        } else {
            g.fillRect(2 * (getWidth() - squareLength) - coordinateX, coordinateY, squareLength, squareLength);
        }
    }
}
