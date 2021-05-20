module JavaFxApplication {
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.controls;
    requires json.simple;

    opens View;
    opens Main;
    opens Controller;
}