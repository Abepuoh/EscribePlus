package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DAO.LibroDAO;
import model.DataObject.Libro;
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

    private void initialize() {

	}
    /**
     * Método para crear un nuevo libro verificando que no exista.
     * @param event
     */
    @FXML
    void añadirLibro(ActionEvent event) {
    	LibroDAO l = new LibroDAO();
    	try {
    		Libro nuevo = l.getBookByName(TfTitulo.getText());
        	if (nuevo!=null) {
            	System.out.println(TfTitulo.getText()+" "+TFGenero.getText()+" "+TFAño.getValue()+" "+TFDescripcion.getText());

    		}else {
    			Dialog.showWarning("Ya existe ese Libro", "Prueba con otro nombre", null);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
    }

    @FXML
    void salir(ActionEvent event) {
    	Stage stage = (Stage) this.buttSalir.getScene().getWindow();
        stage.close();
    }

}
