module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example to javafx.fxml;
    opens com.example.Table to javafx.base;
    opens com.example.Form to javafx.fxml;
    exports com.example;
}
