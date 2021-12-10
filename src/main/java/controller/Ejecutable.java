package controller;

import model.DAO.UsuarioDAO;
import model.DataObject.Usuario;

public class Ejecutable {
	public static void main(String[] args) {
		UsuarioDAO us=new UsuarioDAO();
		Usuario u = new Usuario("Juan", "jf@gmail.com", "1234", 654321987);
		//us.crear(u);
	}
}
