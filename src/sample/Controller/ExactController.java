package sample.Controller;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

public class ExactController extends Controller{

    @FXML
    private LineChart<Double, Double> chart;

    @FXML
    private Button backButton;

    private void drawChart() {
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Exact solution");
        //populating the series with data
        double[] xData = model.getExactX();
        double[] yData = model.getExactY();

        for (int i = 0; i < xData.length; ++i) {
            series.getData().add(new XYChart.Data(String.format("%.2f", xData[i]), yData[i]));
        }

        chart.getData().addAll(series);
    }

    @FXML
    void initialize() {
        backButton.setOnAction(event -> {
            changeWindow(backButton, "/sample/View/sample.fxml", "Differential equation");
        });

        drawChart();
    }

}
