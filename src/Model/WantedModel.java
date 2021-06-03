package Model;

import java.util.*;
import java.util.stream.Collectors;

public class WantedModel {
    Map<String, Integer> certificateAnalysis = new HashMap<String,Integer>();
    Map<String, String> corpAddrAnalysis = new HashMap<String, String>();
    public WantedModel(){

    }
    public void setCertificateAnalysis(String certificates){
        System.out.println(certificates);
        String pureText = certificates.replaceAll("\\([^)]+\\)","");
        String[] certificate = pureText.split(",");
        for(String s :certificate){
            System.out.println(s);
            this.certificateAnalysis.put(s,certificateAnalysis.getOrDefault(s, 0) + 1);
        }
    }
    public List<Map.Entry<String, Integer>> getCertificateAnalysis(){
        List<Map.Entry<String, Integer>>entries = this.certificateAnalysis.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());
        return entries;
    }
}
