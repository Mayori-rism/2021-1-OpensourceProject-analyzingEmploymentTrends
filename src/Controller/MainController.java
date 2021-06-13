package Controller;

import Main.WantedAnalysis;
import Model.JobModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.Chart;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Flow;


public class MainController{
    @FXML
    private AnchorPane wrappingPane;
    @FXML
    private AnchorPane horizonPane;
    @FXML
    private Label titleLabel;
    @FXML
    private AnchorPane  contentsFrame;
    @FXML
    private VBox  contentsPane;
    @FXML
    private HBox  contents;
    @FXML
    private AnchorPane navigationPane;
    @FXML
    private VBox navigation;

    public MainController(){

    }@FXML
    public void initialize()
    throws IOException {
        Chart barChartLayout = (Chart) FXMLLoader.load(getClass().getResource("/View/FXML/Barchart.fxml"));

        addSemi(barChartLayout);
        addSemi(barChartLayout);
    }
    public void addSemi() throws IOException {
        AnchorPane semiContentLayout = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/FXML/SemiContentLayout.fxml"));
        semiContentLayout.getStyleClass().add("semiPane");
        contents.getChildren().add(semiContentLayout);
    }
    public void addSemi(Region layout) throws IOException {
        AnchorPane semiContentLayout = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/FXML/SemiContentLayout.fxml"));
        semiContentLayout.getStyleClass().add("semiPane");

        Pane p = (Pane) semiContentLayout.lookup("#contents");
        layout.prefWidthProperty().bind(p.widthProperty());
        layout.prefHeightProperty().bind(p.heightProperty());

        p.getChildren().add(layout);
        contents.getChildren().add(semiContentLayout);
    }

//    public void a(){
//        WantedAnalysis analysis = new WantedAnalysis();
//        List<JobModel> jobs= analysis.oneStet();
//
//        for(JobModel j:jobs){
//            WebView browser = new WebView();
//            WebEngine webEngine = browser.getEngine();
//
//            HBox pane = (HBox)FXMLLoader.load(getClass().getResource("/View/FXML/wantedItemLayout.fxml"));
//            pane.prefWidthProperty().bind(scroll.widthProperty());
//            Label company = (Label) pane.lookup("#company");
//            Hyperlink title = (Hyperlink) pane.lookup("#title");
//            Label basicAddr = (Label) pane.lookup("#basicAddr");
//            Label sal = (Label) pane.lookup("#sal");
//
//            company.setText(j.getCompany());
//            title.setText(j.getTitle());
//            title.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent e) {
//                    webEngine.load(j.getWantedInfoUrl());
//                }
//            });
//            basicAddr.setText(j.getBasicAddr());
//            sal.setText(j.getSal());
//            scrollBox.getChildren().add(pane);
//        }
//    }
}
