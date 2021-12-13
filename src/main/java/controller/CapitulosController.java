package controller;

import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DAO.CapituloDAO;
import model.DAO.Notas_CapDAO;
import model.DAO.PartesDAO;
import model.DataObject.Capitulo;
import model.DataObject.Libro;
import model.DataObject.Notas_Cap;
import model.DataObject.Partes;
import utils.Dialog;

public class CapitulosController {

    @FXML
    private ComboBox<Partes> CBParte;

    @FXML
    private TableColumn<Capitulo, String> TCCapitulosNombre;

    @FXML
    private TableColumn<Notas_Cap, String> TCDescripcion;

    @FXML
    private TableView<Capitulo> TVCapitulos;

    @FXML
    private TableView<Notas_Cap> TVNotas;

    @FXML
    private Button buttEditarActos;

    @FXML
    private Button buttEditarCap;

    @FXML
    private Button buttEditarNota;
    

    public static Partes currentParte;
    
    private ObservableList<Partes> partes;
    private static ObservableList<Capitulo> capitulos;
    private ObservableList<Notas_Cap> Notas_Cap;


    private static Libro milibro;
    public static Capitulo currentCapitulo;
    private PartesDAO parDao=new PartesDAO();
    private CapituloDAO cadao=new CapituloDAO(); 
    private Notas_CapDAO ndao=new Notas_CapDAO();

    public void initialize() {
    	try {
    		 milibro=MainLibrosController.currentBook;
    			this.partes = FXCollections.observableArrayList();
    			this.partes.setAll(parDao.getByLibro(milibro)); 
    			buttEditarCap.setDisable(true);
    			buttEditarNota.setDisable(true);
    	        CBParte.setItems(partes);
    	        CBParte.getSelectionModel().selectedItemProperty().addListener((Observable,oldValue,newValue)->{
    	        	if(newValue!=null) {
    	        		currentParte=newValue;
    	        		buttEditarCap.setDisable(false);
    	        		configuratablacapitulos(newValue);    	        		
    	        	}
    	        });
		} catch (Exception e) {
			Dialog.showError("Error", "", "");
		}
       
    }
	/**
	 * Metodo que configura la tabla de capitulos con los datos de la base de datos
	 * @param p Parte del libro seleccionado
	 */
	private void configuratablacapitulos(Partes p) {
		try {
			capitulos = FXCollections.observableArrayList();
			capitulos.setAll(cadao.getByParte(currentParte));

			TCCapitulosNombre.setCellValueFactory(
					cadenaCapNombre -> new SimpleStringProperty(cadenaCapNombre.getValue().getName()));

			TVCapitulos.setEditable(true);
			TVCapitulos.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue) -> {
				if (newValue!=null) {
					currentCapitulo=newValue;
					configuratablaNotas(newValue);
				}

			});
			TVCapitulos.setItems(capitulos);
		} catch (Exception e) {
			e.printStackTrace();
			Dialog.showError("Error", "", "");
		}
	}
	/**
	 * Metodo que configura la tabla de notas con los datos de la base de datos	
	 * @param c Capitulo seleccionado
	 */
	private void configuratablaNotas(Capitulo c) {
		try { 
			buttEditarNota.setDisable(false);
			this.Notas_Cap = FXCollections.observableArrayList();
			this.Notas_Cap.setAll(ndao.getFromCapitulos(currentCapitulo));
			TCDescripcion.setCellValueFactory(
					cadenaCapNombre -> new SimpleStringProperty(cadenaCapNombre.getValue().getText()));

			TVNotas.setItems(Notas_Cap);
			TVNotas.refresh();
		} catch (Exception e) {
			e.printStackTrace();
			Dialog.showError("Error", "", "");
		}

	}
	/**
	 * Metodo que abre la ventana de edicion de capitulos
	 * @param event Evento de click
	 * @throws IOException Excepcion de entrada/salida
	 */
    @FXML
    void editActos(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("EditPartes.fxml"));
		Parent modal;
		try {
			modal = fxmlLoader.load();
			Stage modalStage = new Stage();
			modalStage.initModality(Modality.APPLICATION_MODAL);
			modalStage.initOwner(App.rootstage);
			Scene modalScene = new Scene(modal);
			if (MainLibrosController.currentBook!=null) {
				modalStage.setTitle("EDITAR PARTES DE : "+MainLibrosController.currentBook.getTitle());
			} else {
				modalStage.setTitle("EDITAR PARTES");
			}
			modalStage.setScene(modalScene);
			modalStage.showAndWait();
			modalStage.setResizable(false);
			initialize();
		} catch (IOException e) {
			e.printStackTrace();
			Dialog.showError("Error", "", "");
		}
    }
	/**
	 * Metodo que abre la ventana de edicion de capitulos
	 * @param event Evento de click
	 * @throws IOException Excepcion de entrada/salida
	 */
    @FXML
    void editarCap(ActionEvent event) throws IOException{
    	FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("EditCapitulos.fxml"));
		Parent modal;
		try {
			modal = fxmlLoader.load();
			Stage modalStage = new Stage();
			modalStage.initModality(Modality.APPLICATION_MODAL);
			modalStage.initOwner(App.rootstage);
			Scene modalScene = new Scene(modal);
			if (CBParte.getValue()!=null) {
				modalStage.setTitle("CAPITULOS DE: "+CBParte.getValue().getNombre());
			} else {
				modalStage.setTitle("EDITAR CAPITULOS");
			}
			modalStage.setScene(modalScene);
			modalStage.showAndWait();
			modalStage.setResizable(false);
			initialize();
		} catch (IOException e) {
			e.printStackTrace();
			Dialog.showError("Error", "", "");
		}
    }
	/**
	 * Metodo que abre la ventana de edicion de notas
	 * @param event Evento de click	
	 * @throws IOException Excepcion de entrada/salida
	 */
    @FXML
    void editarNota(ActionEvent event) throws IOException{
    	FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("EditNotas_Capitulo.fxml"));
		Parent modal;
		try {
			modal = fxmlLoader.load();
			Stage modalStage = new Stage();
			modalStage.initModality(Modality.APPLICATION_MODAL);
			modalStage.initOwner(App.rootstage);
			Scene modalScene = new Scene(modal);
			if (TVCapitulos.getSelectionModel().getSelectedItem()!=null) {
				modalStage.setTitle("EDITAR NOTAS DE : "+TVCapitulos.getSelectionModel().getSelectedItem().getName());
			} else {
				modalStage.setTitle("EDITAR NOTAS DE : ");
			}
			modalStage.setScene(modalScene);
			modalStage.showAndWait();
			modalStage.setResizable(false);
			initialize();
		} catch (IOException e) {
			e.printStackTrace();
			Dialog.showError("Error", "", "");
		}
    }
	/**
	 * Método que abre la ventana de edicion de capitulos doble click y podemos editar el capitulo.
	 */
    @FXML
    void irCapitulo() {
    	try {
    		currentCapitulo=TVCapitulos.getSelectionModel().getSelectedItem();
			App.setRoot("EscribirCap");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	/**
	 * Método que nos permite volver a la ventana de libros
	 */
    @FXML
    private void switchToLibro()  {
        try {
			App.setRoot("MainLibros");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	/**
	 * Método que nos permite cerra la aplicacion
	 */
    @FXML
    public void closeApp() {
        System.exit(0);
    }
}