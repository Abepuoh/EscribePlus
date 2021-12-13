package controller;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.DAO.LibroDAO;
import model.DAO.Notas_LibDAO;
import model.DataObject.Libro;
import model.DataObject.Notas_Lib;
import model.DataObject.Usuario;

public class EditarNotasController {

    @FXML
    private ChoiceBox<Libro> CBCREARNLibros;

    @FXML
    private ChoiceBox<Libro> CBEditNLibros;

    @FXML
    private ChoiceBox<Notas_Lib> CBEditNotas;

    @FXML
    private ChoiceBox<Libro> CBRemoveNLibro;

    @FXML
    private ChoiceBox<Notas_Lib> CBRemoveNota;

    @FXML
    private TextField TFCrearNDescripcion;
    @FXML
    private TextField TFNotaDescripcion;

    @FXML
    private Button buttBorrar;

    @FXML
    private Button buttCrear;

    @FXML
    private Button buttEditarNota;

    private static Usuario autor= new Usuario();
    Notas_LibDAO nldao=new Notas_LibDAO();
    LibroDAO ldao=new LibroDAO();

    public void initialize() {
    	CBEditNLibros.getItems().addAll(ldao.getBooksByAuthor(autor));
    	CBEditNLibros.getSelectionModel().selectedItemProperty().addListener((Observable,oldValue,newValue)->{
    		choiceNotas(newValue);
    	});
    	CBCREARNLibros.getItems().addAll(ldao.getBooksByAuthor(autor));
    	
    	CBRemoveNLibro.getItems().addAll(ldao.getBooksByAuthor(autor));
    	CBRemoveNLibro.getSelectionModel().selectedItemProperty().addListener((Observable,oldValue,newValue)->{
    		choiceNotas(newValue);
    	});
    }
    
    private void choiceNotas(Libro lb) {
        try {
            List<Notas_Lib> nl=nldao.getFromBook(lb);
            CBEditNotas.getItems().clear();
            CBEditNotas.getItems().addAll(nl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    }
    /**
     * Metodo que nos permite borrar una nota
     * @param event Evento que se produce al pulsar el boton
     */
    @FXML
    public void borrarNota(ActionEvent event) {
    	if(!CBRemoveNota.getSelectionModel().isEmpty()) {
            try {
                Long id=CBRemoveNota.getSelectionModel().getSelectedItem().getId();
                nldao.borrar(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
    	}
    }
    /**
     * Metodo que nos permite crear una nota
     * @param event Evento que se produce al pulsar el boton
     */
    @FXML
    public void crearNota(ActionEvent event) {
    	if(!CBCREARNLibros.getSelectionModel().isEmpty()&&!TFCrearNDescripcion.getText().isBlank()) {
    		String descripcion=TFCrearNDescripcion.getText();
    		Libro lb=CBCREARNLibros.getSelectionModel().getSelectedItem();
    		try {
                Notas_Lib nl=new Notas_Lib(descripcion, lb);
                nldao.crear(nl);
            } catch (Exception e) {
                e.printStackTrace();
            }
    	}
    }
    /**
     * Metodo que nos permite editar una nota
     * @param event Evento que se produce al pulsar el boton
     */
    @FXML
    public void editarNota(ActionEvent event) {
    	if(!CBEditNLibros.getSelectionModel().isEmpty()&&!CBEditNotas.getSelectionModel().isEmpty()) {
    		String descripcion=TFNotaDescripcion.getText();
    		Long id=CBEditNotas.getSelectionModel().getSelectedItem().getId();
    		Libro lb=CBEditNLibros.getSelectionModel().getSelectedItem();
    		try {
                Notas_Lib nl=new Notas_Lib(id, descripcion, lb);
                nldao.editar(nl);
            } catch (Exception e) {
                e.printStackTrace();
            }
    	}
    }
    public static void setautor(Usuario u) {
    	autor=u;
    }
    /**
     * Método que nos permite volver a la vista anterior
     * @throws IOException
     */
    @FXML
    private void switchToLibro() throws IOException {
        App.setRoot("MainLibros");
    }
    /**
     * Método que nos permite cerrar la aplicación
     */
    @FXML
    public void closeApp() {
    	System.exit(0);
    }
}
