package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DAO.UsuarioDAO;
import model.DataObject.Usuario;

public class EditUserController {

	@FXML
	private TextField TFTContraseña;

	@FXML
	private TextField TFTEmail;

	@FXML
	private TextField TFTUsuario;

	@FXML
	private TextField TFTelefono;

	@FXML
	private Button buttEditar;

	@FXML
	private Button buttSalir;

	private Usuario usuario;

	public void initialize() {
		utils.UsuarioSingleton transfer = utils.UsuarioSingleton.getInstance();
		usuario = transfer.getUser();
	}

	@FXML
	void editUser(ActionEvent event) {
		UsuarioDAO dao = new UsuarioDAO();
		String email = this.TFTEmail.getText();
		String password = this.TFTContraseña.getText();
		String name = this.TFTUsuario.getText();
		int phone = Integer.parseInt(this.TFTelefono.getText());

		Usuario aux = usuario;
		if (!TFTEmail.getText().isEmpty() && !TFTUsuario.getText().isEmpty() && !TFTContraseña.getText().isEmpty()
				&& !TFTelefono.getText().isEmpty()) {
			aux.setName(name);
			aux.setEmail(email);
			aux.setPassword(password);
			aux.setPhone(phone);
			try {	
				dao.editar(usuario);
			} catch (Exception e) {
				e.printStackTrace();
				utils.Dialog.showError(  "Error al editar el usuario", "Error", e.getMessage());
			}		
		} else {
			utils.Dialog.showError("Error al editar el usuario", "Error", "Debe llenar todos los campos");
		}
	}

	@FXML
	void salir(ActionEvent event) {
		Stage stage = (Stage) this.buttSalir.getScene().getWindow();
		stage.close();
	}

}
