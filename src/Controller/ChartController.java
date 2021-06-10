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
import java.util.*;
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

        FileInputStream file = new FileInputStream("Resource/Analysis/CertificateAnalysis.json");
        JSONTokener token = new JSONTokener(file);
        JSONObject jsonObject = new JSONObject(token);

        Iterator<String> iterator = jsonObject.keys();
        Map<String, Integer> map = new HashMap<String, Integer>();

        while(iterator.hasNext()){
            String key = iterator.next();
            Iterator<String> i = jsonObject.getJSONObject(key).keys();
            while(i.hasNext()){
                String s = i.next();
                map.put(s, map.getOrDefault(s, 0)+(int)jsonObject.getJSONObject(key).get(s));
            }
        }
        file.close();

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        List<Map.Entry<String, Integer>>entries = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))//내림차순 정렬
                .collect(Collectors.toList());

        for (int i =0;i<10;i++){
            series.getData().add(new XYChart.Data<>(entries.get(i).getKey(),  entries.get(i).getValue()));
        }
        barChart.getData().add(series);
    }
}