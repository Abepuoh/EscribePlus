package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.DAO.CapituloDAO;
import model.DataObject.Capitulo;
import model.DataObject.Partes;

public class EditCapitulosController {

	@FXML
	private ComboBox<Capitulo> CBCapitulos;

	@FXML
	private TextField TfTitulo;

	@FXML
	private Button buttAñadir;

	@FXML
	private Button buttBorrar; 

	@FXML
	private Button buttSalir;
	
    private Partes parte;
    
    private ObservableList<Capitulo> capitulos;
    
    private CapituloDAO capDao=new CapituloDAO();
    
    public void initialize() {
    	try {
    		parte=CapitulosController.currentParte;
    		this.capitulos = FXCollections.observableArrayList();
    		this.capitulos.setAll(capDao.getByParte(parte));
        	buttBorrar.setDisable(true);
        	buttAñadir.setDisable(true);
        	CBCapitulos.setItems(capitulos);
        	CBCapitulos.getSelectionModel().selectedItemProperty().addListener((Observable,oldValue,newValue)->{
                buttBorrar.setDisable(false);
            });
		} catch (Exception e) {}
	}
    
	@FXML
	void añadirCapitulo(ActionEvent event) {
    	try {
    		if (!TfTitulo.getText().isEmpty()) {
				Capitulo p = new Capitulo(TfTitulo.getText());
				p.setParte(parte);
				capDao.crear(p);
				capitulos.add(p);
				TfTitulo.setText("");
				buttAñadir.setDisable(true);
			}
		} catch (Exception e) {
			utils.Dialog.showError("Añadir Parte", "Ha surgido un error al añadir el Capitulo", "");
		}
	}

	@FXML
	void borrarCapitulo(ActionEvent event) {
		Capitulo selected =  CBCapitulos.getSelectionModel().getSelectedItem();
    	Boolean confirmacion=utils.Dialog.showConfirm("Confirmación", "¿Quieres borra el Capitulo?", "Vas a borrar: "+selected.getName());
		if (selected != null && confirmacion) {
			try {
	    		capDao.borrar(selected.getId());
	    		capitulos.remove(selected);
			} catch (Exception e) {
				buttBorrar.setDisable(true);
				utils.Dialog.showError("Borrar Parte", "Ha surgido un error al borrar el Capitulo", "");
			}

		}
	}

	@FXML
	void mostrarBtt(KeyEvent event) {
		buttAñadir.setDisable(false);
	}

	@FXML
	void salir(ActionEvent event) {
		Stage stage = (Stage) this.buttSalir.getScene().getWindow();
		stage.close();
	}

}
