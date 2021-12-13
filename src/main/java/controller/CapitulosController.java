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

    
	private void configuratablacapitulos(Partes p) {
		try {
			capitulos = FXCollections.observableArrayList();
			capitulos.setAll(cadao.getByParte(currentParte));

			TCCapitulosNombre.setCellValueFactory(
					cadenaCapNombre -> new SimpleStringProperty(cadenaCapNombre.getValue().getName()));

			TVCapitulos.setEditable(true);
			TVCapitulos.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue) -> {
				configuratablaNotas(newValue);
			});
			TVCapitulos.setItems(capitulos);
		} catch (Exception e) {}
	}

	private void configuratablaNotas(Capitulo c) {
		try { 
			this.Notas_Cap = FXCollections.observableArrayList();
			this.Notas_Cap.setAll(ndao.getFromCapitulos(currentCapitulo));
			TCDescripcion.setCellValueFactory(
					cadenaCapNombre -> new SimpleStringProperty(cadenaCapNombre.getValue().getText()));

			TVNotas.setItems(Notas_Cap);
			TVNotas.refresh();
		} catch (Exception e) {
		}

	}

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
			modalStage.setScene(modalScene);
			modalStage.showAndWait();
			modalStage.setResizable(false);
			initialize();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

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
			modalStage.setScene(modalScene);
			modalStage.showAndWait();
			modalStage.setResizable(false);
			initialize();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void editarNota(ActionEvent event) throws IOException{
    	FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("EditNotasCap.fxml"));
		Parent modal;
		try {
			modal = fxmlLoader.load();
			Stage modalStage = new Stage();
			modalStage.initModality(Modality.APPLICATION_MODAL);
			modalStage.initOwner(App.rootstage);
			Scene modalScene = new Scene(modal);
			modalStage.setScene(modalScene);
			modalStage.showAndWait();
			modalStage.setResizable(false);
			initialize();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void irCapitulo() {
    	try {
    		currentCapitulo=TVCapitulos.getSelectionModel().getSelectedItem();
			App.setRoot("EscribirCap");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    private void switchToLibro() throws IOException {
        App.setRoot("MainLibros");
    }
    @FXML
    public void closeApp() {
        System.exit(0);
    }
}