package Controller;

import Util.OpenDataRequester.OpenDataRequester;
import Util.OpenDataRequester.RequestDictionary;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class LineChartController {
    private JSONArray openData;

    @FXML
    private LineChart<String, Integer> lineChart;

    public LineChartController() throws IOException {
        OpenDataRequester requester=new OpenDataRequester();
        openData = requester.getResponseData(RequestDictionary.requestSet.MonthlySalary);
    }
    @FXML
    public void initialize(){
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for (int i = 0;i<openData.length(); i++){
            if (openData.getJSONObject(i).get("C2_NM").toString().equals("전직종"))
            {
                continue;
            }
            series.getData().add(new XYChart.Data<>( openData.getJSONObject(i).get("C2_NM").toString(),Integer.parseInt(openData.getJSONObject(i).get("DT").toString())));
        }

        lineChart.getData().add(series);
    }
}
