package sample.Controller;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import sample.Model.Simulator;

public class GlobalController extends Controller{

    @FXML
    private LineChart<Integer, Double> chart;

    @FXML
    private Button backButton;

    private void drawChart() {
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Global Error");
        //populating the series with data
        Simulator sim = new Simulator();
        sim.computeGlobalError(model, gridInitial, gridFinish);
        double[] yData = sim.getGlobalError();
        int[] n = sim.getGlobalN();

        for (int i = 0; i < yData.length; ++i) {
            series.getData().add(new XYChart.Data(String.format("%d", n[i]), yData[i]));
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
