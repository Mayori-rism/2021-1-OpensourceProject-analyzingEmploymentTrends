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
    private AnchorPane semiLayout =null;

    private final String navigationItemPath = "/View/FXML/NavigationItem.fxml";
    private final String navigationPath = "/View/FXML/Navigation.fxml";
    private final String barChartPath = "/View/FXML/Barchart.fxml";
    private final String mapPath = "/View/FXML/Map.fxml";
    private final String lineChartPath= "/View/FXML/LineChart.fxml";
    private final String semiPath = "/View/FXML/SemiContentLayout.fxml";

    private NavigationController naviController = null;
    private BarChartController barChartController = null;
    private MapController mapController =null;
    private LineChartController lineChartController=null;
    private SemiContentController semiController = null;

    enum categorys{
        certificate,region
    }

    public MainController() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(navigationPath));
        this.navigation = (VBox) loader.load();
        this.naviController = loader.getController();

        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(barChartPath));
        this.barChartLayout = (Chart) loader.load();
        this.barChartController =loader.getController();

        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(mapPath));
        this.mapLayout = (ImageView) loader.load();
        this.mapController = loader.getController();

        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(lineChartPath));
        this.lineChartLayout = (Chart) loader.load();
        this.lineChartController = loader.getController();

        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(semiPath));
        this.semiLayout = (AnchorPane) loader.load();
        this.semiController = loader.getController();

    }@FXML
    public void initialize()
    throws IOException {
        /*set navigation*/
        AnchorPane.setTopAnchor(navigation, 0.0);
        navigationPane.getChildren().add(navigation);
        /*add category*/
        AnchorPane barChartSemi = semiController.getSemi(barChartLayout, "자격증 트렌드");
        AnchorPane mapSemi = semiController.getSemi(mapLayout, "전국 채용 트렌드");
        AnchorPane lineChartSemi = semiController.getSemi(lineChartLayout,"임금률");
                
        naviController.addNaviItem(wrappingPane,"기술 트렌드",barChartSemi);
        naviController.addNaviItem(wrappingPane,"지역 트렌드",mapSemi);
        naviController.addNaviItem(wrappingPane,"임금 트렌드",lineChartSemi);
        /*set default pane*/

        contents.getChildren().add(barChartSemi);
        mainTitle.setText("기술 트렌드");
        navigation.getChildren().get(0).getStyleClass().add("selectedItem");
        
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
