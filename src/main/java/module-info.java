module fr.btsciel.capteurtemperature {
    requires javafx.controls;
    requires javafx.fxml;
    requires jssc;


    opens fr.btsciel.capteurtemperature to javafx.fxml;
    exports fr.btsciel.capteurtemperature;
}