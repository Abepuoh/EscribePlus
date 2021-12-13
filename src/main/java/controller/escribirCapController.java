package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import model.DAO.CapituloDAO;
import model.DataObject.Capitulo;

public class escribirCapController {

    @FXML
    private Button ButtGuardar;

    @FXML
    private Label LabelNombre;

    @FXML
    private MenuItem MIVolver;

    @FXML
    private TextArea textArea;
    
    private static Capitulo currentCapitulo;
    public CapituloDAO capDAO = new CapituloDAO();

    @FXML
    public void initialize() {
      try {    
        currentCapitulo=capDAO.getById(CapitulosController.currentCapitulo.getId());
        textArea.setText(currentCapitulo.getText()); 	
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    @FXML
    void guardarCap(ActionEvent event) {
    	String texto = textArea.getText();
    	try {
			if(texto != null) {
				currentCapitulo.setText(texto);
				capDAO.editar(currentCapitulo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }         
    @FXML
    void volver(ActionEvent event) {
    	  try {
			App.setRoot("Capitulos");
		} catch (IOException e) {
			e.printStackTrace();
		}
  }
}
