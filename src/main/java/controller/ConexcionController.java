package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ConexcionController {

    @FXML
    private TextField ContraseñaText;

    @FXML
    private TextField DatabaseText;

    @FXML
    private TextField ServerText;

    @FXML
    private TableColumn<?, ?> TC;

    @FXML
    private TableView<?> TVConexiones;

    @FXML
    private TextField UsuarioText;

    @FXML
    private Button buttClose;

    @FXML
    private Button buttConectar;

    @FXML
    private Button buttSave;
    
    public void initialize() {
    	
    }
    @FXML
    void closeConexion(ActionEvent event) {

    }

    @FXML
    void conectar(ActionEvent event) {

    }

    @FXML
    void handleCloseButtonAction(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {

    }

}

