package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NavigationController {
    @FXML
    private VBox navigation;

    private String navigationItemPath = "/View/FXML/NavigationItem.fxml";

    private List<Button> buttons = new ArrayList<>();

    public NavigationController(){

    }

    @FXML
    public void initialize() throws IOException {
        addNaviItem("자격증 트랜드");
    }

    public void addNaviItem(String category) throws IOException {
        AnchorPane item = (AnchorPane) FXMLLoader.load(getClass().getResource(navigationItemPath));
        Label label = (Label) item.lookup("#categoryLabel");
        label.setText(category);
        navigation.getChildren().add(item);
    }
}
