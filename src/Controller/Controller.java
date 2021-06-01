package Controller;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

/**
 * 생일 통계 뷰에 대한 컨트롤러
 *
 * @author Marco Jakob
 */
class ChartController {

//    @FXML
//    private BarChart<String, Integer> barChart;
//
//    @FXML
//    private CategoryAxis xAxis;
//
//    private ObservableList<String> monthNames = FXCollections.observableArrayList();
//
//    /**
//     * 컨트롤러 클래스를 초기화한다. 이 메서드는 FXML 파일이 로드된 후 자동으로 호출된다.
//     */
//    @FXML
//    private void initialize() {
//        // 영어 월 이름을 배열로 가져온다.
//        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
//        // 리스트로 변환하고 나서 ObservableList에 추가한다.
//        monthNames.addAll(Arrays.asList(months));
//
//        // 수평축에 월 이름을 카테고리로 할당한다.
//        xAxis.setCategories(monthNames);
//    }
//
//    /**
//     * 통계에 보여줄 연락처를 설정한다.
//     *
//     * @param persons
//     */
//    public void setPersonData(List<Person> persons) {
//        // 연락처 생일의 월 개수를 누적한다.
//        int[] monthCounter = new int[12];
//        for (Person p : persons) {
//            int month = p.getBirthday().getMonthValue() - 1;
//            monthCounter[month]++;
//        }
//
//        XYChart.Series<String, Integer> series = new XYChart.Series<>();
//
//        // 월별로 XYChart.Data 객체를 만든다. series에 추가한다.
//        for (int i = 0; i < monthCounter.length; i++) {
//            series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
//        }
//
//        barChart.getData().add(series);
//    }
}