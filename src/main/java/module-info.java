module com.example.usos {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    opens com.example.usos.students to javafx.base;
    opens com.example.usos to javafx.fxml;
    exports com.example.usos;
}