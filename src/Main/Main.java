package Main;

import Controller.MainController;
import com.jfoenix.svg.SVGGlyph;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import com.jfoenix.controls.JFXDecorator;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.Flow;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println(Thread.currentThread().getName() + " : start()");
        Parent root = (Parent)FXMLLoader.load(getClass().getResource("/View/MainLayout.fxml"));

        JFXDecorator decorator = new JFXDecorator(primaryStage, root);
        decorator.setCustomMaximize(true);
        decorator.autosize();
        Scene scene = new Scene(decorator, 1280, 720);
        scene.getStylesheets().add(getClass().getResource("/View/jfx.css").toExternalForm());
        primaryStage.setTitle("취업동향분석 앱 서비스");//타이틀
        primaryStage.setScene(scene);
        primaryStage.show();//윈도우
    }
    
     @Override
    public void stop() throws Exception{
		 System.out.println(Thread.currentThread().getName() + " : stop()");
    	super.stop();
    }

    public static void main(String[] args) throws IOException, ParseException {
//        System.out.println(Thread.currentThread().getName() + " : main()");
        launch(args);
//        wantedAnalysis analysis = new wantedAnalysis();
//        analysis.week();

    }
}
