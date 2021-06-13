package Model;

import Util.Occupation.Occupation;
import jxl.read.biff.BiffException;

import java.io.IOException;

public class JobModel {
    private String company;
    private String title;
    private String occupation;
    private String sal;
    private String[] certificate;
    private String basicAddr;
    private String region;
    private String zipCd;
    private String wantedInfoUrl;

    public JobModel(String company, String title, String occupation, String sal, String certificate, String basicAddr, String region, String zipCd, String wantedInfoUrl) {
        this.company = company;
        this.title = title;
        this.occupation = occupation;
        this.sal = sal;
        this.certificate = Occupation.certificatesParser(certificate);
        this.basicAddr = basicAddr;
        this.region = region;
        this.zipCd = zipCd;
        this.wantedInfoUrl = wantedInfoUrl;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getBasicAddr() {
        return basicAddr;
    }

    public String[] getCertificate() {
        return certificate;
    }

    public String getCompany() {
        return company;
    }

    public String getSal() {
        return sal;
    }

    public String getStrtnmCd() {
        return region;
    }

    public String getTitle() {
        return title;
    }

    public String getWantedInfoUrl() {
        return wantedInfoUrl;
    }

    public String getZipCd() {
        return zipCd;
    }
}
