package com.course2.chapter6;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;

public class Ex6_1 extends Application {

    public static void main(String[] args) {
        launch();
    }

    private boolean dragging;

    private double prevShapeX, prevShapeY;

    private GraphicsContext canvasGraphics;

    public void start(Stage stage) {
        Canvas canvas = new Canvas(500,380);
        canvasGraphics = canvas.getGraphicsContext2D();
        canvasGraphics.setFill(Color.WHITE);
        canvasGraphics.fillRect(0,0,500,380);
        canvasGraphics.setStroke(Color.BLACK);

        canvas.setOnMousePressed( e -> pressMouse(e) );
        canvas.setOnMouseDragged( e -> dragMouse(e) );

        BorderPane root = new BorderPane(canvas);
        root.setStyle("-fx-border-color: black; -fx-border-width: 2px");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Mouse Drag Demo");
        stage.setResizable(false);
        stage.show();
    }

    public void pressMouse(MouseEvent evt) {

        if ( evt.getButton() == MouseButton.SECONDARY ) {
            dragging = false;
            canvasGraphics.setFill(Color.WHITE);
            canvasGraphics.fillRect(0,0,500,380);
            return;
        }

        dragging = true;

        double x = evt.getX();
        double y = evt.getY();

        prevShapeX = x;
        prevShapeY = y;

        if ( evt.isShiftDown() ) {
            canvasGraphics.setFill(Color.BLUE);
            canvasGraphics.fillOval( x - 30, y - 15, 60, 30 );
            canvasGraphics.strokeOval( x - 30, y - 15, 60, 30 );
        }
        else {
            canvasGraphics.setFill(Color.RED);
            canvasGraphics.fillRect( x - 30, y - 15, 60, 30 );
            canvasGraphics.strokeRect( x - 30, y - 15, 60, 30 );
        }

    }

    public void dragMouse(MouseEvent evt) {
        if ( dragging == false ) {
            return;
        }

        double x = evt.getX();
        double y = evt.getY();

        if ( Math.abs(x - prevShapeX) < 5 && Math.abs(y - prevShapeY) < 5 ) {
            return;
        }

        prevShapeX = x;
        prevShapeY = y;

        if (evt.isShiftDown() ) {
            canvasGraphics.setFill(Color.BLUE);
            canvasGraphics.fillOval( x - 30, y - 15, 60, 30 );
            canvasGraphics.strokeOval( x - 30, y - 15, 60, 30 );
        }
        else {
            canvasGraphics.setFill(Color.RED);
            canvasGraphics.fillRect( x - 30, y - 15, 60, 30 );
            canvasGraphics.strokeRect( x - 30, y - 15, 60, 30 );
        }

    }
}