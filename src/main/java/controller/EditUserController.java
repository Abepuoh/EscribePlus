package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DAO.UsuarioDAO;
import model.DataObject.Usuario;
import utils.UsuarioSingleton;

public class EditUserController {

	@FXML
	private TextField TFContraseña;

	@FXML
	private TextField TFContraseña2;

	@FXML
	private TextField TFEmail;

	@FXML
	private TextField TFNombre;

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
	void editarUsuario(ActionEvent event) {
            UsuarioDAO dao = new UsuarioDAO();

		if (!TFEmail.getText().isEmpty() && !TFContraseña2.getText().isEmpty() 
                        && TFContraseña.equals(usuario.getPassword()) && !TFNombre.getText().isEmpty()
                        && !TFTelefono.getText().isEmpty()
                        && TFTelefono.getText().matches("[+-]?\\d*(\\.\\d+)?")) {
                    String email = this.TFEmail.getText();
                    String password = this.TFContraseña2.getText();
                    String name = this.TFNombre.getText();
                    int phone = Integer.parseInt(this.TFTelefono.getText());
                    usuario.setEmail(email);
                    usuario.setPassword(password);
                    usuario.setName(name);
                    usuario.setPhone(phone);
                    try{
                        UsuarioSingleton usuarioSignleton = UsuarioSingleton.getInstance();
					usuarioSignleton.setUser(usuario);
					dao.editar(usuario);
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Informacion");
					alert.setContentText("Se ha editado correctamente");
					alert.showAndWait();
                    }catch(Exception ex){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error de creacion");
			alert.setContentText("Porfavor no deje ningun dato vacío");
			alert.showAndWait();
                    }
		}
            
	}

	@FXML
	void salir(ActionEvent event) {
            Stage stage = (Stage) this.buttSalir.getScene().getWindow();
            stage.close();
	}

}
