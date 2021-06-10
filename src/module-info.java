module JavaFxApplication {
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.controls;
    requires commons.exec;
    requires org.apache.commons.io;
    requires com.fasterxml.jackson.databind;
    requires org.json;
    requires com.jfoenix;
    requires jxl;
    requires javafx.web;

    opens View.FXML;
    opens View.CSS;
    opens Main;
    opens Controller;
}