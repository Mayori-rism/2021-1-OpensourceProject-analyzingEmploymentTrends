package Controller;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NavigationController {
    @FXML
    private VBox navigation;

    private String navigationItemPath = "/View/FXML/NavigationItem.fxml";

    private ArrayList<AnchorPane> items = new ArrayList<>();


    public NavigationController(){

    }

    @FXML
    public void initialize() throws IOException {
    }

    public void addNaviItem(HBox contentsPane, String name, AnchorPane category) throws IOException {
        AnchorPane item = (AnchorPane) FXMLLoader.load(getClass().getResource(navigationItemPath));
        Label label = (Label) item.lookup("#categoryLabel");
        label.setText(name);

        item.addEventFilter(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        removeStyleClass();
                        item.getStyleClass().add("selectedItem");
                        contentsPane.getChildren().clear();
                        contentsPane.getChildren().add(category);
                    }
                });
        items.add(item);
        navigation.getChildren().add(item);
    }

    private void removeStyleClass(){
        for (AnchorPane item:items){
            item.getStyleClass().remove("selectedItem");
        }
    }
}
