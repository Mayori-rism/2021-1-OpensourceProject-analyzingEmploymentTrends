package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Util.OpenDataRequestModule.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException, ParseException {

        OpenDataRequest openDataRequest = new OpenDataRequest();
        openDataRequest.getResponseData(RequestDictionary.requestSet.MonthlySalary);
        openDataRequest.getResponseData(RequestDictionary.requestSet.RecruitmentRate);
        launch(args);
    }
}
