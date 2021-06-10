package Controller;

import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NavigationController {
    @FXML
    private VBox navigate;

    MainController mainController = new MainController();

    private List<Button> buttons = new ArrayList<>();
    public NavigationController(){
        Button semiButton = new Button();
        semiButton.setOnAction(event -> {
            try {
                mainController.addSemi();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        buttons.add(semiButton);
    }

    @FXML
    public void initialize() throws IOException {
        navigate.getChildren().addAll(buttons);
    }
}
