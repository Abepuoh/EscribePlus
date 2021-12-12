package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class escribirCapController {

    @FXML
    private Button ButtGuardar;

    @FXML
    private Label LabelNombre;

    @FXML
    private MenuItem MIVolver;

    @FXML
    private TextArea textArea;

    @FXML
    void guardarCap(ActionEvent event) {

    }

    @FXML
    void volver(ActionEvent event) {
    	  try {
			App.setRoot("Capitulos");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
