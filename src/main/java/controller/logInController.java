package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DAO.UsuarioDAO;
import model.IDAO.DAOException;
import utils.UsuarioSingleton;

public class logInController {

    @FXML
    private TextField TFEmail;

    @FXML
    private PasswordField TFPassword;

    @FXML
    private Button buttAcceder;
    
    @FXML
	public void initialize() {
	}
    
    @FXML
    void logIn(ActionEvent event) {
    	String email = this.TFEmail.getText();
		String password = this.TFPassword.getText();
		UsuarioDAO aux = new UsuarioDAO();
		
		try {
			if (aux.logIn(email, password)) {
				this.TFEmail.clear();
				this.TFPassword.clear();
				UsuarioSingleton data = UsuarioSingleton.getInstance();
				data.setUser(aux.getUsuarioByNombreContraseña(email, password));
				FXMLLoader loader = new FXMLLoader(getClass().getResource("MainLibros.fxml"));
				Parent root = loader.load();
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.showAndWait();
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("Error de acceso");
				alert.setContentText("Has introducido mal algun dato");
				alert.showAndWait();
			}
		} catch (DAOException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
