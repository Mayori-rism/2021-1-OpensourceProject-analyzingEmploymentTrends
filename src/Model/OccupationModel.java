package Model;

import Util.Occupation.Occupation;

import java.util.*;
import java.util.stream.Collectors;

public class OccupationModel {
    private final String occupation;
    private final Map<String, Integer> jobs = new HashMap<String, Integer>();
    private final Map<String, Integer> certificate = new HashMap<String, Integer>();
    private final Map<String, Integer> regions = new HashMap<String, Integer>();
    private final List<JobModel> jobModels = new ArrayList<JobModel>();

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
    public void setJob(JobModel j){
        jobModels.add(j);
    }
    public void setStrtnmCdCount(String address){
        if (!address.isBlank()){
            this.regions.put(address,regions.getOrDefault(address, 0) + 1);
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
        return regions;
    }

    public Map<String, Integer> getCertificateAnalysis(){
       return certificate;
    }
}
