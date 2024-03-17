package com.course2.chapter6;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Ex6_4 extends Ex6_3 {

    private Button rollButton;

    public static void main(String[] args) {
        launch();
    }

    public void start(Stage stage){
        canvas = new Canvas(100,100);
        draw();

        rollButton = new Button("roll");
        rollButton.setMaxWidth(1000);
        rollButton.setOnAction( e ->{
            roll();
            draw();
        } );

        BorderPane root = new BorderPane();
        root.setCenter(canvas);
        root.setBottom(rollButton);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dice!");
        stage.setResizable(false);
        stage.show();
    }

    protected void roll(){
        super.roll();
        rollButton.setDisable(true);
        startTime = System.nanoTime();
        timer.start();
    }


    private long startTime;

    private AnimationTimer timer = new AnimationTimer() {
        public void handle( long time ) {
            dice1 = (int)(Math.random()*6)+1;
            dice2 = (int)(Math.random()*6)+1;
            draw();
            if ( time - startTime >= 1_000_000_000 ) {
                timer.stop();
                rollButton.setDisable(false);
            }
        }
    };
}
