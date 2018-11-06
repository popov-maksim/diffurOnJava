package sample.Controller;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class ChartController extends Controller {

    @FXML
    private LineChart<Double, Double> chart;

    @FXML
    private LineChart<Double, Double> errorChart;

    @FXML
    private Button backButton;

    @FXML
    private CheckBox showExact;

    private void drawChart(boolean showExact) {
        // erasing
        chart.getData().clear();
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Approximation of function");
        //populating the series with data
        model.compute();
        double[] xData = model.getArrayOfX();
        double[] yData = model.getArrayOfY();

        for (int i = 0; i < xData.length; ++i) {
            series.getData().add(new XYChart.Data(String.format("%.2f", xData[i]), yData[i]));
        }

        chart.getData().addAll(series);

        if (showExact) {
            double[] xExact = model.getExactX();
            double[] yExact = model.getExactY();
            XYChart.Series exactSeries = new XYChart.Series();
            exactSeries.setName("Exact solution");

            for (int i = 0; i < xData.length; ++i) {
                exactSeries.getData().add(new XYChart.Data(String.format("%.2f", xExact[i]), yExact[i]));
            }

            chart.getData().addAll(exactSeries);
        }
    }

    private void drawError() {
        // erasing
        errorChart.getData().clear();
        XYChart.Series errorSeries = new XYChart.Series();
        errorSeries.setName("Local Error");
        //populating the series with data
        double[] xError = model.getArrayOfX();
        double[] yError = model.getLocalError();

        for (int i = 0; i < xError.length; ++i) {
            errorSeries.getData().add(new XYChart.Data(String.format("%.2f", xError[i]), yError[i]));
        }

        errorChart.getData().addAll(errorSeries);
    }

    @FXML
    void initialize() {
        backButton.setOnAction(event -> {
            changeWindow(backButton, "/sample/View/sample.fxml", "Differential equation");
        });

        showExact.setOnAction(event -> {
            drawChart(showExact.isSelected());
        });

        drawChart(false);
        drawError();
    }

}
