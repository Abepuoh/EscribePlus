package controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DAO.CapituloDAO;
import model.DAO.Notas_CapDAO;
import model.DataObject.Capitulo;
import model.DataObject.Notas_Cap;
import model.DataObject.Partes;

public class EditNotasCap {

    @FXML
    private ComboBox<Notas_Cap> CBNotas_Cap;

    @FXML
    private TextField TfTitulo;

    @FXML
    private Button buttAñadir;

    @FXML
    private Button buttBorrar;

    @FXML
    private Button buttSalir;
    
    private Capitulo capitulo;
    
    private ObservableList<Notas_Cap> notas_capitulo;
    
    private Notas_CapDAO ntcapDao = new Notas_CapDAO();

    public void initialize() {
    	try {
        	buttBorrar.setDisable(true);
        	buttAñadir.setDisable(true);
    		capitulo=CapitulosController.currentCapitulo;
    		this.notas_capitulo = FXCollections.observableArrayList();
    		this.notas_capitulo.setAll(ntcapDao.getFromCapitulos(capitulo));
    		System.out.println(notas_capitulo);

        	CBNotas_Cap.setItems(notas_capitulo);
        	CBNotas_Cap.getSelectionModel().selectedItemProperty().addListener((Observable,oldValue,newValue)->{
                buttBorrar.setDisable(false);
            });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    @FXML
    void añadirNota_Cap(ActionEvent event) {
    	try {
    		if (!TfTitulo.getText().isEmpty()) {
				Notas_Cap n = new Notas_Cap(TfTitulo.getText(),capitulo);
				ntcapDao.crear(n);
				notas_capitulo.add(n);
				TfTitulo.setText("");
				buttAñadir.setDisable(true);
			}
		} catch (Exception e) {
			utils.Dialog.showError("Añadir Nota a Capitulo", "Ha surgido un error al añadir Nota al Capitulo", "");
		}
    }

    @FXML
    void borrarNota_Cap(ActionEvent event) {
    	Notas_Cap selected =  CBNotas_Cap.getSelectionModel().getSelectedItem();
    	Boolean confirmacion=utils.Dialog.showConfirm("Confirmación", "¿Quieres borra el Capitulo?", "Vas a borrar: "+selected.getText());
		if (selected != null && confirmacion) {
			try {
	    		ntcapDao.borrar(selected.getId());
	    		notas_capitulo.remove(selected);
				buttBorrar.setDisable(true);
			} catch (Exception e) {
				buttBorrar.setDisable(true);
				utils.Dialog.showError("Borrar Nota de Capitulo", "Ha surgido un error al borrar Nota de Capitulo", "");
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

