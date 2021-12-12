package controller;

import javax.persistence.EntityManager;

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

public class AñadirLibroController {

	@FXML
    private DatePicker TFAño;
	
    @FXML
    private TextField TFGenero;

    @FXML
    private TextField TFDescripcion;

    @FXML
    private TextField TfTitulo;

    @FXML
    private Button buttAñadir;

    @FXML
    private Button buttSalir;
    
    private static Usuario usuario;


    public void initialize() {
    	
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
    void añadirLibro(ActionEvent event) {
    	UsuarioDAO u=new UsuarioDAO();
    	LibroDAO l = new LibroDAO();
    	Libro l1 = new Libro();
    	try {
    		Libro nuevo = new Libro();
    		try {
        		nuevo = l.getBookByName(TfTitulo.getText());
			} catch (Exception e) {}
        	if (nuevo.getId()<0 && TFAño.getValue().getYear()>1000 && !TFGenero.getText().isEmpty() && !TFDescripcion.getText().isEmpty()) {

        		Libro book1 = new Libro("matanza",1995,"Fanta", "Uorlok sin diente", u.getUserByName("Pepe"));
            	l.crear(l1);
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
			Dialog.showWarning("Añadir Nuevo Libro", "Error ", null);
		}
    	
    }

    @FXML
    void salir(ActionEvent event) {
    	Stage stage = (Stage) this.buttSalir.getScene().getWindow();
        stage.close();
    }

}
