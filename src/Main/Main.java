package Main;

import Util.Occupation.Occupation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import com.jfoenix.controls.JFXDecorator;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println(Thread.currentThread().getName() + " : start()");
        Parent root = (Parent)FXMLLoader.load(getClass().getResource("/View/FXML/MainLayout.fxml"));

        JFXDecorator decorator = new JFXDecorator(primaryStage, root);
        decorator.setCustomMaximize(true);
        decorator.autosize();

        Scene scene = new Scene(decorator, 1280, 720);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(getClass().getResource("/View/CSS/jfx.css").toExternalForm());

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("취업동향분석 앱 서비스");//타이틀
        primaryStage.setScene(scene);
        primaryStage.show();//윈도우
    }
    
     @Override
    public void stop() throws Exception{
		 System.out.println(Thread.currentThread().getName() + " : stop()");
    	super.stop();
    }

    public static void main(String[] args) throws IOException, ParseException, BiffException {
//        launch(args);
    }
}
