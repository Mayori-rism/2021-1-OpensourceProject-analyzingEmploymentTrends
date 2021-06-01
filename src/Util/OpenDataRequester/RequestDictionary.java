package Util.OpenDataRequester;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.*;


import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;


public class RequestDictionary {

    public enum requestSet {
        RecruitmentRate("kosis", "apiKey", "format"),
        MonthlySalary("kosis", "apiKey", "format"),
        WantedList("worknet", "authKey", "returnType","/opi/opi/opia/wantedApi.do");
        private String domainName = null;
        private String Keytype = null;
        private String formatType = null;
        private String path = null;
        requestSet(String domainName, String Keytype, String formatType) {
            this.domainName = domainName;
            this.Keytype = Keytype;
            this.formatType = formatType;
        }
        requestSet(String domainName,String Keytype, String formatType, String path) {
            this.domainName = domainName;
            this.Keytype = Keytype;
            this.formatType = formatType;
            this.path = path;
        }

        public String getFormatType() {
            return formatType;
        }

        public String getKeytype() {
            return Keytype;
        }

        public String getDomainName() {
            return domainName;
        }
        public String getDomain() throws IOException {
            String resourceName = String.format("Resource/DataSet/%sDataSet.json",this.getDomainName());
            InputStream is = new FileInputStream(resourceName);

            JSONTokener tokener = new JSONTokener(is);
            JSONObject jsonElement= null;
            jsonElement = new JSONObject(tokener);
            return  String.format("%s%s",jsonElement.get("URL").toString(), (this.path != null) ? this.path:"");
        }
        public String getResponseType()throws IOException{
            String resourceName = String.format("Resource/DataSet/%sDataSet.json",this.getDomainName());
            InputStream is = new FileInputStream(resourceName);

            JSONTokener tokener = new JSONTokener(is);
            JSONObject jsonElement= null;
            jsonElement = new JSONObject(tokener);
            return  jsonElement.get(this.getFormatType()).toString();
        }
    }

    public HashMap<String,String> getDataSet(requestSet data) throws IOException {
        JSONObject jsonObject= null;
        JSONArray parameterArray = null;
        HashMap<String,String> targetMap = null;
        String resourceName = String.format("Resource/DataSet/%sDataSet.json",data.getDomainName());
        InputStream is = new FileInputStream(resourceName);

        JSONTokener tokener = new JSONTokener(is);
        jsonObject = new JSONObject(tokener);
        System.out.println(jsonObject.toString());
        parameterArray = jsonObject.getJSONArray("parameter");//get parameter

        for (int i = 0;i < parameterArray.length();i++){
            if((parameterArray.getJSONObject(i).get("dataName").equals(data.name()))){
                targetMap = (HashMap<String, String>) parseJsonToMap(parameterArray.getJSONObject(i).toString());
                break;
            }
        }
        assert targetMap != null;

        targetMap.put(data.getKeytype(),jsonObject.get(data.getKeytype()).toString());
        targetMap.put(data.getFormatType(),jsonObject.get(data.getFormatType()).toString());
        targetMap.remove("dataName");
        targetMap.remove("describe");
        return targetMap;
    }
    public Map<String, String> parseJsonToMap(String json) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, new TypeReference<Map<String, String>>() {
            });
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap<String, String>();
    }

}
