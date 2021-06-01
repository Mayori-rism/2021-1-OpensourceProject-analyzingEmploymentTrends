package Util.OpenDataRequester;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class RequestDictionary {

    public enum requestSet {
        RecruitmentRate("kosis"),
        MonthlySalary("kosis");

        private String domainName;

        requestSet(String domainName) {
            this.domainName = domainName;
        }

        public String getDomainName() {
            return domainName;
        }
        public String getDomain() throws IOException, ParseException {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject= null;
            jsonObject = (JSONObject)jsonParser.parse(new FileReader(String.format("Resource/DataSet/%sDataSet.json",this.getDomainName())));
            return jsonObject.get("URL").toString();
        }
        public String getResponseType()throws IOException, ParseException{
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject= null;
            jsonObject = (JSONObject)jsonParser.parse(new FileReader(String.format("Resource/DataSet/%sDataSet.json",this.getDomainName())));
            return  jsonObject.get("format").toString();
        }
    }

    public HashMap<String,String> getDataSet(requestSet data) throws IOException, ParseException {
        JSONObject jsonObject= null;
        JSONParser jsonParser = new JSONParser();
        JSONArray parameterArray;
        HashMap<String,String> targetMap = null;

        jsonObject = (JSONObject)jsonParser.parse(new FileReader(String.format("Resource/DataSet/%sDataSet.json",data.getDomainName())));
        parameterArray = (JSONArray)jsonObject.get("parameter");//get parameter

        for (int i = 0;i < parameterArray.size();i++){
            if(((JSONObject)parameterArray.get(i)).get("dataName").equals(data.name())){
                targetMap = (HashMap<String,String>)parameterArray.get(i);
                break;
            }
        }
        targetMap.put("apiKey",jsonObject.get("apiKey").toString());
        targetMap.put("format",jsonObject.get("format").toString());
        targetMap.remove("dataName");
        targetMap.remove("describe");
        return targetMap;
    }


}
