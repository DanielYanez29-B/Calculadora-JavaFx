module com.utj.calculadora {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.utj.calculadora to javafx.fxml;
    exports com.utj.calculadora;
}