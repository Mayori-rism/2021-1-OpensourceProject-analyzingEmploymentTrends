package Controller;

import Main.WantedAnalysis;
import Model.JobModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class SemiContentController {
    @FXML
    private ScrollPane scroll;
    @FXML
    private VBox scrollBox;

    public SemiContentController(){
    }
    @FXML
    public void initialize() throws IOException {

    }

    public AnchorPane getSemi(Region layout, String title) throws IOException {
        AnchorPane semiContentLayout = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/FXML/SemiContentLayout.fxml"));
        semiContentLayout.getStyleClass().add("semiPane");

        Label semiTitle = (Label) semiContentLayout.lookup("#semiTitle");
        Label semiDate =  (Label) semiContentLayout.lookup("#semiDate");

        semiTitle.setText(title);
        semiDate.setText(LocalDate.now().toString());
        Pane p = (Pane) semiContentLayout.lookup("#contents");
        layout.prefWidthProperty().bind(p.widthProperty());
        layout.prefHeightProperty().bind(p.heightProperty());

        p.getChildren().add(layout);
        return semiContentLayout;
    }
    public AnchorPane getSemi(Node layout, String title) throws IOException {
        AnchorPane semiContentLayout = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/FXML/SemiContentLayout.fxml"));
        semiContentLayout.getStyleClass().add("semiPane");

        Label semiTitle = (Label) semiContentLayout.lookup("#semiTitle");
        semiTitle.setText(title);

        Pane p = (Pane) semiContentLayout.lookup("#contents");

        p.getChildren().add(layout);
        return semiContentLayout;
    }
}
