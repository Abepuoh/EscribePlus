package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.DAO.LibroDAO;
import model.DAO.PartesDAO;
import model.DAO.PersonajeDAO;
import model.DAO.UsuarioDAO;
import model.DataObject.Libro;
import model.DataObject.Personaje;
import model.DataObject.Usuario;
import utils.ConnectionUtil;

public class Ejecutable {

	public static void main(String[] args) {
		PersonajeDAO pd = new PersonajeDAO();
		UsuarioDAO ud = new UsuarioDAO();
		LibroDAO ld = new LibroDAO();
		Personaje p = new Personaje();
		System.out.println(pd.getAll());
		
		
	}
}
