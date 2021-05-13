module JavaFxApplication {
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.controls;

    opens View;
    opens Main;
    opens Controller;
}