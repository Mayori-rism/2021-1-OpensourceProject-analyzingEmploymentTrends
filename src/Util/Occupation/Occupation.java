package Util.Occupation;


import java.io.*;
import java.util.*;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class Occupation {

    public Occupation() {
    }

    public static String jobCodeParser(String code) throws IOException, BiffException {//직업코드를 최상위 직종코드로 바꾸는 파서
        final Workbook workbook = Workbook.getWorkbook(new File("Resource/ExelResource/occupationCode.xls"));
        Sheet sheet = workbook.getSheet(0);
        int rows = sheet.getRows();
        for (int i = 0; i < rows; i++) {
            if (sheet.getCell(0,i).getContents().equals(code)){
                for (int j = i; 0 <= j; j--) {
                    if(!sheet.getCell(1, j).getContents().isBlank()){
                        String s = sheet.getCell(0, j).getContents();
                        workbook.close();
                        return s;
                    }
                }
            }
        }
        workbook.close();
        return null;
    }
    public static String provincesCodeParser(String code) throws IOException, BiffException {//직업코드를 최상위 직종코드로 바꾸는 파서
        final Workbook workbook = Workbook.getWorkbook(new File("Resource/ExelResource/ProvincesCode.xls"));
        Sheet sheet = workbook.getSheet(0);
        int rows = sheet.getRows();
        for (int i = 0; i < rows; i++) {
            if (sheet.getCell(0,i).getContents().equals(code)){
                for (int j = i; 0 <= j; j--) {
                    if(!sheet.getCell(1, j).getContents().isBlank()){
                        String s = sheet.getCell(1, j).getContents();
                        workbook.close();
                        return s;
                    }
                }
            }
        }
        workbook.close();
        return null;
    }
    public static String toText(String code) throws IOException, BiffException {//직업코드를 최상위 직종코드로 바꾸는 파서
        final Workbook workbook = Workbook.getWorkbook(new File("Resource/ExelResource/occupationCode.xls"));
        Sheet sheet = workbook.getSheet(0);
        int rows = sheet.getRows();
        int columns = sheet.getColumns();

        for (int i = 0; i < rows; i++) {
            if (sheet.getCell(0,i).getContents().equals(code)){
                for (int j = i; 0 <= j; j--) {
                    if(!sheet.getCell(1, j).getContents().isBlank()){
                        String s = sheet.getCell(1, j).getContents();
                        workbook.close();
                        return s;
                    }
                }
            }

        }
        workbook.close();
        return null;
    }

    public static String[] certificatesParser(String cert) {
        String pureText = cert.replaceAll("\\([^\\(\\)]+\\)", "");
        String[] certificate = pureText.split(",");
        return certificate;
    }
}
