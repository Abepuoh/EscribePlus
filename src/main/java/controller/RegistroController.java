package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.DAO.UsuarioDAO;
import model.DataObject.Usuario;
import utils.UsuarioSingleton;

public class RegistroController {

    @FXML
    private TextField TFEmail;

    @FXML
    private TextField TFNombre;

    @FXML
    private PasswordField TFPassword;

    @FXML
    private Button buttRegistro;
    
	/**
	 * Metodo que sirve para guardar usuarios nuevos en la base de datos
	 * @param event
	 */
	@FXML
	void saveUser(ActionEvent event) {

		String name = this.TFNombre.getText();
		String email = this.TFEmail.getText();
		String password = this.TFPassword.getText();
		UsuarioDAO aux = new UsuarioDAO();

		if (!this.TFNombre.getText().trim().isEmpty() && !this.TFPassword.getText().trim().isEmpty()
				&& !this.TFEmail.getText().trim().isEmpty()) {
			Usuario user = new Usuario(name,email,password);
			try {
				if (!aux.getAll().contains(user)) {
					UsuarioSingleton usuarioSignleton = UsuarioSingleton.getInstance();
					usuarioSignleton.setUser(user);
					aux.crear(user);
					utils.Dialog.showDialog(Alert.AlertType.INFORMATION, "Registro", "Registro exitoso",
							"Usuario registrado correctamente");
					App.setRoot("MainLibros");
				}
			} catch (Exception e) {
				e.printStackTrace();
				utils.Dialog.showDialog(Alert.AlertType.ERROR, "Registro", "Error", "Error al registrar usuario");
			}
		} else {
			utils.Dialog.showDialog(Alert.AlertType.ERROR, "Registro", "Error", "No se puede dejar campos vacios");
		}
	}
}
