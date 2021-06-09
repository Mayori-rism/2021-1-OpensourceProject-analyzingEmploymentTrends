package Main;

import Model.JobModel;
import Model.OccupationModel;
import Util.Occupation.Occupation;
import Util.OpenDataRequester.*;
import jxl.read.biff.BiffException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.BufferOverflowException;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class wantedAnalysis {
    private final OpenDataRequester requester = new OpenDataRequester();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
    private final Map<String, String> wantedListMap = new HashMap<String, String>();
    private final Map<String, String> wantedDetailMap = new HashMap<String, String>();
    private List<JobModel> jobList = new CopyOnWriteArrayList();
    private Map<String, OccupationModel> occupationMap = new HashMap<String, OccupationModel>();
    private final String requestUrl = "http://openapi.work.go.kr/opi/opi/opia/wantedApi.do";

    wantedAnalysis() {
        this.wantedListMap.put("authKey", "WNKOTSAB24OI3QV77TB4K2VR1HK");
        this.wantedListMap.put("callTp", "L");
        this.wantedListMap.put("returnType", "XML");
        this.wantedListMap.put("startPage", "1");
        this.wantedListMap.put("display", "100");

        this.wantedDetailMap.put("authKey", "WNKOTSAB24OI3QV77TB4K2VR1HK");
        this.wantedDetailMap.put("callTp", "D");
        this.wantedDetailMap.put("returnType", "XML");
        this.wantedDetailMap.put("infoSvc", "VALIDATION");
        this.wantedDetailMap.put("wantedAuthNo", "");
    }

    private class wantedDetail implements Runnable {
        private final JSONArray wantedList;
        private final LocalDate targetDate;

        public wantedDetail(JSONArray wantedList, LocalDate tagetDate) {
            this.wantedList = wantedList;
            this.targetDate = tagetDate;
        }

        @Override
        public void run() {
            for (int i = 0; i < wantedList.length(); i++) {
                String regDt = wantedList.getJSONObject(i).get("regDt").toString();

                if (targetDate.isBefore(LocalDate.parse(regDt, formatter))) {
                    break;
                }
                String wantedAuthNo = wantedList.getJSONObject(i).get("wantedAuthNo").toString();

                wantedDetailMap.put("wantedAuthNo", wantedAuthNo);
                JSONArray wantedDetailRequest = null;
                try {
                    wantedDetailRequest = requester.getResponseData(requestUrl, wantedDetailMap, "XML");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                JSONObject wantedInfo = wantedDetailRequest.getJSONObject(0).getJSONObject("wantedDtl").getJSONObject("wantedInfo");

                String company = wantedList.getJSONObject(i).get("company").toString();
                String title = wantedList.getJSONObject(i).get("title").toString();
                String occupation = wantedList.getJSONObject(i).get("jobsCd").toString();
                String sal = wantedList.getJSONObject(i).get("sal").toString();
                String certificate = wantedInfo.get("certificate").toString();
                String basicAddr = wantedList.getJSONObject(i).get("basicAddr").toString();
                String strtnmCd = wantedList.getJSONObject(i).get("strtnmCd").toString();
                String zipCd = wantedList.getJSONObject(i).get("zipCd").toString();
                String wantedInfoUrl = wantedList.getJSONObject(i).get("wantedInfoUrl").toString();

                jobList.add(new JobModel(company, title, occupation, sal, certificate, basicAddr, strtnmCd, zipCd, wantedInfoUrl));
            }
        }
    }

    /*매개변수로 검색간격을 설정함으로 직종별 데이터가 정리된 맵이 반환된다.*/
    public Map<String, OccupationModel> getWantedData(int target) {
        LocalDate targetDate = LocalDate.now().minusDays(target);//target day

        LocalDate date = LocalDate.now();//date init


        Thread lastThread = null;
        //타겟 날짜까지 체용정보 요청
        while (targetDate.isBefore(date)) {
            JSONArray wantedListRequest = null;
            try {
                wantedListRequest = requester.getResponseData(requestUrl, wantedListMap, "XML");
            } catch (IOException e) {
                e.printStackTrace();
            }
            JSONArray wantedList = wantedListRequest.getJSONObject(0).getJSONObject("wantedRoot").getJSONArray("wanted");
            date = LocalDate.parse(wantedList.getJSONObject(0).get("regDt").toString(), formatter);//check date

            lastThread = new Thread(new wantedDetail(wantedList, targetDate));
            lastThread.start();

            wantedListMap.computeIfPresent("startPage", (k, v) -> String.valueOf(Integer.parseInt(v) + 10));//next page
        }
        try {
            System.out.println("join");
            lastThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (JobModel j : jobList) {
            String code = null;
            try {
                code = Occupation.jobCodeParser(j.getOccupation());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (BiffException e) {
                e.printStackTrace();
            }
            if (occupationMap.get(code) == null)//분석모듈을 이용해서 더 큰단위에 직종 그룹으로 기준을 해야함
            {
                OccupationModel model = new OccupationModel(code);
                model.setJob(j);
                model.setJobCount(j.getOccupation());
                model.setStrtnmCdCount(j.getStrtnmCd());
                model.setCertificateCount(j.getCertificate());
                occupationMap.put(code, model);
            } else {
                OccupationModel model = occupationMap.get(code);
                model.setJob(j);
                model.setJobCount(j.getOccupation());
                model.setStrtnmCdCount(j.getStrtnmCd());
                model.setCertificateCount(j.getCertificate());
            }
        }
        return occupationMap;
    }

    public void getAnalysisFile(Map<String, OccupationModel> occupations){
        JSONObject jsonMainObj = new JSONObject();
        for (String key :occupations.keySet()) {
            JSONObject jsonObject = new JSONObject();//save file data

            List<Map.Entry<String, Integer>> entries = occupations.get(key).getCertificateAnalysis();
            for (Map.Entry<String, Integer> entry : entries) {
                jsonObject.put(entry.getKey(), entry.getValue());
                System.out.println(jsonObject.toString());
            }
            if (!jsonObject.isEmpty()){
                jsonMainObj.put(occupations.get(key).getOccupation(),jsonObject);
            }
        }

        InputStream jsonStream = new ByteArrayInputStream(jsonMainObj.toString().getBytes());

        ReadableByteChannel rbc = Channels.newChannel(jsonStream);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(String.format("OpenData/wantedAnalysisData/%s.json", "CertificateAnalysis"));
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            rbc.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
