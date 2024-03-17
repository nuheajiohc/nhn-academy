package com.course2.chapter6;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Ex6_2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private double redX = 10, redY = 10;
    private double blueX = 50, blueY = 10;
    private double squareLength = 30;
    private Canvas canvas;

    public void start(Stage stage) {
        canvas = new Canvas(300, 250);
        draw();

        canvas.setOnMousePressed(e -> mousePressed(e));
        canvas.setOnMouseDragged(e -> mouseDragged(e));
        canvas.setOnMouseReleased(e -> mouseReleased(e));
        Pane root = new Pane(canvas);
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(e->{
            if ( e.getCode() == KeyCode.ESCAPE ) {
                redX = 10;
                redY = 10;
                blueX = 50;
                blueY = 10;
                draw();
            }
        });

        stage.setScene(scene);
        stage.setTitle("Drag the squares!");
        stage.setResizable(false);
        stage.show();
    }

    public void draw() {
        GraphicsContext g = canvas.getGraphicsContext2D();
        drawBackgrond(g, Color.WHITE);
        drawSquare(g, Color.RED, redX, redY);
        drawSquare(g, Color.BLUE, blueX, blueY);
    }

    private void drawSquare(GraphicsContext g, Color color, double x, double y) {
        g.setFill(color);
        g.fillRect(x, y, squareLength, squareLength);
    }

    private void drawBackgrond(GraphicsContext g, Color color) {
        g.setFill(color);
        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private boolean isImpossibleDrag = true;

    private boolean isRedSqaure = false;
    private double offsetX;
    private double offsetY;

    public void mousePressed(MouseEvent event) {
        double currentX = event.getX();
        double currentY = event.getY();
        if (currentX >= redX && currentX <= redX + squareLength && currentY >= redY &&
                currentY <= redY + squareLength) {
            isImpossibleDrag = false;
            isRedSqaure =true;
            offsetX = currentX - redX;
            offsetY = currentY - redY;
        }
        if (currentX >= blueX && currentX <= blueX + squareLength && currentY >= blueY &&
                currentY <= blueY + squareLength) {
            isImpossibleDrag = false;
            isRedSqaure =false;
            offsetX = currentX - blueX;
            offsetY = currentY - blueY;
        }

    }

    private void pressSquare(double currentX, double currentY, double squareX, double squareY) {
        if (currentX >= squareX && currentX <= squareX + squareLength && squareY >= redY &&
                currentY <= squareY + squareLength) {
            isImpossibleDrag = false;
            offsetX = currentX - squareX;
            offsetY = currentY - squareY;
        }
    }


    public void mouseDragged(MouseEvent event) {
        if (isImpossibleDrag) {
            return;
        }

        double currentX = event.getX();
        double currentY = event.getY();
        if (isRedSqaure) {
            redX = currentX - offsetX;
            redY = currentY - offsetY;
        }
        else{
            blueX = currentX - offsetX;
            blueY = currentY - offsetY;
        }
        draw();
    }

    public void mouseReleased(MouseEvent event) {
        isImpossibleDrag = true;
    }
}
