package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CapitulosController {

    @FXML
    private ComboBox<?> CBParte;

    @FXML
    private TableColumn<?, ?> TCCapitulosNombre;

    @FXML
    private TableColumn<?, ?> TCDescripcion;

    @FXML
    private TableColumn<?, ?> TCNotaNombre;

    @FXML
    private TableColumn<?, ?> TCRecorComentario;

    @FXML
    private TableColumn<?, ?> TCRecorFecha;

    @FXML
    private TableView<?> TVCapitulos;

    @FXML
    private TableView<?> TVNotas;

    @FXML
    private TableView<?> TVRecordatorio;

    @FXML
    private Button buttEditarActos;

    @FXML
    private Button buttEditarCap;

    @FXML
    private Button buttEditarNota;

    @FXML
    private Button buttEditarRecord;
    
    public void initialize() {
    	
    }
  
    @FXML
    void editActos(ActionEvent event) {

    }

    @FXML
    void editarCap(ActionEvent event) {

    }

    @FXML
    void editarNota(ActionEvent event) {

    }

    @FXML
    void editarRecordatorio(ActionEvent event) {

    }

}
