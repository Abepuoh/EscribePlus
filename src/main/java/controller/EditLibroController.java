package controller;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DAO.LibroDAO;
import model.DAO.UsuarioDAO;
import model.DataObject.Libro;
import model.DataObject.Usuario;
import utils.Dialog;

public class EditLibroController {

	@FXML
    private DatePicker TFAño;
	
    @FXML
    private TextField TFGenero;

    @FXML
    private TextField TFDescripcion;

    @FXML
    private TextField TfTitulo;

    @FXML
    private Button buttEditar;
 
    @FXML
    private Button buttSalir;

    private static Usuario usuario;

    private static Libro currentLibro;

    public void initialize() {
    	LibroDAO l = new LibroDAO();
		try {
			currentLibro=l.getBookByName(MainLibrosController.currentBook.getTitle());
			TfTitulo.setText(currentLibro.getTitle()); 
			TFGenero.setText(currentLibro.getGenre());
			TFDescripcion.setText(currentLibro.getDescription());
			TFAño.setValue(LocalDate.of(currentLibro.getYear(), 1, 1));
		} catch (Exception e) {
			e.printStackTrace();
		}

    }
    /**
     * Setea un usuario recibido por el login;
     * @param u
     */
    public static void initController() {
		utils.UsuarioSingleton transfer = utils.UsuarioSingleton.getInstance();
		usuario = transfer.getUser();
	}

    /**
     * Método para crear un nuevo libro verificando que no exista.
     * @param event
     */
    @FXML
    void editarLibro(ActionEvent event) {
    	UsuarioDAO u=new UsuarioDAO();
    	LibroDAO l = new LibroDAO();
    	Libro l1 = new Libro();
    	try {
    		Libro nuevo = new Libro();
    		try {
        		nuevo = l.getBookByName(TfTitulo.getText());
			} catch (Exception e) {}
        	if (nuevo.getId()>0 && !TfTitulo.getText().isEmpty() &&TFAño.getValue().getYear()>1000 && !TFGenero.getText().isEmpty() && !TFDescripcion.getText().isEmpty()) {
        		Libro n = l.getBookByName(MainLibrosController.currentBook.getTitle());;
        		n.setTitle(TfTitulo.getText());
        		n.setYear(TFAño.getValue().getYear());
        		n.setGenre(TFGenero.getText());
        		n.setDescription(TFDescripcion.getText());
        		l.editar(n);
            	salir(null);
    		}else {
    			if (TFGenero.getText().isEmpty()) {
    				Dialog.showWarning("Añadir Nuevo Libro", "Rellene el genero", null);

				}else if (TFDescripcion.getText().isEmpty()) {
					Dialog.showWarning("Añadir Nuevo Libro", "Rellene la descripción", null);

				}else {
					Dialog.showWarning("Ya existe ese Libro", "Prueba con otro nombre", null);					
				}
    		}
		} catch (Exception e) {
			e.printStackTrace();
			Dialog.showWarning("Añadir Nuevo Libro", "Rellene los campos ", null);
		}
    	
    }

    @FXML
    void salir(ActionEvent event) {
        Stage stage = (Stage) this.buttSalir.getScene().getWindow();
        stage.close();	
    }

}
