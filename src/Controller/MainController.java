package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.chart.Chart;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.io.IOException;

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

    private VBox navigation = null;
    private Chart barChartLayout =null;

    private final String navigationItemPath = "/View/FXML/NavigationItem.fxml";
    private final String navigationPath = "/View/FXML/Navigation.fxml";
    private final String barChartPath = "/View/FXML/Barchart.fxml";

    public MainController() throws IOException {
        this.navigation = (VBox) FXMLLoader.load(getClass().getResource(navigationPath));
        this.barChartLayout = (Chart) FXMLLoader.load(getClass().getResource(barChartPath));
    }@FXML
    public void initialize()
    throws IOException {
        /*set navigation*/
        AnchorPane.setTopAnchor(navigation, 0.0);
        navigationPane.getChildren().add(navigation);
        /*set default pane*/
        addSemi(barChartLayout);
//        ImageView webView = (ImageView)FXMLLoader.load(getClass().getResource("/View/FXML/wantedMap.fxml"));
//        webView.setPreserveRatio(true);
//        addSemi(webView);
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
    public void addSemi(Node layout) throws IOException {
        AnchorPane semiContentLayout = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/FXML/SemiContentLayout.fxml"));
        semiContentLayout.getStyleClass().add("semiPane");
        Pane p = (Pane) semiContentLayout.lookup("#contents");

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
