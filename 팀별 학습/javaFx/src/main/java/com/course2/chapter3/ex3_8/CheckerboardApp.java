package com.course2.chapter3.ex3_8;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CheckerboardApp extends Application {
    public static void main(String[] args) {
        launch();
    }

    public void start(Stage stage) {
        int width = 400;
        int height = 400;
        CheckerboardCanvas canvas = new CheckerboardCanvas(width, height);
        canvas.drawCheckerBoard();

        BorderPane root = new BorderPane(canvas);
        root.setStyle("-fx-border-width: 4px; -fx-border-color: #444");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("checkerboard");
        stage.setResizable(false);
        stage.show();
    }
}
