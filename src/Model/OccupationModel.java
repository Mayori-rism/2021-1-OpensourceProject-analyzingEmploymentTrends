package Model;

import Util.Occupation.Occupation;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OccupationModel {
    private final String occupation;
    private final Map<String, Integer> jobs = new HashMap<String, Integer>();
    private final Map<String, Integer> certificate = new HashMap<String, Integer>();
    private final Map<String, Integer> strtnmCds = new HashMap<String, Integer>();

    public OccupationModel(String occupation){
        this.occupation = occupation;
    }
    public void setJobCount(String jobName){
        if (!jobName.isBlank()){
            this.jobs.put(jobName,jobs.getOrDefault(jobName, 0) + 1);
        }
    }
    public void setCertificateCount(String[] certName){
        for (int i = 0;i< certName.length;i++){
            if (!certName[i].isBlank()){
                this.certificate.put(certName[i], certificate.getOrDefault(certName[i], 0) + 1);
                return;
            }
        }

    }
    public void setStrtnmCdCount(String address){
        if (!address.isBlank()){
            this.strtnmCds.put(address,strtnmCds.getOrDefault(address, 0) + 1);
        }
    }

    public String getOccupation() {
        return occupation;
    }

    public Map<String, Integer> getJobsCount() {
        return jobs;
    }

    public Map<String, Integer> getCertificateCount() {
        return certificate;
    }

    public Map<String, Integer> getCorpAddrCount() {
        return strtnmCds;
    }

    public List<Map.Entry<String, Integer>> getCertificateAnalysis(){
        List<Map.Entry<String, Integer>>entries = this.certificate.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))//내림차순 정렬
                .collect(Collectors.toList());
        return entries;
    }
}
