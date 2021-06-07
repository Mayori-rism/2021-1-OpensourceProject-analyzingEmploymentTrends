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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/MainLayout.fxml"));

        Parent root = (Parent)loader.load();
//        ChartController c = loader.getController();
//        c.setChartData();
        primaryStage.setTitle("취업동향분석 앱 서비스");//타이틀
        primaryStage.setScene(new Scene(root));
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
