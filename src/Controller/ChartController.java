package Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ChartController {
    @FXML
    private BarChart<Integer, String> barChart;

    public ChartController() {

    }

    @FXML
    private void initialize()  throws IOException {

        FileInputStream file = new FileInputStream("Resource/Analysis/CertificateAnalysis.json");
        JSONTokener token = new JSONTokener(file);
        JSONObject jsonObject = new JSONObject(token);

        List<String> list = new ArrayList<String>(Arrays.asList("01","02","03","04","05","06","07","08","09","10","11","12","13"));
        Map<String, Integer> map = new HashMap<String, Integer>();

        for (String occKey:list)
        {
            JSONObject target = jsonObject.getJSONObject(occKey).getJSONObject("certificate");
            Iterator<String> key = target.keys();

            while (key.hasNext()){
                String s = key.next();
                map.put(s, map.getOrDefault(s, 0)+(int)target.get(s));
            }
        }
        file.close();

        XYChart.Series<Integer, String> series = new XYChart.Series<>();

        List<Map.Entry<String, Integer>>entries = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))//내림차순 정렬
                .collect(Collectors.toList());

        for (int i =10;i>=0;i--){
            series.getData().add(new XYChart.Data<>(entries.get(i).getValue(),entries.get(i).getKey()));
        }

        barChart.getData().add(series);
    }
}