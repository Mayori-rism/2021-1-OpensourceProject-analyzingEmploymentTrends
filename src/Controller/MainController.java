package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.Chart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class MainController{
    @FXML
    private Pane navigationPane;
    @FXML
    private Pane contactPane;

    @FXML
    private AnchorPane mainLayoutPane;
    public MainController(){

    }
    @FXML
    public void initialize() throws IOException {
        Chart barChartLayout = (Chart) FXMLLoader.load(getClass().getResource("/View/BarChart.fxml"));
        contactPane.getChildren().add(barChartLayout);
        barChartLayout.prefWidthProperty().bind(contactPane.widthProperty());
        barChartLayout.prefHeightProperty().bind(contactPane.heightProperty());
    }
}
