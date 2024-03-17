module com.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.course2.chapter4 to javafx.fxml;
    exports com.course2.chapter4;

    opens com.course2.chapter6 to javafx.fxml;
    exports com.course2.chapter6;

    exports com.course2.chapter3.ex3_8;
    opens com.course2.chapter3.ex3_8 to javafx.fxml;

    exports com.course2.chapter3.ex3_9;
    opens com.course2.chapter3.ex3_9 to javafx.fxml;
}