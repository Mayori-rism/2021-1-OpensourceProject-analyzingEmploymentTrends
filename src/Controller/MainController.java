package Controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.Chart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.IOException;


public class MainController{
    @FXML
    private AnchorPane wrappingPane;
    @FXML
    private HBox horizonPane;
    @FXML
    private VBox contentsPane;
    @FXML
    private Label titleLabel;
    @FXML
    private Pane contentsFrame;
    @FXML
    private AnchorPane navigationPane;


    public MainController(){

    }@FXML
    public void initialize()
    throws IOException {
        Chart barChartLayout = (Chart) FXMLLoader.load(getClass().getResource("/View/FXML/Barchart.fxml"));

        addSemi(barChartLayout);
    }
    public void addSemi() throws IOException {
        AnchorPane semiContentLayout = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/FXML/SemiContentLayout.fxml"));
        semiContentLayout.getStyleClass().add("semiPane");
        contentsFrame.getChildren().add(semiContentLayout);
    }
    public void addSemi(Region layout) throws IOException {
        AnchorPane semiContentLayout = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/FXML/SemiContentLayout.fxml"));
        semiContentLayout.getStyleClass().add("semiPane");

        Pane p = (Pane) semiContentLayout.lookup("#contents");
        layout.prefWidthProperty().bind(p.widthProperty());
        layout.prefHeightProperty().bind(p.heightProperty());
        p.getChildren().add(layout);
        contentsFrame.getChildren().add(semiContentLayout);
    }
}
