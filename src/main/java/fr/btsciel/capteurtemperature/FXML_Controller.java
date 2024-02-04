package fr.btsciel.capteurtemperature;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import jssc.SerialPortException;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;

public class FXML_Controller {
    final int NBS_DE_POINTS = 20;
    @FXML
    CategoryAxis xAxis;
    SimpleDateFormat simpleDateFormat;
    XYChart.Series series;
    Timer timer;
    DecimalFormat df;
    @FXML
    Label label_Valeur;
    String port;
    boolean stop;
    @FXML
    Button RAZ;
    CapteurTemperature capteurTemperature;
    @FXML
    CheckBox checkBox_Continu;
    @FXML
    Label cursorCoords;
    @FXML
    NumberAxis yAxis;
    @FXML
    LineChart<String,Number> lineChart;

    private void base_De_Temps (){
    }

    public void initialize (URL url, ResourceBundle resourceBundle) throws SerialPortException {
        lineChart.setAnimated(true);
        lineChart.setCreateSymbols(false);
        capteurTemperature.configureLiaisonSerieCapteur();
        series.getData().add(new XYChart.Data<>(simpleDateFormat.format(new Date()), capteurTemperature.getValeurLue()));
        lineChart.getData().add(series);
    }
}