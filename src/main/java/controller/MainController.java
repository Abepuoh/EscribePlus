package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import utils.ConnectionUtil;

public class MainController implements Initializable {

	@FXML
	private ComboBox<String> CBBBDD;
	@FXML
	private Button buttConect;

	@FXML
	private VBox vbox;
	private Parent fxml;
	ObservableList<String> BBDD = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// cargar ComboBox con BBDD
		BBDD.add("AplicacionH2");
		BBDD.add("aplicacionMariaDB");
		CBBBDD.setItems(BBDD);
		TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
		t.setToX(vbox.getLayoutX() * 20);
		t.play();
		t.setOnFinished((e) -> {
			try {
				fxml = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
				vbox.getChildren().removeAll();
				vbox.getChildren().setAll(fxml);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		});
	}
	
    @FXML
    void conectar(ActionEvent event) {
    	if(CBBBDD.getSelectionModel().getSelectedItem()!=null) {
    		if(CBBBDD.getValue()=="aplicacionMariaDB") {
    			ConnectionUtil.choice=true;
    		}
    	}
    }

	@FXML
	private void open_signin(ActionEvent event) {
		TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
		t.setToX(vbox.getLayoutX() * 20);
		t.play();
		t.setOnFinished((e) -> {
			try {
				fxml = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
				vbox.getChildren().removeAll();
				vbox.getChildren().setAll(fxml);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		});
	}

	@FXML
	private void open_signup(ActionEvent event) {
		TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
		t.setToX(0);
		t.play();
		t.setOnFinished((e) -> {
			try {
				fxml = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
				vbox.getChildren().removeAll();
				vbox.getChildren().setAll(fxml);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		});
	}
}
