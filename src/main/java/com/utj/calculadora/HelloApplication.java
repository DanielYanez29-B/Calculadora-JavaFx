package com.utj.calculadora;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("calculadora-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 380);

        CalculadoraController controller = fxmlLoader.getController();
        scene.addEventFilter(KeyEvent.KEY_PRESSED, controller::manejarTeclado);
        stage.setTitle("Calculadora");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
