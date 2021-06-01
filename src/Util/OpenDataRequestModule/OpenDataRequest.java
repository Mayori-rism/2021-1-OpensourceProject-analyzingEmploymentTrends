package Util.OpenDataRequestModule;

import org.json.simple.parser.ParseException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Map;

public class OpenDataRequest {
    RequestDictionary dictionary= new RequestDictionary();

    public OpenDataRequest(){

    }

    public void getResponseData(RequestDictionary.requestSet request) throws IOException, ParseException {
        Map<String, String> parameters = dictionary.getDataSet(request);

        URL url = new URL(request.getDomain() + "?"+ ParameterStringBuilder.getParamsString(parameters));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");

        int status = con.getResponseCode();

        ReadableByteChannel rbc = Channels.newChannel(con.getInputStream());
        FileOutputStream fos = new FileOutputStream(String.format("OpenData/%s.%s", request.name(), request.getResponseType()));
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

        rbc.close();
        fos.close();
        con.disconnect();
    }

    public void getResponseData(String requestUrl, Map<String, String> parameter,String fileName, String fileType) throws  IOException, ParseException {
        URL url = new URL(requestUrl + "?"+ ParameterStringBuilder.getParamsString(parameter));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");

        int status = con.getResponseCode();

        ReadableByteChannel rbc = Channels.newChannel(con.getInputStream());
        FileOutputStream fos = new FileOutputStream(String.format("OpenData/%s.%s", fileName, fileType));
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

        rbc.close();
        fos.close();
        con.disconnect();
    }
}




//https://www.baeldung.com/java-http-request