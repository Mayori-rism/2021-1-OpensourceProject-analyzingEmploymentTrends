package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.chart.Chart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;

public class MainController{
    @FXML
    private AnchorPane wrappingPane;
    @FXML
    private AnchorPane horizonPane;
    @FXML
    private Label mainTitle;
    @FXML
    private AnchorPane  contentsFrame;
    @FXML
    private VBox  contentsPane;
    @FXML
    private HBox contents;
    @FXML
    private AnchorPane navigationPane;

    private VBox navigation = null;
    private Chart barChartLayout =null;
    private ImageView mapLayout = null;
    private Chart lineChartLayout = null;

    private final String navigationItemPath = "/View/FXML/NavigationItem.fxml";
    private final String navigationPath = "/View/FXML/Navigation.fxml";
    private final String barChartPath = "/View/FXML/Barchart.fxml";
    private final String mapPath = "/View/FXML/Map.fxml";
    private final String lineChartPath= "/View/FXML/LineChart.fxml";

    private NavigationController naviController = null;
    private BarChartController barChartController = null;
    private MapController mapController =null;
    enum categorys{
        certificate,region
    }

    public MainController() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(navigationPath));
        this.navigation = (VBox) loader.load();
        naviController = loader.getController();

        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(barChartPath));
        this.barChartLayout = (Chart) loader.load();
        barChartController =loader.getController();

        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(mapPath));
        this.mapLayout = (ImageView) loader.load();
        mapController = loader.getController();

        loader = new FXMLLoader();

        loader.setLocation(getClass().getResource(lineChartPath));
        this.lineChartLayout = (Chart) loader.load();
    }@FXML
    public void initialize()
    throws IOException {
        /*set navigation*/
        AnchorPane.setTopAnchor(navigation, 0.0);
        navigationPane.getChildren().add(navigation);
        /*add category*/
        AnchorPane barChartSemi = getSemi(barChartLayout, "자격증 트렌드");
        AnchorPane mapSemi = getSemi(mapLayout, "전국 채용 트렌드");
        AnchorPane lineChartSemi = getSemi(lineChartLayout,"임금률");
                
        naviController.addNaviItem(wrappingPane,"기술 트렌드",barChartSemi);
        naviController.addNaviItem(wrappingPane,"지역 트렌드",mapSemi);
        naviController.addNaviItem(wrappingPane,"임금 트렌드",lineChartSemi);
        /*set default pane*/
//        contents.getChildren().add(chartSemi);
        
    }
    public AnchorPane getSemi(Region layout, String title) throws IOException {
        AnchorPane semiContentLayout = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/FXML/SemiContentLayout.fxml"));
        semiContentLayout.getStyleClass().add("semiPane");

        Label semiTitle = (Label) semiContentLayout.lookup("#semiTitle");
        semiTitle.setText(title);

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
