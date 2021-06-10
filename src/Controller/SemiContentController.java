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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
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
        WantedAnalysis analysis = new WantedAnalysis();
        List<JobModel> jobs= analysis.oneStet();

        for(JobModel j:jobs){
            WebView browser = new WebView();
            WebEngine webEngine = browser.getEngine();

            HBox pane = (HBox)FXMLLoader.load(getClass().getResource("/View/FXML/wantedItemLayout.fxml"));
            pane.prefWidthProperty().bind(scroll.widthProperty());
            Label company = (Label) pane.lookup("#company");
            Hyperlink title = (Hyperlink) pane.lookup("#title");
            Label basicAddr = (Label) pane.lookup("#basicAddr");
            Label sal = (Label) pane.lookup("#sal");

            company.setText(j.getCompany());
            title.setText(j.getTitle());
            title.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    webEngine.load(j.getWantedInfoUrl());
                }
            });
            basicAddr.setText(j.getBasicAddr());
            sal.setText(j.getSal());
            scrollBox.getChildren().add(pane);
        }
    }
}
