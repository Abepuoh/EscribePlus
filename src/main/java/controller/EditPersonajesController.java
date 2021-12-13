package controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.DAO.LibroDAO;
import model.DAO.PersonajeDAO;
import model.DataObject.Libro;
import model.DataObject.Partes;
import model.DataObject.Personaje;
import model.DataObject.Usuario;

public class EditPersonajesController {

    @FXML
    private ComboBox<Libro> CBPersonajes;

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
    
    private LibroDAO ldao = new LibroDAO();
    private PersonajeDAO pdao = new PersonajeDAO();
    private ObservableList<Libro> libros;
    private static Usuario usuario;
    
    @FXML
    void initialize() {
    	utils.UsuarioSingleton transfer = utils.UsuarioSingleton.getInstance();
		usuario = transfer.getUser();
    	try {
    		this.libros = FXCollections.observableArrayList();
    		this.libros.setAll(ldao.getBooksByAuthor(usuario));
        	CBPersonajes.setItems(libros);
            CBPersonajes.getSelectionModel().selectedItemProperty().addListener((Observable,oldValue,newValue)->{
               
            });
		} catch (Exception e) {}
    }

    @FXML
    void borrarPersonaje(ActionEvent event) {

    	if(!TFTNombre.getText().isEmpty()) {
    		Personaje p = pdao.getCharacterByName(TFTNombre.getText());
        	
        	if(p==null) {
        		pdao.borrar(p.getId());
        	}else {
        		//mensaje de error
        	}
    	}
    	
    }

    @FXML
    void crearEditarPersonaje(ActionEvent event) {
    	
    	
    	if(!TFTAlineamiento.getText().isEmpty() && !TFTNombre.getText().isEmpty() && !txtDescripcion.getText().isEmpty()) {
    		Libro l = CBPersonajes.getSelectionModel().getSelectedItem();
    		Personaje p = pdao.getCharacterByName(TFTNombre.getText());
        	System.out.println(p);
        	if(p==null) {
        		p = new Personaje();
        		p.setNombre(TFTNombre.getText());
        		p.setAlineamiento(TFTAlineamiento.getText());
        		p.setDescripcion(txtDescripcion.getText());
        		pdao.crear(p);
        		ldao.addCharactertoBook(p, l);
        	}else {
        		p.setAlineamiento(TFTAlineamiento.getText());
        		p.setDescripcion(txtDescripcion.getText());
        		pdao.editar(p);
        	}
    	}

    	
    }
    
    @FXML
    private void switchToLibro() throws IOException {
        App.setRoot("MainLibros");
    }

}
