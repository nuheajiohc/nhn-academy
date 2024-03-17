package com.course2.chapter6;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Ex6_3 extends Application {


    protected Canvas canvas;
    protected int dice1 = 4;
    protected int dice2 =3;

    public static void main(String[] args) {
        launch();
    }

    public void start(Stage stage) {
        canvas = new Canvas(100, 100);
        draw();
//
        canvas.setOnMousePressed(e -> {
            roll();
            draw();
        });
//
        Pane root = new Pane(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dice");
        stage.setResizable(false);
        stage.show();
    }

    protected void draw() {
        GraphicsContext g = canvas.getGraphicsContext2D();
        drawBackgrond(g, Color.SKYBLUE);
        drawDice(g, dice1, 10, 10);
        drawDice(g, dice2, 55, 55);

    }

    public void drawBackgrond(GraphicsContext g, Color color) {
        g.setFill(color);
        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void drawDice(GraphicsContext g, int val, int x, int y) {
        g.setFill(Color.WHITE);
        g.fillRect(x, y, 30, 30);
        g.setFill(Color.BLACK);
        g.setStroke(Color.BLACK);
        g.strokeRect(x, y, 30, 30);

        if(val>1){
            g.fillOval(x+3,y+3,7,7);
            g.fillOval(x+20,y+20,7,7);
        }
        if(val>3){
            g.fillOval(x+20,y+3,7,7);
            g.fillOval(x+3,y+20,7,7);
        }
        if(val%2==1){
            g.fillOval(x+12,y+11.5,7,7);
        }
        if(val==6){
            g.fillOval(x+3,y+11.5,7,7);
            g.fillOval(x+20,y+11.5,7,7);
        }
    }

    protected void roll(){
        dice1 = (int)(Math.random()*6)+1;
        dice2 = (int)(Math.random()*6)+1;
    }
}

