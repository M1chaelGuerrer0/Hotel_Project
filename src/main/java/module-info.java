module com.example.gooncentral {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.gooncentral to javafx.fxml;
    exports com.example.gooncentral;
}