module com.example.usos {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.hibernate.orm.core;
    opens com.example.usos.StudentMethods to javafx.base,org.hibernate.orm.core;
    opens com.example.usos to javafx.fxml,org.hibernate.orm.core;
    exports com.example.usos;
    exports com.example.usos.AdminDashboard;
    opens com.example.usos.AdminDashboard to javafx.fxml;
    exports com.example.usos.StudentDashboard;
    opens com.example.usos.StudentDashboard to javafx.fxml,org.hibernate.orm.core;
    requires java.sql;
    requires java.persistence;
    requires java.naming;


}