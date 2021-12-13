package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DAO.PartesDAO;
import model.DataObject.Libro;
import model.DataObject.Partes;

public class EditPartesController {

	@FXML
	private ComboBox<Partes> CBPartes;
	
    @FXML
    private TextField TfTitulo;

    @FXML
    private Button buttAñadir;

    @FXML
    private Button buttBorrar;

    @FXML
    private Button buttSalir;

    private Libro libro;
    
    private ObservableList<Partes> partes;

     
    private PartesDAO parDao=new PartesDAO();
    
    public void initialize() {
    	try {
    		libro=MainLibrosController.currentBook;
    		this.partes = FXCollections.observableArrayList();
    		this.partes.setAll(parDao.getByLibro(libro)); 
        	buttBorrar.setDisable(true);
        	buttAñadir.setDisable(true); 
            CBPartes.getSelectionModel().selectedItemProperty().addListener((Observable,oldValue,newValue)->{
                buttBorrar.setDisable(false);
            });
		} catch (Exception e) {} 
	}
    
    @FXML
    public void mostrarBtt() {
    	buttAñadir.setDisable(false);
    } 
    
    @FXML
    void añadirParte(ActionEvent event) {
    	try {
    		if (!TfTitulo.getText().isEmpty()) {
				Partes p = new Partes(TfTitulo.getText(), libro);
				parDao.crear(p);
				partes.add(p);
				TfTitulo.setText("");
				buttAñadir.setDisable(true);
			}
		} catch (Exception e) {
			utils.Dialog.showError("Añadir Parte", "Ha surgido un error al añadir la parte", "");
		}
    }

    @FXML
    void borrarParte(ActionEvent event) {
    	Partes selected =  CBPartes.getSelectionModel().getSelectedItem();
    	Boolean confirmacion=utils.Dialog.showConfirm("Confirmación", "¿Quieres borra la parte?", "Vas a borrar: "+selected.getNombre());
		if (selected != null && confirmacion) {
			try {
	    		parDao.borrar(selected.getId());
	    		partes.remove(selected);
			} catch (Exception e) {
				buttBorrar.setDisable(true);
				utils.Dialog.showError("Borrar Parte", "Ha surgido un error al borrar la parte", "");
			}

		}
    }

    @FXML
    void salir(ActionEvent event) {
    	Stage stage = (Stage) this.buttSalir.getScene().getWindow();
        stage.close();
    }

}