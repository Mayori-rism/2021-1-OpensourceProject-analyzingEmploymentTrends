package Util.Occupation;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Occupation {
    public Occupation() {
    }

    public static String parser(String code) {//직업코드를 최상위 직종코드로 바꾸는 파서
        FileInputStream file = null;
        try {
            file = new FileInputStream("Resource/ExelResource/occupationCode.xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String[] certificatesParser(String cert) {
        String pureText = cert.replaceAll("\\([^\\(\\)]+\\)", "");
        String[] certificate = pureText.split(",");
        return certificate;
    }
}
