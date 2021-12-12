package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class EditPersonajesController {

    @FXML
    private ComboBox<?> CBPersonajes;

    @FXML
    private TextField TFTAlineamiento;

    @FXML
    private TextField TFTNombre;

    @FXML
    private Button buttBorrar;

    @FXML
    private Button buttCrear;

    @FXML
    private TextField txtDescripcion;

    @FXML
    void borrarPersonaje(ActionEvent event) {

    }

    @FXML
    void crearEditarPersonaje(ActionEvent event) {

    }
    
    @FXML
    private void switchToLibro() throws IOException {
        App.setRoot("MainLibros");
    }

}
