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
import model.DataObject.Usuario;
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

		if (!TFEmail.getText().isEmpty() && !TFPassword.getText().isEmpty()) {
			;
			Usuario user = aux.logIn(email, password);

			if (user != null) {
				UsuarioSingleton usuarioSignleton = UsuarioSingleton.getInstance();
				usuarioSignleton.setUser(user);
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("CORRECTO");
				alert.setContentText("El email y la contraseña son correctos");
				alert.showAndWait();
				try {
					App.setRoot("MainScreen");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("Error al introducir email o contraseña");
				this.TFEmail.clear();
				this.TFPassword.clear();
				alert.showAndWait();
			}
		}
	}
}
