package Main;

import Util.DataProcessor.DataProcessor;
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

    public static void main(String[] args) throws IOException, BiffException {
        launch(args);
//        DataProcessor a = new DataProcessor();
//        String a1 = "울산광역시,대전광역시,광주광역시,전라남도,경기도,전라북도,부산광역시,경상남도,대구광역시,충청북도,서울특별시,충청남도,제주특별자치도,경상북도,강원도,인천광역시,세종특별자치시";
//        String a2 = "45,36,48,77,754,97,139,146,93,101,308,105,26,126,57,165,14";
//        a.callProcessor(a1,a2);
    }

}
