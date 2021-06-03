package Main;

import Util.OpenDataRequester.*;
import Model.WantedModel;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class wantedAnalysis {
    private OpenDataRequester requester = new OpenDataRequester();

    private LocalDate targetDate = LocalDate.now().minusDays(7);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");

    private Map<String, String> wantedListMap = new HashMap<String,String>();
    private Map<String, String> wantedDetailMap = new HashMap<String,String>();

    private String requestUrl = null;
    private WantedModel wantedModel = new WantedModel();

    wantedAnalysis(){
        this.wantedListMap.put("authKey","WNKOTSAB24OI3QV77TB4K2VR1HK");
        this.wantedListMap.put("callTp", "L");
        this.wantedListMap.put("returnType","XML");
        this.wantedListMap.put("startPage","1");
        this.wantedListMap.put("display","100");

        this.wantedDetailMap.put("authKey","WNKOTSAB24OI3QV77TB4K2VR1HK");
        this.wantedDetailMap.put("callTp","D");
        this.wantedDetailMap.put("returnType","XML");
        this.wantedDetailMap.put("infoSvc","VALIDATION");
        this.wantedDetailMap.put("wantedAuthNo", "");

        this.requestUrl = "http://openapi.work.go.kr/opi/opi/opia/wantedApi.do";
    }
    public void week() throws IOException, ParseException {
        LocalDate date = LocalDate.now();//date init
        Map<String, Integer> certificateAnalysis = new HashMap<String,Integer>();

        while(targetDate.isBefore(date)){
            System.out.println(wantedListMap.get("startPage"));
            JSONArray wantedListRequest = requester.getResponseData(requestUrl, wantedListMap,"XML");
            JSONArray wantedList = wantedListRequest.getJSONObject(0).getJSONObject("wantedRoot").getJSONArray("wanted");

            for (int i = 0;i < wantedList.length(); i++){
                String regDt = wantedList.getJSONObject(i).get("regDt").toString();
                String wantedAuthNo = wantedList.getJSONObject(i).get("wantedAuthNo").toString();

                date = LocalDate.parse(regDt,formatter);

                wantedDetailMap.put("wantedAuthNo",wantedAuthNo);
                JSONArray wantedDetailRequest = requester.getResponseData(requestUrl,wantedDetailMap,"XML");

                wantedModel.setCertificateAnalysis(wantedDetailRequest.getJSONObject(0).getJSONObject("wantedDtl").getJSONObject("wantedInfo").get("certificate").toString());
//                System.out.println(wantedDetailRequest.getJSONObject(0).getJSONObject("wantedDtl").getJSONObject("corpInfo").get("corpAddr"));
            }
            wantedListMap.computeIfPresent("startPage", (k, v)-> String.valueOf(Integer.parseInt(v) + 10));
        }
        List<Map.Entry<String, Integer>> entries= wantedModel.getCertificateAnalysis();
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println("Key: " + entry.getKey() + ", "
                    + "Value: " + entry.getValue());
        }
    }
}
