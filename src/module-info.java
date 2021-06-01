module JavaFxApplication {
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.controls;
    requires commons.exec;
    requires org.apache.commons.io;
    requires com.fasterxml.jackson.databind;
    requires org.json;

    opens View;
    opens Main;
    opens Controller;
}