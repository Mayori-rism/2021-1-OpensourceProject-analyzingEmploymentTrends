package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println(Thread.currentThread().getName() + " : start()");
        Parent root = FXMLLoader.load(getClass().getResource("/View/sample.fxml"));//이름 넣기
        primaryStage.setTitle("취업동향분석 앱 서비스");//타이틀
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();//윈도우
    }
    
     @Override
    public void stop() throws Exception{
		 System.out.println(Thread.currentThread().getName() + " : stop()");
    	super.stop();
    }

    public static void main(String[] args) throws IOException, ParseException {
//        System.out.println(Thread.currentThread().getName() + " : main()");
//        launch(args);
        wantedAnalysis analysis = new wantedAnalysis();
        analysis.week();
    }
}
