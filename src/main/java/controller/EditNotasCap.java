package controller;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.DAO.LibroDAO;
import model.DataObject.Libro;
import model.DataObject.Notas_Cap;
import model.DataObject.Partes;


public class EditNotasCap {

    @FXML 
    private ChoiceBox<Libro> CBCREARNLibros; //ELIGE LIBRO PARA CREAR

    @FXML
    private ChoiceBox<Libro> CBEditNLibros; //ELIGE LIBRO PARA EDITAR

    @FXML
    private ChoiceBox<Notas_Cap> CBEditNotas; //EDITAS NOTAS

    @FXML
    private ChoiceBox<Libro> CBRemoveNLibro; 

    @FXML
    private ChoiceBox<Notas_Cap> CBRemoveNota;

    @FXML
    private TextField TFCrearNDescripcion;

    @FXML
    private TextField TFNotaDescripcion;

    @FXML
    private Button buttBorrar;

    @FXML
    private Button buttCrear;

    @FXML
    private Button buttEditarNota;
    
    LibroDAO libroNotas = new LibroDAO();
    
    private ObservableList<Partes> librosN;
    
    @FXML
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

    @FXML
    void switchToLibro(ActionEvent event) {
    	  try {
			App.setRoot("Capitulos");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
