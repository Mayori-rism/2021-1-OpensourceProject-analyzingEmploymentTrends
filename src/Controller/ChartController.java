package Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChartController {
    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis xAxis;

    private ObservableList<String> dataName = FXCollections.observableArrayList();

    public ChartController() {

    }

    @FXML
    private void initialize()  throws IOException {

    }
    public void setChartData() throws IOException {
        FileInputStream file = new FileInputStream("OpenData/wanted.json");
        JSONTokener token = new JSONTokener(file);
        JSONObject jsonObject = new JSONObject(token);
        file.close();

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Integer> map = mapper.readValue(jsonObject.toString(), Map.class);

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        List<Map.Entry<String, Integer>>entries = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))//내림차순 정렬
                .collect(Collectors.toList());

//        Iterator<String> key = jsonObject.keys();
//        while(key.hasNext()){
//            System.out.println(key.next());
//            System.out.println( jsonObject.get(key.next()));
//            series.getData().add(new XYChart.Data<>(key.next(),  (Integer) jsonObject.get(key.next())));
//        }

        for (int i =0;i<10;i++){
            series.getData().add(new XYChart.Data<>(entries.get(i).getKey(),  entries.get(i).getValue()));
        }
        barChart.getData().add(series);
    }
}