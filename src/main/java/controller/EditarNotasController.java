package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class EditarNotasController {

    @FXML
    private ChoiceBox<?> C;

    @FXML
    private ChoiceBox<?> CBEditNLibros;

    @FXML
    private ChoiceBox<?> CBEditNotas;

    @FXML
    private ChoiceBox<?> CBRemoveNLibro;

    @FXML
    private ChoiceBox<?> CBRemoveNota;

    @FXML
    private TextField TFCrearNDescripcion;

    @FXML
    private TextField TFCrearNNombre;

    @FXML
    private TextField TFNotaDescripcion;

    @FXML
    private Button buttBorrar;

    @FXML
    private Button buttCrear;

    @FXML
    private Button buttEditarNota;
    
    public void initialize() {
    	   
    }
    
    @FXML
    void borrarNota(ActionEvent event) {

    }

    @FXML
    void crearNota(ActionEvent event) {

    }

    @FXML
    void editarNota(ActionEvent event) {

    }

}
