package Model;

import Util.Occupation.Occupation;

public class JobModel {
    private String company;
    private String title;
    private String occupation;
    private String sal;
    private String[] certificate;
    private String basicAddr;
    private String strtnmCd;
    private String zipCd;
    private String wantedInfoUrl;

    public JobModel(String company, String title, String occupation, String sal, String certificate, String basicAddr, String strtnmCd, String zipCd, String wantedInfoUrl) {
        this.company = company;
        this.title = title;
        this.occupation = occupation;
        this.sal = sal;
        this.certificate = Occupation.certificatesParser(certificate);
        this.basicAddr = basicAddr;
        this.strtnmCd = strtnmCd;
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
        return strtnmCd;
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
