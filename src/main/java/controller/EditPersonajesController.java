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
import model.DataObject.Personaje;

public class EditPersonajesController {

	@FXML
	private ComboBox<Libro> CBLibros;

	@FXML
	private ComboBox<Personaje> CBPersonajes;

	@FXML
	private TextField TFTAlineamiento;

	@FXML
	private TextField TFTNombre;

	@FXML
	private Button buttBorrar;

	@FXML
	private Button buttCrear;

	@FXML 
	private Button buttAñadir;

	@FXML
	private TextField txtDescripcion;

	private PersonajeDAO pdao = new PersonajeDAO();
	private LibroDAO ldao = new LibroDAO();
	private ObservableList<Personaje> personajes = FXCollections.observableArrayList();
	private ObservableList<Libro> libros = FXCollections.observableArrayList();

	@FXML
	void initialize() {

		this.personajes.setAll(pdao.getAll()); 
		this.libros.setAll(ldao.getAll());
		buttBorrar.setDisable(true);
		CBLibros.setItems(libros);
		CBPersonajes.setItems(personajes);
		CBPersonajes.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue) -> {
			buttBorrar.setDisable(false);
		});
		CBLibros.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue) -> {
			buttBorrar.setDisable(false);
		});

	}

	@FXML
	void añadePersonaje(ActionEvent event) {

	}

	@FXML
	void borrarPersonaje(ActionEvent event) {
		Boolean confirmacion=utils.Dialog.showConfirm("Confirmación", "¿Quieres borra la parte?",
				"Vas a borrar: "+CBPersonajes.getValue().getNombre()); 
		if(CBPersonajes.getValue() != null && CBLibros.getValue() != null  && confirmacion) {			
			try {
				ldao.deleteCharacterFromBook(CBPersonajes.getValue(), CBLibros.getValue());
			} catch (Exception e) {
				buttBorrar.setDisable(true);
				utils.Dialog.showError("Borrar Personaje", "Ha surgido un error al borrar la parte", "");
			}
		}
	}

	@FXML
	void crearEditarPersonaje(ActionEvent event) {

		if (CBPersonajes.getValue() != null) {
			Personaje p = new Personaje();
			p.setNombre(TFTNombre.getText());
			p.setDescripcion(txtDescripcion.getText());
			p.setAlineamiento(TFTAlineamiento.getText());
			p.addLibro(MainLibrosController.currentBook);
			pdao.editar(p);
		} else if (CBPersonajes.getValue() == null) {
			Personaje p = new Personaje();
			p.setNombre(TFTNombre.getText());
			p.setDescripcion(txtDescripcion.getText());
			p.setAlineamiento(TFTAlineamiento.getText());
			p.addLibro(MainLibrosController.currentBook);
			pdao.crear(p);
		}

	}

	@FXML
	private void switchToLibro() throws IOException {
		App.setRoot("MainLibros");
	}

}
