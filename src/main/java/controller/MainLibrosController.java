package controller;

import java.io.IOException;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DAO.LibroDAO;
import model.DAO.Notas_LibDAO;
import model.DataObject.Libro;
import model.DataObject.Notas_Lib;
import model.DataObject.Usuario;

public class MainLibrosController {
	

    @FXML
    private MenuItem MEditUser;

    @FXML
    private MenuItem MIEditarPersonajes;
    
    @FXML
    private TableColumn<Libro, Integer> TCLibroAño;

    @FXML
    private TableColumn<Libro, String> TCLibroDescripcion;

    @FXML
    private TableColumn<Libro, String> TCLibroGenero;

    @FXML
    private TableColumn<Libro, String> TCLibroTitulo;

    @FXML
    private TableColumn<Notas_Lib, String> TCNotasDescripcion;

    @FXML
    private TableColumn<Notas_Lib, Long> TCNotasNombre;

    @FXML
    private TableView<Libro> TVLibro;

    @FXML
    private TableView<Notas_Lib> TVNotas;

    @FXML
    private Button buttAñadirLibro;

    @FXML
    private Button buttBorrarLibro;

    @FXML
    private Button buttEditarLibro;
    
    @FXML
    private Button buttEditarNotas;
    
    
    private ObservableList<Libro> Libros;
    
    private ObservableList<Notas_Lib> Notas;
    
    private static Usuario usuario;
    
    public static Libro currentBook;
    
    public void initialize() {
    	utils.UsuarioSingleton transfer = utils.UsuarioSingleton.getInstance();
		usuario = transfer.getUser(); 
    	configuraTablaLibros();
    }
    
    @FXML
	void EditarNotas(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("EditNotasLibro.fxml"));
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
			configuraTablaLibros();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }


    @FXML
    void añadirLibro(ActionEvent event) {
    	
    	FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AñadirLibro.fxml"));
		Parent modal;
		try {
			modal = fxmlLoader.load();
			Stage modalStage = new Stage();
			modalStage.initModality(Modality.APPLICATION_MODAL);
			modalStage.initOwner(App.rootstage);
			AñadirLibroController.initController();
			Scene modalScene = new Scene(modal);
			modalStage.setScene(modalScene);
			modalStage.showAndWait();
			modalStage.setResizable(false);
	    	configuraTablaLibros();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }

    @FXML
    void borrarLibro(ActionEvent event) {
    	Boolean confirmacion=utils.Dialog.showConfirm("Confirmación", "¿Quieres borra el Libro?", "");
		Libro selected = TVLibro.getSelectionModel().getSelectedItem();
		if (selected != null && confirmacion) {
			try {
				buttBorrarLibro.setDisable(false);
	    		LibroDAO l = new LibroDAO();
	    		l.borrar(selected.getId());
	    		Libros.remove(selected);
				configuraTablaLibros();

			} catch (Exception e) {
				buttBorrarLibro.setDisable(true);
				utils.Dialog.showError("Borrar Genero", "Ha surgido un error al borrar el genero", "");
			}

		}
    }

    @FXML
    void editarLibro(ActionEvent event) {
    	
    	FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("EditLibros.fxml"));
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
    void editPersonajes(ActionEvent event) {
    	FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("personajes.fxml"));
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
	public void abrirLibro() {
		try {
			currentBook=TVLibro.getSelectionModel().getSelectedItem();
			App.setRoot("Capitulos");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
    /**
     * Método para la configuración de las columnas de la tabla de libros.
     */
    private void configuraTablaLibros() {
    	try {
    		LibroDAO l = new LibroDAO();
    		this.Libros = FXCollections.observableArrayList();
    		this.Libros.setAll(l.getBooksByAuthor(usuario));

    		TCLibroTitulo.setCellValueFactory(cellData -> {
    			return new SimpleObjectProperty<>(cellData.getValue().getTitle());
    		});
    		TCLibroAño.setCellValueFactory(cellData -> {
    			return new SimpleObjectProperty<>(cellData.getValue().getYear());
    		});
    		TCLibroDescripcion.setCellValueFactory(cellData -> {
    			return new SimpleObjectProperty<>(cellData.getValue().getDescription());
    		});
    		TCLibroGenero.setCellValueFactory(cellData -> {
    			return new SimpleObjectProperty<>(cellData.getValue().getGenre());
    		});

    		TVLibro.setEditable(true);
    		TVLibro.getSelectionModel().selectedItemProperty()
    				.addListener((observable, oldvalue, newvalue) -> showLibroButt(newvalue));
    		TVLibro.setItems(Libros);

    		showLibroButt(null);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
    
    /**
     * Método para la configuración de las columnas de la tabla de notas de libros.
     */
    private void configuraTablaNotas() {
    	try {
    		Notas_LibDAO n = new Notas_LibDAO();
    		this.Notas = FXCollections.observableArrayList();
    		this.Notas.setAll(n.getAll());
    		//this.Notas.setAll(n.getByBook(TVLibro.getSelectionModel().getSelectedItem()));

    		TCNotasDescripcion.setCellValueFactory(cellData -> {
    			return new SimpleObjectProperty<>(cellData.getValue().getTexto());
    		});
    		TCNotasNombre.setCellValueFactory(cellData -> {
    			return new SimpleObjectProperty<>(cellData.getValue().getId());
    		});
    		
    		TVNotas.getSelectionModel().selectedItemProperty()
    				.addListener((observable, oldvalue, newvalue) -> showNotasButt(newvalue));
    		TVNotas.setItems(Notas);

    		showNotasButt(null);
    		
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * Habilita los botones para los libros.
	 * Ademas de mostrar las notas del libro.
	 * 
	 * @param libro selecionado.
	 */
	private void showLibroButt(Libro libro) {
		if (libro != null) {
			buttBorrarLibro.setDisable(false);
			buttEditarLibro.setDisable(false);
			currentBook=libro;
			configuraTablaNotas();
		} else {
			buttBorrarLibro.setDisable(true);
			buttEditarLibro.setDisable(true);
			buttEditarNotas.setDisable(true);

		}
	}
	/**
	 * Habilita los botones para los libros.
	 * Ademas de mostrar las notas del libro.
	 * 
	 * @param Nota_libro selecionado.
	 */
	private void showNotasButt(Notas_Lib Nota_libro) {
		if (Nota_libro != null) {
			buttEditarNotas.setDisable(false);
		} else {
			buttEditarNotas.setDisable(true);

		}

	}
    @FXML
    void editUsuario(ActionEvent event) {

    	FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("EditUser.fxml"));
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
}
   
