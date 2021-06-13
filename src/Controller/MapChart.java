package Controller;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MapChart {

    @FXML
    public void initialize() throws IOException {
        FileInputStream file = new FileInputStream("Resource/Analysis/CertificateAnalysis.json");
        JSONTokener token = new JSONTokener(file);
        JSONObject jsonObject = new JSONObject(token);

        List<String> list = new ArrayList<String>(Arrays.asList("01","02","03","04","05","06","07","08","09","10","11","12","13"));
        Map<String, Integer> map = new HashMap<String, Integer>();

        for (String occKey:list)
        {
            JSONObject target = jsonObject.getJSONObject(occKey).getJSONObject("address");
            Iterator<String> key = target.keys();

            while (key.hasNext()){
                String s = key.next();
                map.put(s, map.getOrDefault(s, 0)+(int)target.get(s));
            }
        }
        file.close();
    }
}
