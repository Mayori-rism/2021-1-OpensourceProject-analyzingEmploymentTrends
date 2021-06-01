package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Util.OpenDataRequester.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException, ParseException {

        OpenDataRequester openDataRequest = new OpenDataRequester();
        openDataRequest.getResponseData(RequestDictionary.requestSet.MonthlySalary);
        openDataRequest.getResponseData(RequestDictionary.requestSet.RecruitmentRate);
        launch(args);
    }
}
