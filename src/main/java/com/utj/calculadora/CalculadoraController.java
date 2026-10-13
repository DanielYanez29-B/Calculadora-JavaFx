package com.utj.calculadora;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class CalculadoraController {
    @FXML private TextField display;
    @FXML private TextField result;

    @FXML
    public void onButtonClicked(ActionEvent event) {
        Button boton = (Button) event.getSource();
        procesarEntrada(boton.getText());
    }

    public void manejarTeclado(KeyEvent event) {
        boolean capturado = true;
        switch (event.getCode()) {
            // Números (KeyPad y fila superior)
            case NUMPAD0: case DIGIT0: procesarEntrada("0"); break;
            case NUMPAD1: case DIGIT1: procesarEntrada("1"); break;
            case NUMPAD2: case DIGIT2: procesarEntrada("2"); break;
            case NUMPAD3: case DIGIT3: procesarEntrada("3"); break;
            case NUMPAD4: case DIGIT4: procesarEntrada("4"); break;
            case NUMPAD5: case DIGIT5: procesarEntrada("5"); break;
            case NUMPAD6: case DIGIT6: procesarEntrada("6"); break;
            case NUMPAD7: case DIGIT7: procesarEntrada("7"); break;
            case NUMPAD8: case DIGIT8: procesarEntrada("8"); break;
            case NUMPAD9: case DIGIT9: procesarEntrada("9"); break;

            // Operaciones
            case ADD: procesarEntrada("+"); break;
            case SUBTRACT: case MINUS: procesarEntrada("-"); break;
            case MULTIPLY: procesarEntrada("×"); break;
            case DIVIDE: case SLASH: procesarEntrada("÷"); break;

            // Punto decimal
            case DECIMAL: case PERIOD: case COMMA: procesarEntrada("."); break;

            // Acciones
            case ENTER: case EQUALS: procesarEntrada("="); break;
            case BACK_SPACE: procesarEntrada("DEL"); break;
            case ESCAPE: procesarEntrada("C"); break;

            default:
                capturado = false;
                break;
        }
        if (capturado) {
            event.consume();
        }
    }

    @FXML
    public void procesarEntrada(String texto) {
        switch (texto) {
            case "C":
                display.clear();
                break;

            case "DEL":
                if (!display.getText().isEmpty()) {
                    display.setText(display.getText().substring(0, display.getText().length() - 1));
                }
                break;

            case "=":
                calcular(display.getText());
                break;

            case "+","-","×","÷" :
                if (!display.getText().isEmpty() &&
                        (!display.getText().contains("+") && !display.getText().endsWith("-") && !display.getText().contains("×") && !display.getText().contains("÷"))) {
                    display.appendText(texto);
                }
                break;

            case "±":
                String textoDisplay = display.getText();
                if (textoDisplay.isEmpty()) break;

                if (textoDisplay.startsWith("-")) {
                    display.setText(textoDisplay.substring(1));
                } else {
                    display.setText("-" + textoDisplay);
                }
                break;

            default:
                display.appendText(texto);
                break;
        }
    }

    public void calcular(String display) {
        String expresion = display;
        if (expresion.isEmpty()) return;

        boolean primerNegativo = expresion.startsWith("-");
        if (primerNegativo) {
            expresion = expresion.substring(1);
        }

        String[] tokens = expresion.split("(?<=[+\\-×÷])|(?=[+\\-×÷])");
        if (tokens.length == 3) {
            try {
                double num1 = Double.parseDouble((primerNegativo ? "-" : "") + tokens[0]);
                String operador = tokens[1];
                double num2 = Double.parseDouble(tokens[2]);

                double resultado = 0;
                switch (operador) {
                    case "+": resultado = sumar(num1, num2); break;
                    case "-": resultado = restar(num1, num2); break;
                    case "×": resultado = multiplicar(num1, num2); break;
                    case "÷": resultado = dividir(num1, num2); break;
                }

                result.setText(String.valueOf(resultado));
            } catch (ArithmeticException e) {
                result.setText("Error: Div/0");
            } catch (NumberFormatException e) {
                result.setText("Error");
            }
        }
    }

    public double sumar(double a, double b) {
        return a + b;
    }

    public double restar(double a, double b) {
        return a - b;
    }

    public double multiplicar(double a, double b) {
        return a * b;
    }

    public double dividir(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Error: Div/0");
        }
        return a / b;
    }



}
