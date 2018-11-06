package sample.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.Model.EulerMethod;
import sample.Model.ImprovedEulerMethod;
import sample.Model.NumericMethod;
import sample.Model.RungeKuttaMethod;

public class SampleController extends Controller{

    @FXML
    private TextField xInitialField;

    @FXML
    private TextField yInitialField;

    @FXML
    private TextField xField;

    @FXML
    private TextField nField;

    @FXML
    private Button EulerButton;

    @FXML
    private Button ImprovedEulerButton;

    @FXML
    private Button RungeKuttaButton;

    @FXML
    private Button ExactButton;

    @FXML
    private Button emGlobalError;

    @FXML
    private Button iemGlobalError;

    @FXML
    private Button rkmGlobalError;

    @FXML
    private TextField nInitial;

    @FXML
    private TextField nFinish;

    private static double inputX0 = 0, inputY0 = 0, inputX = 3;
    private static int inputN = 10;

    private void getInitialData() {
        inputX0 = Double.parseDouble(xInitialField.getText());
        inputY0 = Double.parseDouble(yInitialField.getText());
        inputX = Double.parseDouble(xField.getText());
        inputN = Integer.parseInt(nField.getText());
    }

    private void showAlert(Exception e) {
        e.printStackTrace();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Illegal input data");
        alert.showAndWait();
    }

    @FXML
    void initialize() {

        xInitialField.setText(Double.toString(inputX0));
        yInitialField.setText(Double.toString(inputY0));
        xField.setText(Double.toString(inputX));
        nField.setText(Integer.toString(inputN));
        nInitial.setText(Integer.toString(gridInitial));
        nFinish.setText(Integer.toString(gridFinish));

        EulerButton.setOnAction(event -> {
            try {
                getInitialData();
                model = new EulerMethod(inputX0, inputY0, inputX, inputN);
                changeWindow(EulerButton, "/sample/View/chart.fxml", "Euler Method");
            } catch (NumberFormatException e) {
                showAlert(e);
            }
        });

        ImprovedEulerButton.setOnAction(event -> {
            try {
                getInitialData();
                model = new ImprovedEulerMethod(inputX0, inputY0, inputX, inputN);
                changeWindow(ImprovedEulerButton, "/sample/View/chart.fxml", "Improved Euler Method");
            } catch (NumberFormatException e) {
                showAlert(e);
            }
        });

        RungeKuttaButton.setOnAction(event -> {
            try {
                getInitialData();
                model = new RungeKuttaMethod(inputX0, inputY0, inputX, inputN);
                changeWindow(RungeKuttaButton, "/sample/View/chart.fxml", "Runga-Kutta Method");
            } catch (NumberFormatException e) {
                showAlert(e);
            }
        });

        ExactButton.setOnAction(event -> {
            try {
                getInitialData();
                model = new NumericMethod(inputX0, inputY0, inputX, inputN) {
                    @Override
                    protected double delta(double xPrev, double yPrev) {
                        return 0;
                    }
                };
                changeWindow(ExactButton, "/sample/View/exact.fxml", "Exact Solution");
            } catch (NumberFormatException e) {
                showAlert(e);
            }
        });

        emGlobalError.setOnAction(event -> {
            try {
                getInitialData();
                gridInitial = Integer.parseInt(nInitial.getText());
                gridFinish = Integer.parseInt(nFinish.getText());
                model = new EulerMethod(inputX0, inputY0, inputX, inputN);
                changeWindow(emGlobalError, "/sample/View/global.fxml", "EuM Global Error");
            } catch (NumberFormatException e) {
                showAlert(e);
            }
        });

        iemGlobalError.setOnAction(event -> {
            try {
                getInitialData();
                gridInitial = Integer.parseInt(nInitial.getText());
                gridFinish = Integer.parseInt(nFinish.getText());
                model = new ImprovedEulerMethod(inputX0, inputY0, inputX, inputN);
                changeWindow(iemGlobalError, "/sample/View/global.fxml", "IEuM Global Error");
            } catch (NumberFormatException e) {
                showAlert(e);
            }
        });

        rkmGlobalError.setOnAction(event -> {
            try {
                getInitialData();
                gridInitial = Integer.parseInt(nInitial.getText());
                gridFinish = Integer.parseInt(nFinish.getText());
                model = new RungeKuttaMethod(inputX0, inputY0, inputX, inputN);
                changeWindow(rkmGlobalError, "/sample/View/global.fxml", "RKM Global Error");
            } catch (NumberFormatException e) {
                showAlert(e);
            }
        });

    }

}

