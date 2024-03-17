package com.course2.chapter3.ex3_9;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MotionAnimationApp extends Application {
    public static void main(String[] args) {
        launch();
    }

    public void start(Stage stage) {
        double width = 320;
        double height = 120;
        MotionFrameCanvas canvas = new MotionFrameCanvas(width, height);

        new AnimationTimer() {
            private int frameNum;
            private long startTime = -1;
            private long previousTime;

            public void handle(long now) {
                if (startTime < 0) {
                    startTime = previousTime = now;
                    canvas.drawFrame(frameNum);
                } else if (now - previousTime > 0.95e9 / 60) {
                    frameNum++;
                    canvas.drawFrame(frameNum);
                    previousTime = now;
                }
            }
        }.start();

        BorderPane root = new BorderPane(canvas);
        root.setStyle("-fx-border-width: 4px; -fx-border-color: #444");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("motion Animation");
        stage.setResizable(false);
        stage.show();
    }
}
