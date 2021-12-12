package controller;

import java.io.IOException;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
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
    


    private static Libro milibro;
    private PartesDAO parDao=new PartesDAO();
    private CapituloDAO cadao=new CapituloDAO();
    private Notas_CapDAO ndao=new Notas_CapDAO();

    public void initialize() {
        milibro=MainLibrosController.currentBook;
        configuraTablas();
        CBParte.getItems().addAll(parDao.getByLibro(milibro));
        CBParte.getSelectionModel().selectedItemProperty().addListener((Observable,oldValue,newValue)->{
            tablacapitulos(newValue);
        });
        TVCapitulos.getSelectionModel().selectedItemProperty().addListener((Observable,oldValue,newValue)->{
            tablaNotas(newValue);
        });
    }

    public void configuraTablas() {
        TCCapitulosNombre.setCellValueFactory(cadenaCapNombre->
            new SimpleStringProperty(cadenaCapNombre.getValue().getName()));

        TCDescripcion.setCellValueFactory(cadenaCapNombre->
        new SimpleStringProperty(cadenaCapNombre.getValue().getText()));



    }
    private void tablacapitulos(Partes p) {
        List<Capitulo> capitulos= cadao.getByParte(p);
        TVCapitulos.setItems(FXCollections.observableList(capitulos));
        TVCapitulos.refresh();
    }
    private void tablaNotas(Capitulo c) {
        List<Notas_Cap> notas= ndao.getFromCapitulos(c);
        TVNotas.setItems(FXCollections.observableList(notas));
        TVNotas.refresh();

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
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void irCapitulo(ActionEvent event) {
    	try {
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