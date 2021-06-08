package Model;

import Util.Occupation.Occupation;

import java.util.HashMap;
import java.util.Map;

public class OccupationModel {
    private final String occupation;
    private final Map<String, Integer> jobs = new HashMap<String, Integer>();
    private final Map<String, Integer> certificate = new HashMap<String, Integer>();
    private final Map<String, Integer> corpAddr = new HashMap<String, Integer>();

    public OccupationModel(String occupation){
        this.occupation = Occupation.parser(occupation);
    }
    public Boolean setJobCount(String jobName){
        if (!jobName.isBlank()){
            this.jobs.put(jobName,jobs.getOrDefault(jobName, 0) + 1);
            return true;
        }
        return false;
    }
    public Boolean setCertificateCount(String certName){
        if (!certName.isBlank()){
            this.certificate.put(certName, certificate.getOrDefault(certName, 0) + 1);
            return true;
        }
        return false;
    }
    public Boolean setCorpAddrCount(String address){
        if (!address.isBlank()){
            this.corpAddr.put(address,corpAddr.getOrDefault(address, 0) + 1);
            return true;
        }
        return false;
    }

    public String getOccupation() {
        return occupation;
    }

    public int getJobsCount(String jobName) {
        return jobs.get(jobName);
    }

    public int getCertificateCount(String CertName) {
        return certificate.get(CertName);
    }

    public int getCorpAddrCount(String address) {
        return corpAddr.get(address);
    }
}
