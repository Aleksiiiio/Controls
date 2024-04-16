module pl.gornik.controls {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.gornik.controls to javafx.fxml;
    exports pl.gornik.controls;
}