module com.example.usos {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    opens com.example.usos.StudentMethods to javafx.base;
    opens com.example.usos to javafx.fxml;
    exports com.example.usos;
    exports com.example.usos.AdminDashboard;
    opens com.example.usos.AdminDashboard to javafx.fxml;
    exports com.example.usos.StudentDashboard;
    opens com.example.usos.StudentDashboard to javafx.fxml;
}