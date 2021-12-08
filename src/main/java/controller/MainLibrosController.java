package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MainLibrosController {

    @FXML
    private TableColumn<?, ?> TCLibroAño;

    @FXML
    private TableColumn<?, ?> TCLibroDescripcion;

    @FXML
    private TableColumn<?, ?> TCLibroGenero;

    @FXML
    private TableColumn<?, ?> TCLibroTitulo;

    @FXML
    private TableColumn<?, ?> TCNotasDescripcion;

    @FXML
    private TableColumn<?, ?> TCNotasNombre;

    @FXML
    private TableView<?> TVLibro;

    @FXML
    private TableView<?> TVNotas;

    @FXML
    private Button buttAñadirLibro;

    @FXML
    private Button buttBorrarLibro;

    @FXML
    private Button buttEditarLibro;

    @FXML
    private Button buttEditarNotas;
    
    public void initialize() {
   
    }
    
    @FXML
    void EditarNotas(ActionEvent event) {

    }

    @FXML
    void añadirLibro(ActionEvent event) {

    }

    @FXML
    void borrarLibro(ActionEvent event) {

    }

    @FXML
    void editarLibro(ActionEvent event) {

    }

}
