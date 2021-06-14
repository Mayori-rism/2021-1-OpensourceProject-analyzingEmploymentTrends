package Controller;

import Util.DataProcessor.DataProcessor;
import Util.Occupation.Occupation;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import jxl.read.biff.BiffException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


public class MapController {
    private String url ="/Resource/Python/map.png";
    @FXML
    private ImageView view;

    public MapController() throws IOException, BiffException {
//        FileInputStream file = new FileInputStream("Resource/Analysis/CertificateAnalysis.json");
//        JSONTokener token = new JSONTokener(file);
//        JSONObject jsonObject = new JSONObject(token);
//
//        List<String> list = new ArrayList<String>(Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13"));
//        Map<String, Integer> map = new HashMap<String, Integer>();
//
//        for (String occKey : list) {
//            JSONObject target = jsonObject.getJSONObject(occKey).getJSONObject("address");
//            Iterator<String> key = target.keys();
//
//            while (key.hasNext()) {
//
//                String targetKey = key.next();
//                String s = Occupation.provincesCodeParser(targetKey);
//
//                map.put(s, map.getOrDefault(s, 0) + (int) target.get(targetKey));
//            }
//        }
//
//        List<String> regionList = new ArrayList<>();
//        List<String> valueList = new ArrayList<>();
//
//        int count = 0;
//        for (String key : map.keySet()) {
//            System.out.println(key);
//
//            regionList.add(count, key);
//            valueList.add(count, map.get(key).toString());
//            count++;
//        }
//        DataProcessor processor = new DataProcessor();
//        processor.callProcessor(String.join(",", regionList), String.join(",", valueList));

    }

    @FXML
    public void initialize() throws IOException, BiffException {

        view.setImage(new Image(url));
    }
}
