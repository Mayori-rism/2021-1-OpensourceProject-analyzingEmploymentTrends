package Util.Occupation;

public class Occupation {
    public Occupation(){}

    public static String parser(String code){
        return "";
    }
    public static String[] certificatesParser(String cert) {
        String pureText = cert.replaceAll("\\([^\\(\\)]+\\)", "");
        String[] certificate = pureText.split(",");
        return certificate;
    }
}
