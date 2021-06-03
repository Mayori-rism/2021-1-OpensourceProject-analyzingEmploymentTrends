package Util.OpenDataRequester;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.XML;

public class OpenDataRequester {
    RequestDictionary dictionary= new RequestDictionary();

    public OpenDataRequester(){

    }

    public  JSONArray  getResponseData(RequestDictionary.requestSet request) throws IOException{
        Map<String, String> parameters = dictionary.getDataSet(request);
        JSONArray jObject=new JSONArray();

        URL url = new URL(request.getDomain() + "?"+ ParameterStringBuilder.getParamsString(parameters));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", String.format("application/%s", request.getResponseType()));

        int status = con.getResponseCode();

        if( request.getResponseType().equals("XML")){
            String conString = IOUtils.toString(con.getInputStream());
            jObject.put(XML.toJSONObject(conString));
        }else{
            JSONTokener tokener = new JSONTokener(con.getInputStream());
            jObject = new JSONArray(tokener);
        }

        con.disconnect();

        return jObject;
    }

    public JSONArray getResponseData(String requestUrl, Map<String, String> parameter, String fileType) throws  IOException {
        JSONArray jObject=new JSONArray();

        URL url = new URL(requestUrl + "?"+ ParameterStringBuilder.getParamsString(parameter));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", String.format("application/%s", fileType));

        int status = con.getResponseCode();

        if( fileType.equals("XML")){
            String conString = IOUtils.toString(con.getInputStream());
            jObject.put(XML.toJSONObject(conString));
        }else{
            JSONTokener tokener = new JSONTokener(con.getInputStream());
            jObject = new JSONArray(tokener);
        }

        return jObject;
    }

    public void getResponseDataFile(RequestDictionary.requestSet request) throws IOException{
        Map<String, String> parameters = dictionary.getDataSet(request);
        JSONObject jObject=null;

        URL url = new URL(request.getDomain() + "?"+ ParameterStringBuilder.getParamsString(parameters));
        System.out.println(url.toString());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", String.format("application/%s", request.getResponseType()));

        int status = con.getResponseCode();



        if( request.getResponseType().equals("XML")){
            String conString = IOUtils.toString(con.getInputStream());
            jObject = XML.toJSONObject(conString);
            InputStream jsonStream = new ByteArrayInputStream(jObject.toString().getBytes());

            ReadableByteChannel rbc = Channels.newChannel(jsonStream);
            FileOutputStream fos = new FileOutputStream(String.format("OpenData/%s.json", request.name()));
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            rbc.close();
            fos.close();
        }else{
            ReadableByteChannel rbc = Channels.newChannel(con.getInputStream());
            FileOutputStream fos = new FileOutputStream(String.format("OpenData/%s.json", request.name()));
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            rbc.close();
            fos.close();
        }

        con.disconnect();
    }

    public void getResponseDataFile(String requestUrl, Map<String, String> parameter,String fileName, String fileType) throws  IOException{
        URL url = new URL(requestUrl + "?"+ ParameterStringBuilder.getParamsString(parameter));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        JSONObject jObject=null;

        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", String.format("application/%s", fileType));

        int status = con.getResponseCode();

        if(fileType.equals("XML")){
            String conString = IOUtils.toString(con.getInputStream());
            jObject = XML.toJSONObject(conString);
            InputStream jsonStream = new ByteArrayInputStream(jObject.toString().getBytes());

            ReadableByteChannel rbc = Channels.newChannel(jsonStream);
            FileOutputStream fos = new FileOutputStream(String.format("OpenData/%s.json", fileName));
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            rbc.close();
            fos.close();
        }else{
            ReadableByteChannel rbc = Channels.newChannel(con.getInputStream());
            FileOutputStream fos = new FileOutputStream(String.format("OpenData/%s.json",fileName));
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            rbc.close();
            fos.close();
        }
        con.disconnect();
    }
}
