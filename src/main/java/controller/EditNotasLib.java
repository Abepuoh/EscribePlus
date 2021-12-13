package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DAO.Notas_LibDAO;
import model.DataObject.Libro;
import model.DataObject.Notas_Lib;

public class EditNotasLib {
	@FXML
    private ComboBox<Notas_Lib> CBNotas_Lib;

    @FXML
    private TextField TfTitulo;

    @FXML
    private Button buttAñadir;

    @FXML
    private Button buttBorrar;

    @FXML
    private Button buttSalir;

    private Libro libro;
    
    private ObservableList<Notas_Lib> notas_libro;
    
    private Notas_LibDAO ntlibDao = new Notas_LibDAO();
    
    public void initialize() {
    	try {
        	buttBorrar.setDisable(true);
        	buttAñadir.setDisable(true);
    		libro=MainLibrosController.currentBook;
    		this.notas_libro = FXCollections.observableArrayList();
    		this.notas_libro.setAll(ntlibDao.getFromBook(libro));
    		System.out.println(notas_libro);

        	CBNotas_Lib.setItems(notas_libro);
        	CBNotas_Lib.getSelectionModel().selectedItemProperty().addListener((Observable,oldValue,newValue)->{
                buttBorrar.setDisable(false);
            });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    /**
     * Método para crear Notas de libros.
     * Gurdandola en local y en la interfaz
     * @param event
     */
    @FXML
    void añadirNota_Lib(ActionEvent event) {
    	try {
    		if (!TfTitulo.getText().isEmpty()) {
				Notas_Lib n = new Notas_Lib(TfTitulo.getText(),libro);
				ntlibDao.crear(n);
				notas_libro.add(n);
				TfTitulo.setText("");
				buttAñadir.setDisable(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			utils.Dialog.showError("Añadir Nota a Libro", "Ha surgido un error al añadir Nota a Libro", "");
		}
    }

    /**
     * Método para borrar Notas de libros.
     * Gurdandola en local y en la interfaz.
     * @param event
     */
    @FXML
    void borrarNota_Lib(ActionEvent event) {
    	Notas_Lib selected =  CBNotas_Lib.getSelectionModel().getSelectedItem();
    	Boolean confirmacion=utils.Dialog.showConfirm("Confirmación", "¿Quieres borra la Nota?", "Vas a borrar: "+selected.getTexto());
		if (selected != null && confirmacion) {
			try {
	    		ntlibDao.borrar(selected.getId());
	    		notas_libro.remove(selected);
				buttBorrar.setDisable(true);
			} catch (Exception e) {
				buttBorrar.setDisable(true);
				utils.Dialog.showError("Borrar Nota de Libro", "Ha surgido un error al borrar Nota de Libro", "");
			}

		}
    }

    @FXML
    void mostrarBtt() {
		buttAñadir.setDisable(false);
    }

	/**
	 * Método para cerrar ventana modal.
	 * @param event
	 */
    @FXML
    void salir(ActionEvent event) {
    	Stage stage = (Stage) this.buttSalir.getScene().getWindow();
		stage.close();
    }
    /**
     * Funcion para cerrar la aplicación
     * @param event
     */
    @FXML
    void closeApp(ActionEvent event) {
        System.exit(0);

    }
}
