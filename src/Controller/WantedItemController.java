package Controller;

import Main.WantedAnalysis;
import Model.JobModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.List;

public class WantedItemController {
    @FXML
    private Label company;
    @FXML
    private Hyperlink title;
    @FXML
    private Label basicAddr;
    @FXML
    private Label sal;

    public WantedItemController(){

    }
    @FXML
    public void initialize() throws IOException {

    }
}

